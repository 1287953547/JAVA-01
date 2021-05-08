package homework1.entity;

import org.springframework.stereotype.Component;

/**
 * @author Mr.xiao
 * @create 2021-02-21 20:55
 */
@Component
public class Student {
    private int id = 1;
    private String name = "lijing";

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
