package com.ynhj.magic_war.service;

import com.ynhj.magic_war.common.entity.Result;
import com.ynhj.magic_war.model.User;
import com.ynhj.magic_war.model.entity.JwtUser;
import com.ynhj.magic_war.model.entity.dto.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @date: 2020-11-13
 * @author: yangniuhaojiang
 * @title: UserService
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public interface UserService extends UserDetailsService {
    Result insert(RegisterRequest registerUser);

    Result getDetail(JwtUser user);
}
