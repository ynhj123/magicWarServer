package com.ynhj.magic_war.service.Impl;

import com.ynhj.magic_war.mapper.RoleMapper;
import com.ynhj.magic_war.model.Role;
import com.ynhj.magic_war.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @date: 2020-11-16
 * @author: yangniuhaojiang
 * @title: RoleServiceImpl
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;


    @Override
    public int insert(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public Role get(String uid) {
        return roleMapper.getByUid(uid);
    }
}
