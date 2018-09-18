package com.yorkwang.model;

import com.jfinal.plugin.activerecord.Model;

public class Company extends Model<Company>{
    public static final Company dao = new Company().dao();
}
