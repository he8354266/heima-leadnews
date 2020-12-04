import com.heima.media.MediaJarApplication;
import com.heima.media.pojo.Student;
import com.heima.media.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/49:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes =MediaJarApplication.class)
public class SpringbootMongodbApplicationTests {
    @Autowired
    private StudentService studentService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void findAll() {
       List<Student> all =  studentService.findAll(2,2);
        for (Student s:all){
            System.out.println(s.getName());
        }
    }

    @Test
    public void findStuById() {
        Student stuById = studentService.findStuById((long) 4);
        if (stuById != null) {
            System.out.println(stuById.get_id() + "####" + stuById.getName() + "###" + stuById.getAge() + "###" + stuById.getGradeId());

        }
    }

    @Test
    public void saveStu() {
        Student student = new Student();
        student.set_id(4);
        student.setGradeId(1);
        student.setName("丽丽111");
        student.setSex("女");
        student.setAge(20);
        studentService.saveStu(student);
    }

    @Test
    public void removeStu() {
        studentService.removeStu(4);
    }

    @Test
    public void updateStu() {
        Student student = new Student();
        student.set_id(4);
        student.setGradeId(1);
        student.setName("三三");
        student.setSex("女");
        student.setAge(20);
        studentService.updateStu(student);
    }
}
