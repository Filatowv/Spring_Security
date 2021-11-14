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

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

//    @Autowired
//    PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
//    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
//    @Transactional
    public void addUser(User user) {
//        user.setPasswords(getPasswordEncoder().encode(user.getPasswords()));
        userDao.addUser(user);
    }

    @Override
//    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
//    @Transactional
    public void updateUser(User user) {
//        if (!user.getPasswords().equals(getUserById(user.getId()).getPasswords())) {
//            user.setPasswords(getPasswordEncoder().encode(user.getPasswords()));
//        }
        userDao.updateUser(user);
    }

    @Override
//    @Transactional(readOnly = true)
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
//    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }
}
