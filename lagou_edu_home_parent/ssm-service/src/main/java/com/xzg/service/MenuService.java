package com.xzg.service;

import com.xzg.domain.Menu;

import java.util.List;

public interface MenuService {
    //查询所有菜单
    public List<Menu> findAllMenu();

    //查询子集菜单
    public List<Menu> findSubMenuListBypid(int pid);

    //根据角色ID查询关联菜单ID
    public List<Integer> findMenuByRoleId(int rid);
    //查询菜单
    Menu findMenuById(int id);
    //添加菜单
    public void saveMenu(Menu menu);
    //修改菜单
    public void updateMenu(Menu menu);
}
