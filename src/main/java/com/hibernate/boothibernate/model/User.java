package com.hibernate.boothibernate.model;


import com.hibernate.boothibernate.utils.annotation.Excel;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: lushun
 * @date: 2018/12/27 14:41
 * Title: Controller
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    @Excel(column = "name", name = "姓名")
    private String name;

    @Column(length = 10)
    @Excel(column = "age", name = "年龄")
    private String age;

    @Column(length = 10)
    @Excel(column = "job", name = "职位")
    private String job;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
