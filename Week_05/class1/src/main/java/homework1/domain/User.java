package homework1.domain;

import homework1.enums.City;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.xiao
 * @create 2021-02-21 19:26
 */
public class User {
    private Long id;
    private String name;
    private City city;
    private List<String> hobbies;
    private String[] phone;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", hobbies=" + hobbies +
                ", phone=" + Arrays.toString(phone) +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String[] getPhone() {
        return phone;
    }

    public void setPhone(String[] phone) {
        this.phone = phone;
    }
}
