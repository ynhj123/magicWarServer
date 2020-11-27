package com.ynhj.magic_war.common.entity;



import java.util.List;

/**
 * @Auther: ynhj
 * @Date: 2020-01-05 22:16
 * @Description:
 */

public class Page<T> {
    List<T> list;
    Long count;


    public static Page build() {
        return new Page();
    }

    public Page list(List<T> list) {
        this.list = list;
        return this;
    }

    public Page count(Long count) {
        this.count = count;
        return this;
    }

    /**
     * @return the List<T>
     * @author: yangniuhaojiang
     * @title: getList
     * @description: update_version: update_date: update_author: update_note:
     */
    public List<T> getList() {
        return list;
    }

    /**
     * @param list the List<T> to set
     * @author: yangniuhaojiang
     * @title: setList
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * @return the Long
     * @author: yangniuhaojiang
     * @title: getCount
     * @description: update_version: update_date: update_author: update_note:
     */
    public Long getCount() {
        return count;
    }

    /**
     * @param count the Long to set
     * @author: yangniuhaojiang
     * @title: setCount
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setCount(Long count) {
        this.count = count;
    }
}
