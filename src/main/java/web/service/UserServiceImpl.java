package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override

    public void addUser(User user) {
        user.setPasswordUser(getPasswordEncoder().encode(user.getPasswordUser()));
        userDao.addUser(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        if (!user.getPasswordUser().equals(getUserById(user.getId()).getPasswordUser())) {
            user.setPasswordUser(getPasswordEncoder().encode(user.getPasswordUser()));
        }
        userDao.updateUser(user);
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }
}
