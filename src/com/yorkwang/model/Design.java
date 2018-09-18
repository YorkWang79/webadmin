package com.yorkwang.model;

import com.jfinal.plugin.activerecord.Model;

public class Design extends Model<Design>{
    public static final Design dao = new Design().dao();
}
