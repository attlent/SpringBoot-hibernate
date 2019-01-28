package com.hibernate.boothibernate.service.impl;

import com.hibernate.boothibernate.dao.UserDao;
import com.hibernate.boothibernate.model.User;
import com.hibernate.boothibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: lushun
 * @date: 2018/12/27 15:44
 * Title: Controller
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> getUser(User user) {
        return userDao.findUserByAge(user.getAge());
    }

    @Override
    public List<User> findByName(User user) {
        return userDao.findByName(user.getName());
    }

    @Override
    public int updateNameById(User user) {
        return userDao.updateNameById(user.getName(), user.getId());
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }


}
