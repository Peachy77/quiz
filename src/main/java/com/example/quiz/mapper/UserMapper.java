package com.example.quiz.mapper;

import com.example.quiz.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    public List<User> list();

    @Insert("insert into user(userName, userPassword,isDelete,userRole,createTime, updateTime)"+
            "values(#{userName}, #{userPassword}, #{isDelete}, #{userRole}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int saveUser(User user);
}
