package com.xzg.controller;

import com.xzg.domain.Menu;
import com.xzg.domain.ResponseResult;
import com.xzg.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    //查询所有菜单
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();
        ResponseResult result = new ResponseResult(true, 200, "响应成功", allMenu);
        return result;
    }

    //回显菜单信息
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(int id){
        if (id==-1) {
            //添加操作
            List<Menu> subMenuListBypid = menuService.findSubMenuListBypid(-1);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", subMenuListBypid);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        }else {
            //修改操作
            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findSubMenuListBypid(-1);
            Map<String,Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",menuList);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        }
    }
   // 添加&修改菜单
    //添加菜单
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu (@RequestBody Menu menu){
        //添加菜单
        if (menu.getId()==null){
            menuService.saveMenu(menu);
            ResponseResult result = new ResponseResult(true,200,"添加成功",null);
            return result;
        }else {
        //更新操作
            menuService.updateMenu(menu);
            ResponseResult result = new ResponseResult(true,200,"更新成功",null);
            return result;
        }

    }

}
