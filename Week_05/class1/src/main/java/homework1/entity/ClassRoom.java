package homework1.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mr.xiao
 * @create 2021-02-21 20:56
 */
@Component
public class ClassRoom {
    @Autowired
    private Student student;

    @Override
    public String toString() {
        return "ClassRoom{" +
                "student=" + student +
                '}';
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
