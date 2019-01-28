package com.hibernate.boothibernate.dao;

import com.hibernate.boothibernate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author lushun
 * @date 2018-12-27
 */

public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findUserByAge(String age);

    @Query(value = "select * from user_info where name like %:name", nativeQuery = true)
    List<User> findByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("update User u set u.name = ?1 where u.id = ?2")
    int updateNameById(String name, Integer id);

    @Modifying
    @Transactional
    @Query(value = "delete from User where id =?1")
    void deleteById(Integer id);

}
