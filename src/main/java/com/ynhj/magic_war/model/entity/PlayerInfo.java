package com.ynhj.magic_war.model.entity;

import com.ynhj.magic_war.model.Equipment;
import com.ynhj.magic_war.model.entity.msg.SynPlayerMsg;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: Player
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class PlayerInfo {
    private String uid;
    private float x;
    private float z;
    private float ey;
    private Double hp;
    private int degree;
    private String nickname;
    private Double attach;
    private Double defense;
    private Double speed;
    private int killNum;

    public PlayerInfo(String uid, int degree, String nickname, Equipment equipment) {
        this.uid = uid;
        if (equipment != null) {
            this.hp = 100.0 + (equipment.getHp() == null ? 0 : equipment.getHp());
            this.attach = equipment.getAttach() == null ? 0.0 : equipment.getAttach();
            this.defense = equipment.getDefense() == null ? 0.0 : equipment.getDefense();
            this.speed = 5 + (equipment.getSpeed() == null ? 0.0 : equipment.getSpeed());
        } else {
            this.hp = 100.0d;
            this.attach = 0d;
            this.defense = 0d;
            this.speed = 5.0;
        }
        this.degree = degree;
        this.nickname = nickname;
        this.killNum = 0;
        switch (degree) {
            case 0:
                this.ey = 0;
                this.x = 0;
                this.z = -20;
                break;
            case 1:
                this.ey = -45;
                this.x = 15;
                this.z = -15;
                break;
            case 2:
                this.ey = -90;
                this.x = 20;
                this.z = 0;
                break;
            case 3:
                this.ey = -135;
                this.x = 15;
                this.z = 15;
                break;
            case 4:
                this.ey = -180;
                this.x = 0;
                this.z = 20;
                break;
            case 5:
                this.ey = -225;
                this.x = -15;
                this.z = 15;
                break;
            case 6:
                this.ey = 90;
                this.x = -20;
                this.z = 0;
                break;
            case 7:
                this.ey = 45;
                this.x = -15;
                this.z = 0;
                break;
            default:
                this.ey = 0;
                this.x = 0;
                this.z = 0;
                break;
        }
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getDegree
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getDegree() {
        return degree;
    }

    /**
     * @param degree the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setDegree
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setDegree(int degree) {
        this.degree = degree;
    }

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
    public double getHp() {
        return hp;
    }

    /**
     * @param hp the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setHp
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setHp(double hp) {
        this.hp = hp;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getNickname
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the String to set
     * @author: yangniuhaojiang
     * @title: setNickname
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getAttach
     * @description: update_version: update_date: update_author: update_note:
     */
    public double getAttach() {
        return attach;
    }

    /**
     * @param attach the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setAttach
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setAttach(double attach) {
        this.attach = attach;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getDefense
     * @description: update_version: update_date: update_author: update_note:
     */
    public double getDefense() {
        return defense;
    }

    /**
     * @param defense the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setDefense
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setDefense(double defense) {
        this.defense = defense;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getSpeed
     * @description: update_version: update_date: update_author: update_note:
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setSpeed
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setSpeed(double speed) {
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

    public void update(SynPlayerMsg msg) {
        this.x = msg.getX();
        this.ey = msg.getEy();
        this.z = msg.getZ();
        this.hp = msg.getHp();
        this.killNum = msg.getKillNum();
        this.speed = msg.getSpeed();
    }
}
