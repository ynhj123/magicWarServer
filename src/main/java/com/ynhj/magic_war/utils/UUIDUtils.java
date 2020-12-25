package com.ynhj.magic_war.utils;

import com.ynhj.magic_war.common.exception.BaseException;
import com.ynhj.magic_war.common.exception.SystemErrorType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: UUIDUtils
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class UUIDUtils {
    static List<String> spaceRoomIds = new ArrayList<>();
    static DecimalFormat decimalFormat = new DecimalFormat("000000");

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public static void init() {
        for (int i = 1; i < 1000; i++) {
            spaceRoomIds.add(decimalFormat.format(i));
        }
    }

    //生成房间id
    public static String getRoomId() throws BaseException {
        if (spaceRoomIds.size() == 0) {
            throw new BaseException(SystemErrorType.SYSTEM_ERROR, "房间达到上限");
        }
        return spaceRoomIds.remove(0);
    }

    public static void removeRoomId(String roomId) throws BaseException {
        spaceRoomIds.add(roomId);
        spaceRoomIds = spaceRoomIds.stream().sorted().collect(Collectors.toList());
    }

}
