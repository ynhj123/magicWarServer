package com.ynhj.magic_war.model.entity;

import com.ynhj.magic_war.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @date: 2020-11-13
 * @author: yangniuhaojiang
 * @title: JwtUser
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class JwtUser implements UserDetails {

    private String id;
    private String username;
    private String password;
    //权限
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    public JwtUser(String uid, String username) {
        this.id = uid;
        this.username = username;
    }

    // 写一个能直接使用user创建jwtUser的构造器
    public JwtUser(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        authorities = Collections.singleton(new SimpleGrantedAuthority(id));
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getId
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the String to set
     * @author: yangniuhaojiang
     * @title: setId
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账号是否未过期，默认是false，记得要改一下
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号是否未锁定，默认是false，记得也要改一下
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 账号凭证是否未过期，默认是false，记得还要改一下
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
