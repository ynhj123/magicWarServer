package com.ynhj.magic_war.common.net;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2020-12-24
 * @author: yangniuhaojiang
 * @title: ProtobufMapper
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Component
public class ProtobufMapper {
    private static Map<Integer, String> intToString = new HashMap<>();
    private static Map<String, Integer> stringToInt = new HashMap<>();

    static {
        intToString.put(100, "MsgPing");
        intToString.put(101, "MsgPong");
        intToString.put(1001, "EnterMsg");
        intToString.put(1002, "RoomListMsg");
        intToString.put(1003, "CreateRoomMsg");
        intToString.put(1004, "EnterRoomMsg");
        intToString.put(1005, "ChatRoomMsg");
        intToString.put(1006, "KickRoomMsg");
        intToString.put(1007, "LeaveRoomMsg");
        intToString.put(1008, "GetRoomInfoMsg");
        intToString.put(1009, "StartMsg");
        intToString.put(1010, "UnreadyStartMsg");
        intToString.put(1011, "ReadyStartMsg");
        intToString.put(1012, "LoadFinishMsg");
        intToString.put(1013, "SyncPlayerMsg");
        intToString.put(1014, "SkillMsg");
        intToString.put(1015, "HitMsg");
        intToString.put(1016, "EndMsg");
        intToString.put(1017, "LeaveBattleMsg");
        intToString.forEach((k, v) -> stringToInt.put(v, k));
    }

    public static String getString(int key) {
        return intToString.get(key);
    }

    public static Integer getInt(String value) {
        return stringToInt.get(value);
    }
}
