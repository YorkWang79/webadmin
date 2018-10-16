package com.yorkwang.model;

import com.jfinal.plugin.activerecord.Model;
import com.yorkwang.service.UploadImageService;
import com.yorkwang.utils.Utils;

public class Design extends Model<Design>{
    public static final Design dao = new Design().dao();
    
    private String paths = "";
    private String[] pathArray = null;

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
        pathArray = paths!=null && paths.length() != 0 ? paths.split(",") : null;
    }
    
    public String[] getPathArray() {
        return pathArray;
    }
    
    public void generatePaths() {
        String ids = this.getStr("pic_ids");
        String paths = Utils.getArrayString(UploadImageService.getCompanyImagesString(ids));
        System.out.println("paths:" + paths);
        this.setPaths(paths);
    }
}
