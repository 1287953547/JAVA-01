package homework1.domain;

import java.util.List;

/**
 * @author Mr.xiao
 * @create 2021-02-21 19:58
 */
public class UserRepository {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                '}';
    }
}
