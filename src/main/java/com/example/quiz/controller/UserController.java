package com.example.quiz.controller;

import com.example.quiz.mapper.UserMapper;
import com.example.quiz.model.Result;
import com.example.quiz.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper; // 注入Mapper实例

    @RequestMapping("/register")
    public Result addUser(String username, String password, String checkpassword){
        //代码逻辑步骤；
        if(StringUtils.isAnyBlank(username, password, checkpassword)){
            return Result.error("用户名或密码为空");
        }
        if (!password.equals(checkpassword)) {
            return Result.error("两次输入的密码不一致");
        }
        //2、校验用户的账户、密码是否符合要求：
        //	- 账户字符不能少于4个；
        //	- 密码不能小于8位；
        //  - 密码和确认密码要一致；
        //	- 账户不能与已有的重复；
        //	- 账户不能包含特殊字符；
        //	- 密码和校验密码相同；
        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        if(!matcher.matches()){
            return Result.error("用户名包含特殊字符");
        }
        //3、对密码进行加密；保证后端工作人员不能看到用户密码；
        final String SALT = "com.quiz";
        String encrptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        //4、向数据库插入数据；


            User user = new User();
            user.setUserName(username);
            user.setUserPassword(encrptedPassword);
            /**
             * 注册默认是普通用户，所以userRole设置为0；
             */
            user.setUserRole(0);
            user.setIsDelete(0);

            Date now = new Date();
            user.setCreateTime(now);
            user.setUpdateTime(now);

            //4.插入到数据库；
            int result = userMapper.saveUser(user);

            if (result > 0)
                return Result.success("新增用户成功");
            else
                return Result.error("注册用户失败");

    }
}
