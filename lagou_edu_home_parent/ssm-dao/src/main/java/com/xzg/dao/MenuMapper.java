package com.xzg.dao;

import com.xzg.domain.Menu;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    //查询所有菜单
    public List<Menu> findAllMenu();

    //查询子集菜单
    public List<Menu> findSubMenuListBypid(int pid);

    //根据角色ID查询关联菜单ID
    public List<Integer> findMenuByRoleId(int rid);
    Menu findMenuById(int id);

    //添加菜单
    public void saveMenu(Menu menu);
    //修改菜单
    public void  updateMenu(Menu menu);
}
