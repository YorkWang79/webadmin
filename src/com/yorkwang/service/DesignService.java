package com.yorkwang.service;

import java.util.List;

import com.yorkwang.model.Design;
import com.yorkwang.utils.Utils;

public class DesignService {
    public static List<Design> getDesignByYear(int year) {
        List<Design> designs = Design.dao.find("select * from design where year = " + year);
        for (Design design : designs) {
            String ids = design.getStr("pic_ids");
            String paths = Utils.getArrayString(UploadImageService.getCompanyImagesString(ids));
            design.setPaths(paths);
        }
        return designs;
    }
}
