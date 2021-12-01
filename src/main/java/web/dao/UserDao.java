package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(long id);
    void deleteUser(long id);
    void updateUser(User user);
    User getUserByName(String name);

}
