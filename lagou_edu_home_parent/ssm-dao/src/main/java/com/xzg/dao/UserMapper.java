package com.xzg.dao;

import com.xzg.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    //查询所有用户
    public List<User> findAllUserByPage(UserVO userVO);
    //修改用户状态
    public void updateUserStatus(@Param("id") int id, @Param("status") String status);
    //用户登录
    public User login(User user);

    //修改角色信息
    //1、先删除user的角色信息
    public void deleteUserRoles(int uid);
    //2、添加角色信息
    public void saveUserRoles(User_Role_relation user_role_relation);

    //查询用户关联角色信息(回显)
    public List<Role> findUserRoleById(Integer uid);
    //根据角色id查询角色拥有的父级菜单（-1）
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    //根据pid查询子集菜单
    public List<Menu> findSubMenuByPid(Integer pid);
    //获取用户的资源权限信息
    public List<Resource> findResourceByRoleId(List<Integer> ids);


}
