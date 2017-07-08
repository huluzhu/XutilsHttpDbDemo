package com.baway.xutilshttpdbdemo;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by 胡计强 on 2017/07/07.
 */
@Table(name = "bean")
public class ListBean {
    private String data;
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "url")
    private String pic;
    @Column(name = "title")
    private String title;
    private int type;

    public String getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public String getPic() {
        return pic;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }


    public void setData(String data) {
        this.data = data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(int type) {
        this.type = type;
    }
}
