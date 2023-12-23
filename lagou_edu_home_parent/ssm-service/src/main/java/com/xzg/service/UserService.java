package com.xzg.service;

import com.github.pagehelper.PageInfo;
import com.xzg.domain.Role;
import com.xzg.domain.User;
import com.xzg.domain.UserVO;
import com.xzg.domain.User_Role_relation;

import java.util.List;

public interface UserService {
    //查询所有用户
    public PageInfo findAllUserByPage(UserVO userVO);
    //更新用户状态
    public void updateUserStatus (Integer id, String status);
    //用户登录
    public User login(User user) throws Exception;
    //查询用户关联角色信息(回显)
    public List<Role> findUserRoleById(Integer uid);
    //修改角色信息
    //1、先删除user的角色信息
    public void deleteUserRoles(int userId);
    //2、添加角色信息
    public void saveUserRoles(UserVO userVO);

}
