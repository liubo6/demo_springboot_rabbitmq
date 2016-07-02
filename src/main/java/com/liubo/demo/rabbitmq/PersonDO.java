package com.liubo.demo.rabbitmq;

import java.io.Serializable;

/**
 * Created by hzlbo on 2016/7/2.
 */
public class PersonDO implements Serializable{
    private String id;
    private String userId;
    private String userName;
    private Integer age;

    public PersonDO() {
    }

    public PersonDO(String id, String userId, String userName, Integer age) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonDO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
