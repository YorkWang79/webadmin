package com.yorkwang.model;

import com.jfinal.plugin.activerecord.Model;

public class Picture extends Model<Picture>{
    public static final Picture dao = new Picture().dao();
}
