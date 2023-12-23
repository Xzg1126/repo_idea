package com.xzg.dao;

import com.xzg.domain.Role;
import com.xzg.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {
    //查询所有角色
    public List<Role> findAllRole(Role role);
    //添加角色
    public void saveRole(Role role);
    //修改角色
    public void updateRole(Role role);

    //先删除role_menu_relation里角色对应的menu
    public void deleteRoleContextMenu(Integer roleId);

    //为role添加menu
    public void saveRoleContextMenu(Role_menu_relation rma);
    //删除roles
    public void deleteRole(int rid);
}
