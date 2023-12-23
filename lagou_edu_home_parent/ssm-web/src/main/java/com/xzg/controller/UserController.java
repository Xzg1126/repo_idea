package com.xzg.controller;

import com.github.pagehelper.PageInfo;
import com.xzg.domain.ResponseResult;
import com.xzg.domain.Role;
import com.xzg.domain.User;
import com.xzg.domain.UserVO;
import com.xzg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    //查询用户
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){
        PageInfo pageInfo = userService.findAllUserByPage(userVO);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", pageInfo);
        List<User> list = pageInfo.getList();
        System.out.println(list);
        return result;
    }

    //修改用户状态
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id, String status){
        Map<String,String> map = new HashMap<>();
        map.put("status",status);
        userService.updateUserStatus(id,status);
        ResponseResult result = new ResponseResult(true, 200, "修改成功", map);
        return result;
    }
    //用户登录
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if (user1!=null){
            //保存id和access_token到session中
            String access_token = UUID.randomUUID().toString();
            HttpSession session = request.getSession();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());
            //将查询出来的user1存到map
            map.put("user",user1);
            return new ResponseResult(true,1,"登录成功",map);
        }else {
            return new ResponseResult(true,400,"查询失败",null);
        }
    }
    //查询用户关联的角色信息
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer uid){
        List<Role> userRoleById = userService.findUserRoleById(uid);
        return new ResponseResult(true,200,"查询成功",userRoleById);
    }
  /*  //删除user的角色信息
    @RequestMapping("/deleteUserRoles")
    public ResponseResult deleteUserRoles(int uid){
        userService.deleteUserRoles(uid);
        System.out.println("删除成功");
        return new ResponseResult(true,200,"删除成功",null);
    }*/
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole (UserVO userVO){
        userService.saveUserRoles(userVO);
        return new ResponseResult(true,200,"分配角色成功",null);
    }
}
