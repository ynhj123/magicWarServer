package com.ynhj.magic_war.model.entity.msg;

import java.util.List;

/**
 * @date: 2020-12-04
 * @author: yangniuhaojiang
 * @title: BattleResultMsg
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class BattleResultMsg extends MsgBase {
    //
    List<PlayerRank> ranks;
    //
    class PlayerRank {
        private String uid;
        private String nickname;
        private int score;
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
         * @title: getScore
         * @description: update_version: update_date: update_author: update_note:
         */
        public int getScore() {
            return score;
        }

        /**
         * @param score the $field.typeName to set
         * @author: yangniuhaojiang
         * @title: setScore
         * @description: update_version: update_date: update_author: update_note:
         */
        public void setScore(int score) {
            this.score = score;
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
}
