package com.xzg.service.impl;

import com.xzg.dao.RoleMapper;
import com.xzg.domain.Role;
import com.xzg.domain.RoleMenuVO;
import com.xzg.domain.Role_menu_relation;
import com.xzg.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    //查询所有角色
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }
    //添加角色
    @Override
    public void saveRole(Role role) {
        role.setUpdatedTime(new Date());
        role.setCreatedTime(new Date());
        role.setCreatedBy("admain");
        role.setUpdatedBy("admain");
        roleMapper.saveRole(role);
    }
    //修改角色
    @Override
    public void updateRole(Role role) {
        //补充信息
        role.setUpdatedTime(new Date());
        roleMapper.updateRole(role);
    }
    //修改角色权限
    @Override
    public void RoleContextMenu(RoleMenuVO roleMenuVo) {
        //删除role的权限
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        //添加role的权限
        for (Integer mid: roleMenuVo.getMenuIdList()) {
            Role_menu_relation roleMenuRelation = new Role_menu_relation();
            roleMenuRelation.setMenuId(mid);
            roleMenuRelation.setRoleId(roleMenuVo.getRoleId());
            roleMenuRelation.setCreatedTime(new Date());
            roleMenuRelation.setUpdatedTime(new Date());
            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedby("system");
            roleMapper.saveRoleContextMenu(roleMenuRelation);
        }
    }
    //删除roles
    @Override
    public void deleteRole(int rid) {
        //先清空角色对应的权限信息
        roleMapper.deleteRoleContextMenu(rid);
        roleMapper.deleteRole(rid);

    }
}
