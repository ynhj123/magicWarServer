package com.ynhj.magic_war.mapper;

import com.ynhj.magic_war.model.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


public interface EquipmentMapper  {


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table equipment
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table equipment
     *
     * @mbggenerated
     */
    int insert(Equipment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table equipment
     *
     * @mbggenerated
     */
    int insertSelective(Equipment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table equipment
     *
     * @mbggenerated
     */
    Equipment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table equipment
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Equipment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table equipment
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Equipment record);
}