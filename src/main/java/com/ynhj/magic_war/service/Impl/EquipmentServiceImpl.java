package com.ynhj.magic_war.service.Impl;

import com.ynhj.magic_war.mapper.EquipmentMapper;
import com.ynhj.magic_war.model.Equipment;
import com.ynhj.magic_war.model.Role;
import com.ynhj.magic_war.service.EquipmentService;
import com.ynhj.magic_war.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @date: 2020-11-30
 * @author: yangniuhaojiang
 * @title: EquipmentServiceImpl
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Resource
    EquipmentMapper equipmentMapper;
    @Autowired
    RoleService roleService;
    @Override
    public Equipment getTotal(String uid) {
        Role role = roleService.get(uid);
        return equipmentMapper.getTotalByRid(role.getId());
    }
}
