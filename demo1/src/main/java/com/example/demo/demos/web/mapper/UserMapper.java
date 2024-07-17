package com.example.demo.demos.web.mapper;

import com.example.demo.demos.web.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserMapper {
    @Select("select id,name,username,password,age from user ;")
    List<User> list();

    @Delete("delete from user where id = #{id}")
    void deleteById(String id);

    @Insert("insert into  user(id,name,username, password,age) values (#{id},#{name},#{username},#{password},#{age})")
    void insert(User user);

    @Select("select id,name,username,password,age from user where username =#{username} and password=#{password} ;")
    List<User> selectByUser(User user);
}
