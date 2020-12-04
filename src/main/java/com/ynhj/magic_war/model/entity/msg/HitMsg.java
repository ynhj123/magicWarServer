package com.ynhj.magic_war.model.entity.msg;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: HitMsg
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class HitMsg extends MsgBase {
    private String uid;
    private int skillId; //0造成伤害并击飞1持续掉血
    //
    private float x;
    private float z;

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getY
     * @description: update_version: update_date: update_author: update_note:
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setY
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getTargetId
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     * @param targetId the String to set
     * @author: yangniuhaojiang
     * @title: setTargetId
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    private float y;
    private String targetId;

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getUid
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid the String to set
     * @author: yangniuhaojiang
     * @title: setUid
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getType
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getSkillId() {
        return skillId;
    }

    /**
     * @param skillId the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setType
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getX
     * @description: update_version: update_date: update_author: update_note:
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setX
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getZ
     * @description: update_version: update_date: update_author: update_note:
     */
    public float getZ() {
        return z;
    }

    /**
     * @param z the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setZ
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setZ(float z) {
        this.z = z;
    }






}
