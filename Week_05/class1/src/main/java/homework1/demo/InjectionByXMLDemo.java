package homework1.demo;

import com.sun.scenario.effect.impl.prism.ps.PPSBlend_MULTIPLYPeer;
import homework1.domain.User;
import homework1.domain.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author Mr.xiao
 * @create 2021-02-21 20:10
 */
public class InjectionByXMLDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF\\injection-context.xml");
        User user = (User) applicationContext.getBean("user");
        System.out.println(user);
        UserRepository bean = applicationContext.getBean(UserRepository.class);
        System.out.println(bean);
    }
}

