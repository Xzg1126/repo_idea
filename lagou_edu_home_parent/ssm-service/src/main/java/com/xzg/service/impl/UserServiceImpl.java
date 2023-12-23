package com.xzg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzg.dao.UserMapper;
import com.xzg.domain.Role;
import com.xzg.domain.User;
import com.xzg.domain.UserVO;
import com.xzg.domain.User_Role_relation;
import com.xzg.service.UserService;
import com.xzg.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo findAllUserByPage(UserVO userVO) {

        PageHelper.startPage(userVO.getCurrentPage(),userVO.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVO);
        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;

    }

    @Override
    public void updateUserStatus(Integer id, String status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        userMapper.updateUserStatus(id,status);
    }
    //用户登录
    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        //如果查询到有该用户  将由前端传过来的用户密码与查到的用户的密文进行匹配
        if (user1!=null && Md5.verify(user.getPassword(), "xzg", user1.getPassword())){
            return user1;
        }
        //查无此人
        else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRoleById(Integer uid) {
        List<Role> userRoleById = userMapper.findUserRoleById(uid);
        return userRoleById;
    }
    //删除user的关联角色
    @Override
    public void deleteUserRoles(int userId) {
        userMapper.deleteUserRoles(userId);
    }

    @Override
    public void saveUserRoles(@RequestBody   UserVO userVo) {
        userMapper.deleteUserRoles(userVo.getUserId());

        for (int roleid : userVo.getRoleIdList()) {
            User_Role_relation userRoleRelation = new User_Role_relation();
            userRoleRelation.setUserId(userVo.getUserId());
            userRoleRelation.setRoleId(roleid);
            Date date = new Date();
            userRoleRelation.setCreatedTime(date);
            userRoleRelation.setUpdatedTime(date);
            userRoleRelation.setCreatedBy("system");
            userRoleRelation.setUpdatedby("system");
            userMapper.saveUserRoles(userRoleRelation);
        }

    }


}

