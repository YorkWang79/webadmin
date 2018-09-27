package com.yorkwang.model;

import com.jfinal.plugin.activerecord.Model;

public class UploadImage extends Model<UploadImage>{
    public static final UploadImage dao = new UploadImage().dao();
    
    public static final int TYPE_COMPANY_INFO = 1;
    
    private boolean selected = false;

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
