package com.xzg.service;

import com.xzg.domain.Role;
import com.xzg.domain.RoleMenuVO;

import java.util.List;

public interface RoleService {
    //查询所有角色
    public List<Role> findAllRole(Role role);
    //添加角色
    public void saveRole(Role role);

    public void updateRole(Role role);
    void RoleContextMenu(RoleMenuVO roleMenuVo);
    //删除roles
    public void deleteRole(int rid);
}
