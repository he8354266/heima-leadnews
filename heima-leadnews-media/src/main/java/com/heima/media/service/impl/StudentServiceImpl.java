package com.heima.media.service.impl;

import com.heima.media.pojo.Student;
import com.heima.media.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/49:20
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveStu(Student student) {
        mongoTemplate.save(student);
    }

    @Override
    public void removeStu(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Student.class);
    }

    @Override
    public void updateStu(Student student) {
        Query query = new Query(Criteria.where("id").is(student.get_id()));
        Update update = new Update();
        update.set("name", student.getName());
        update.set("sex", student.getSex());
        update.set("age", student.getAge());
        update.set("gradeId", student.getGradeId());
        mongoTemplate.updateFirst(query, update, Student.class);
    }

    @Override
    public Student findStuById(Long id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Student one = mongoTemplate.findOne(query, Student.class);
        return one;
    }

    @Override
    public List<Student> findAll(Integer page, Integer pageSize) {
        if (page < 0) {
            page = 0;
        }
        //多表进行关联
        LookupOperation lookupOperation = LookupOperation.newLookup().
                from("grade").//关联表名
                localField("gradeId").//关联字段
                foreignField("_id").//主表关联字段对应的次表字段
                as("grade");//查询结果集合名
        // 将条件封装到Aggregate管道
        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperation,
                Aggregation.project("_id", "name", "sex", "age", "gradeId", "grade.gradeName"),//指定输出文档中的字段
                Aggregation.sort(Sort.Direction.ASC, "_id"),//排序
                Aggregation.skip((page - 1) * pageSize),//过滤条数，跳过一定数量的数据
                Aggregation.limit(pageSize));//限制传递给下一步的文档数量

        //查询
        List<Student> students = mongoTemplate.aggregate(aggregation, "student", Student.class).getMappedResults();
        return students;
    }
}
