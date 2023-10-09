package com.example.bookmanager.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ManagerUserMapper {
    //后台用户登录
    @Select("select * from ec_user where login_name=#{loginName} and password=#{password}")
    User getUserByLoginNameAndPassword(User user);

    //激活用户(更新激活码)
    @Update("update ec_user set disabled=1, active='' where active=#{activeCode}")
    void active(String activeCode);

    //保存用户
    @Insert("insert into ec_user(login_name, password, name, sex, email, phone, address, create_date, active) values(#{loginName}, #{password}, #{name}, #{sex}, #{email}, #{phone}, #{address}, #{createDate}, #{active})")
    void saveUser(User user);

    //验证登录名(是否重复)
    @Select("select * from ec_user where login_name=#{loginName}")
    User validateLoginName(String loginName);


}
