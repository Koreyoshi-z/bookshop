package com.example.bookapp.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    //注册功能
    @Insert("insert into ec_user(login_name, password, name, sex, email, phone, address, create_date, active) values(#{loginName}, #{password}, #{name}, #{sex}, #{email}, #{phone}, #{address}, #{createDate}, #{active})")
    void saveUser(User user);

    //登录功能
    @Select("select * from ec_user where login_name=#{loginName} and password=#{password}")
    User queryUserByNameAndPassword(User user);

    //验证登录名(是否重复)
    @Select("select * from ec_user where login_name=#{loginName}")
    User validateLoginName(String loginName);

    //查询激活码(根据激活码获取用户信息，如果找不到说明激励不正确，或者已经激活过)
    @Select("select * from ec_user where active=#{activeCode}")
    User getUserByActive(String activeCode);

    //用户激活功能(更新激活码)
    @Update("update ec_user set disabled=1, active='' where active=#{activeCode}")
    void active(String activeCod);


}
