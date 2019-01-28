package com.hibernate.boothibernate.service;

import com.hibernate.boothibernate.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: lushun
 * @date: 2018/12/27 15:43
 * Title: Controller
 */

public interface UserService {

    /**
     * 保存用户信息
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 用户查询
     *
     * @param user
     * @return
     */
    List<User> getUser(User user);

    /**
     * 按姓名查询
     *
     * @param user
     * @return
     */
    List<User> findByName(User user);

    /**
     * 更新
     *
     * @param user
     * @return
     */
    int updateNameById(User user);

    /**
     * 删除
     *
     * @param user
     * @return
     */
    void deleteUser(User user);
}

