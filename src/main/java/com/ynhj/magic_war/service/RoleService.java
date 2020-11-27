package com.ynhj.magic_war.service;

import com.ynhj.magic_war.model.Role;

/**
 * @date: 2020-11-16
 * @author: yangniuhaojiang
 * @title: RoleService
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public interface RoleService {

    int insert(Role role);
    Role get(String uid);
}
