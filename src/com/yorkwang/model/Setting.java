package com.yorkwang.model;

import com.jfinal.plugin.activerecord.Model;

public class Setting extends Model<Setting>{
    public static final Setting dao = new Setting().dao();
}
