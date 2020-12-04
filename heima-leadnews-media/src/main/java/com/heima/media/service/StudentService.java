package com.heima.media.service;

import com.heima.media.pojo.Student;

import java.util.List;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/49:20
 */
public interface StudentService {
    /**
     * 新增
     * @param student
     */
    void saveStu(Student student);

    /**
     * 删除
     * @param id
     */
    void removeStu(Integer id);

    /**
     * 修改
     * @param student
     */
    void updateStu(Student student);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Student findStuById(Long id);

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<Student> findAll(Integer page, Integer pageSize);
}
