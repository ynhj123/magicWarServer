package com.ynhj.magic_war.Netty;

import com.ynhj.magic_war.model.entity.OnlineUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public final class SessionMap {
    private SessionMap() {
    }

    private final Logger log = LogManager.getLogger(getClass());
    private static SessionMap singleInstance = new SessionMap();

    //会话集合
    private ConcurrentHashMap<String, ServerSession> map =
            new ConcurrentHashMap<String, ServerSession>();

    public static SessionMap inst() {
        return singleInstance;
    }

    /**
     * 增加session对象
     */
    public void addSession(
            String sessionId, ServerSession s) {
        map.put(sessionId, s);
        log.info("用户登录:id= " + s.getUser().getId()
                + "   在线总数: " + map.size());

    }

    /**
     * 获取session对象
     */
    public ServerSession getSession(String sessionId) {
        if (map.containsKey(sessionId)) {
            return map.get(sessionId);
        } else {
            return null;
        }
    }

    /**
     * 根据用户id，获取session对象
     */
    public List<ServerSession> getSessionsBy(String userId) {

        List<ServerSession> list = map.values()
                .stream()
                .filter(s -> s.getUser().getId().equals(userId))
                .collect(Collectors.toList());
        return list;
    }

    public List<ServerSession> getSessionsBy(List<String> userIds) {

        List<ServerSession> list = map.values()
                .stream()
                .filter(s -> userIds.contains(s.getUser().getId()))
                .collect(Collectors.toList());
        return list;
    }

    /**
     * 删除session
     */
    public void removeSession(String sessionId) {
        if (!map.containsKey(sessionId)) {
            return;
        }
        ServerSession s = map.get(sessionId);
        map.remove(sessionId);

    }


    public boolean hasLogin(OnlineUser user) {
        Iterator<Map.Entry<String, ServerSession>> it =
                map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ServerSession> next = it.next();
            OnlineUser u = next.getValue().getUser();
            if (u.getId().equals(user.getId())) {
                return true;
            }
        }

        return false;
    }
}
