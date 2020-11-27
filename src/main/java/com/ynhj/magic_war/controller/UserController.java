package com.ynhj.magic_war.controller;

import com.ynhj.magic_war.common.entity.Result;
import com.ynhj.magic_war.model.entity.JwtUser;
import com.ynhj.magic_war.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


/**
 * @date: 2020-11-16
 * @author: yangniuhaojiang
 * @title: TestController
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/info")
    public Result test(Authentication authentication){
        JwtUser user =  (JwtUser) authentication.getPrincipal();
        return userService.getDetail(user);
    }
}
