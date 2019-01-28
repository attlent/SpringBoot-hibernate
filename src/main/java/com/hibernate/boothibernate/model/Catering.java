package com.hibernate.boothibernate.model;

import com.hibernate.boothibernate.utils.annotation.Excel;


/**
 * Created by IntelliJ IDEA.
 * User: lushun
 * Title: Catering
 * Date: 2017/12/20
 * Time: 17:11
 */
public class Catering {

    private Integer id;

    /**
     * 日期
     */
    @Excel(name = "day", columnIndex = 1)
    private Integer day;

    /**
     * 时间
     */
    @Excel(name = "time", columnIndex = 2)
    private String time;

    /**
     * 部门名称
     */
    @Excel(name = "depart_name", columnIndex = 3)
    private String depart_name;

    /**
     * 楼层
     */
    @Excel(name = "floor", columnIndex = 4)
    private String floor;

    /**
     * 地址
     */
    @Excel(name = "add", columnIndex = 5)
    private String add;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }


}
