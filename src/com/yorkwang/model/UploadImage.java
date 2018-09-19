package com.yorkwang.model;

import com.jfinal.plugin.activerecord.Model;

public class UploadImage extends Model<UploadImage>{
    public static final UploadImage dao = new UploadImage().dao();
    
    public static final int TYPE_COMPANY_INFO = 1;
}
