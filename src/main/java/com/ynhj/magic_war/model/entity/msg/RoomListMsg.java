package com.ynhj.magic_war.model.entity.msg;

import com.ynhj.magic_war.model.entity.RoomInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: RoomListMsg
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class RoomListMsg extends MsgBase {
    int curPage;
    int pageSize;
    int size;
    List<RoomInfo> rooms = new ArrayList<>();
    String code;
    String msg;

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getSize
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setSize
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getCurPage
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getCurPage() {
        return curPage;
    }

    /**
     * @param curPage the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setCurPage
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getPageSize
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setPageSize
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the RoomInfo>
     * @author: yangniuhaojiang
     * @title: getRooms
     * @description: update_version: update_date: update_author: update_note:
     */
    public List<RoomInfo> getRooms() {
        return rooms;
    }

    /**
     * @param rooms the RoomInfo> to set
     * @author: yangniuhaojiang
     * @title: setRooms
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setRooms(List<RoomInfo> rooms) {
        this.rooms = rooms;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getCode
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setCode
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getMsg
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the String to set
     * @author: yangniuhaojiang
     * @title: setMsg
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
