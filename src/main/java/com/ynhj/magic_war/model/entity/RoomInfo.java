package com.ynhj.magic_war.model.entity;

import com.ynhj.magic_war.model.entity.msg.protobuf.RoomListMsgOuterClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: RoomInfo
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class RoomInfo implements Comparable<RoomInfo> {
    private String id;
    private int maxCount;
    private int count;
    private int status; //0wait 1ing
    List<PlayerRoom> players = new ArrayList<>();

    /**
     * @return the PlayerRoom>
     * @author: yangniuhaojiang
     * @title: getPlayers
     * @description: update_version: update_date: update_author: update_note:
     */
    public List<PlayerRoom> getPlayers() {
        return players;
    }

    /**
     * @param players the PlayerRoom> to set
     * @author: yangniuhaojiang
     * @title: setPlayers
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setPlayers(List<PlayerRoom> players) {
        this.players = players;
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

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getMaxCount
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getMaxCount() {
        return maxCount;
    }

    /**
     * @param maxCount the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setMaxCount
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getCount
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setCount
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the RoomStatus
     * @author: yangniuhaojiang
     * @title: getStatus
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the RoomStatus to set
     * @author: yangniuhaojiang
     * @title: setStatus
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public int compareTo(RoomInfo o) {
        int id = Integer.parseInt(this.getId());
        Integer otherId=  Integer.parseInt(o.getId());
        return id-otherId;
    }

    public RoomListMsgOuterClass.RoomInfo toMsg() {
        RoomListMsgOuterClass.RoomInfo.Builder builder = RoomListMsgOuterClass.RoomInfo.newBuilder();
        builder.setCount(this.count);
        builder.setMaxCount(this.maxCount);
        builder.setStatus(this.status);
        builder.setId(this.id);
        players.forEach(playerRoom -> builder.addPlayers(playerRoom.toMsg()));
        return builder.build();
    }


    enum RoomStatus {

        WAIT(0),
        ING(1);
        private int val;
        private static final Map<Integer, RoomStatus> CODE_MAP = new HashMap<>();

        static {
            for (RoomStatus typeEnum : RoomStatus.values()) {
                CODE_MAP.put(typeEnum.getVal(), typeEnum);
            }
        }

        public static RoomStatus getEnum(Integer code) {
            return CODE_MAP.get(code);
        }

        RoomStatus(int val) {
            this.val = val;
        }

        /**
         * @return the ${field.typeName}
         * @author: yangniuhaojiang
         * @title: getVal
         * @description: update_version: update_date: update_author: update_note:
         */
        public int getVal() {
            return val;
        }

        /**
         * @param val the $field.typeName to set
         * @author: yangniuhaojiang
         * @title: setVal
         * @description: update_version: update_date: update_author: update_note:
         */
        public void setVal(int val) {
            this.val = val;
        }
    }



}
