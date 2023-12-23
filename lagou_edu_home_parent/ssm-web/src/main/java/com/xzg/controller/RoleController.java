package com.xzg.controller;

import com.xzg.domain.Menu;
import com.xzg.domain.ResponseResult;
import com.xzg.domain.Role;
import com.xzg.domain.RoleMenuVO;
import com.xzg.service.MenuService;
import com.xzg.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
     private RoleService roleService;
    @Autowired
    private MenuService menuService;
    //查询所有角色
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole (@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", allRole);
        return result;
    }
    //添加&修改角色
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        //添加角色
        if (role.getId()== null){
            roleService.saveRole(role);
            ResponseResult result = new ResponseResult(true, 200, "添加成功", null);
            return result;
        }//修改角色
        else {
            roleService.updateRole(role);
            ResponseResult result = new ResponseResult(true, 200, "添加成功", null);
            return result;
        }
    }
    //查询子集菜单
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        //查询所有父子级菜单（封装了subMenuList子级菜单的集合）
        List<Menu> subMenuListBypid = menuService.findSubMenuListBypid(-1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",subMenuListBypid);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }
    //根据角色ID查询关联菜单ID
    @RequestMapping("findMenuByRoleId")
    public ResponseResult findMenuByRoleId(int rid){
        List<Integer> menuByRoleId = menuService.findMenuByRoleId(rid);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", menuByRoleId);
        return result;
    }
    //修改角色权限
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVO roleMenuVO){
        roleService.RoleContextMenu(roleMenuVO);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", "");
        return result;
    }
    //删除role
    @RequestMapping("deleteRole")
    public ResponseResult deleteRole(int rid){
        roleService.deleteRole(rid);
        ResponseResult result = new ResponseResult(true, 200, "删除成功", null);
        return result;
    }
}
