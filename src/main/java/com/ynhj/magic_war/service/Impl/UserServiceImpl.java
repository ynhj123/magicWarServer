package com.ynhj.magic_war.service.Impl;

import com.ynhj.magic_war.common.entity.Result;
import com.ynhj.magic_war.common.exception.BaseException;
import com.ynhj.magic_war.common.exception.SystemErrorType;
import com.ynhj.magic_war.mapper.UserMapper;
import com.ynhj.magic_war.model.Role;
import com.ynhj.magic_war.model.User;
import com.ynhj.magic_war.model.entity.JwtUser;
import com.ynhj.magic_war.model.entity.dto.RegisterRequest;
import com.ynhj.magic_war.model.entity.vo.UserVo;
import com.ynhj.magic_war.service.RoleService;
import com.ynhj.magic_war.service.UserService;
import com.ynhj.magic_war.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @date: 2020-11-13
 * @author: yangniuhaojiang
 * @title: UserServiceImpl
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Autowired
    RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUserName = userMapper.getByUserName(username);
        if (byUserName == null) {
            throw new BadCredentialsException("用户名或密码错误");

        }
        return new JwtUser(byUserName);
    }

    @Override
    @Transactional
    public Result insert(RegisterRequest registerUser) {
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
        User byUserName = userMapper.getByUserName(user.getUsername());
        if (byUserName != null) {
            return Result.fail(SystemErrorType.REPEAT_ERROR);
        }
        user.setId(UUIDUtils.getUUID());
        user.setCreateTime(new Date());
        int insert = userMapper.insert(user);

        if (insert == 0) {
            return Result.fail(SystemErrorType.SYSTEM_ERROR);
        }
        Role role = new Role();
        role.setCreateTime(new Date());
        role.setScore(0);
        role.setName(registerUser.getNickName());
        role.setUserId(user.getId());
        int insertRole = roleService.insert(role);
        if (insertRole == 0) {
            throw new BaseException(SystemErrorType.SYSTEM_ERROR, "注册失败！");
        }
        return Result.success();
    }

    @Override
    public Result getDetail(JwtUser user) {
        User byUserName = userMapper.getByUserName(user.getUsername());
        Role role = roleService.get(user.getId());
        UserVo userVo = new UserVo();
        userVo.setIcon(byUserName.getIcon());
        userVo.setUid(byUserName.getId());
        userVo.setUsername(byUserName.getUsername());
        userVo.setRoleId(role.getId());
        userVo.setNickname(role.getName());
        userVo.setScore(role.getScore());
        return Result.success(userVo);
    }

}
