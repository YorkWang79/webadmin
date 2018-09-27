package com.yorkwang.model;

import com.jfinal.plugin.activerecord.Model;

public class Design extends Model<Design>{
    public static final Design dao = new Design().dao();
    
    private String paths = "";

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }
    
}
