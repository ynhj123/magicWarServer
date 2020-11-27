package com.ynhj.magic_war.controller;

import com.ynhj.magic_war.common.entity.Result;
import com.ynhj.magic_war.model.User;
import com.ynhj.magic_war.model.entity.dto.LoginRequest;
import com.ynhj.magic_war.model.entity.dto.RegisterRequest;
import com.ynhj.magic_war.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2020-11-13
 * @author: yangniuhaojiang
 * @title: AuthController
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;



    @PostMapping(value = "/register",produces="application/json;charset=UTF-8")
    public Result registerUser(@RequestBody RegisterRequest registerUser){
        return userService.insert(registerUser);
    }
    /*@PostMapping(value = "/login",produces="application/json;charset=UTF-8")
    public Result registerUser(@RequestBody LoginRequest loginUser){
        return Result.success();
    }*/
}
