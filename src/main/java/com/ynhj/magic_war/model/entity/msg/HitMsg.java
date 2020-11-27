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
    private int type; //0造成伤害并击飞1持续掉血
    private float x;
    private float z;
    private float ey;
    private float hp;
    private float speed;
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
    public int getType() {
        return type;
    }

    /**
     * @param type the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setType
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setType(int type) {
        this.type = type;
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

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getEy
     * @description: update_version: update_date: update_author: update_note:
     */
    public float getEy() {
        return ey;
    }

    /**
     * @param ey the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setEy
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setEy(float ey) {
        this.ey = ey;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getHp
     * @description: update_version: update_date: update_author: update_note:
     */
    public float getHp() {
        return hp;
    }

    /**
     * @param hp the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setHp
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setHp(float hp) {
        this.hp = hp;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getSpeed
     * @description: update_version: update_date: update_author: update_note:
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @param speed the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setSpeed
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }


}
