package com.ynhj.magic_war;

import com.ynhj.magic_war.mapper.EquipmentMapper;
import com.ynhj.magic_war.model.Equipment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @date: 2020-11-12
 * @author: yangniuhaojiang
 * @title: MybitsTest
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class MybatisTest extends  MagicWarApplicationTests{

    @Autowired
    EquipmentMapper equipmentMapper;

    @Test
    void insert(){


        Equipment equipment = new Equipment();
        equipment.setHp(0);
        equipment.setType("1");
        equipment.setAttach(1.1);
        equipment.setName("dss");
        equipment.setDefense(2.1);
        equipmentMapper.insert(equipment);
    }
}
