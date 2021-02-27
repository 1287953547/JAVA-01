package homework1.demo;

import homework1.entity.ClassRoom;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Mr.xiao
 * @create 2021-02-21 21:09
 */
public class InjectionByAnnotationDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("META-INF\\injection-context.xml");
        ClassRoom bean = classPathXmlApplicationContext.getBean(ClassRoom.class);
        System.out.println(bean);
    }
}
