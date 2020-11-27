package com.ynhj.magic_war.model.entity.msg;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: SynPlayerMsg
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class SynPlayerMsg extends MsgBase {
    private String uid;
    private float x;
    private float z;
    private float ey;
    private float hp;
    private float speed;
    private int killNum;

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

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getKillNum
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getKillNum() {
        return killNum;
    }

    /**
     * @param killNum the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setKillNum
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setKillNum(int killNum) {
        this.killNum = killNum;
    }


}
