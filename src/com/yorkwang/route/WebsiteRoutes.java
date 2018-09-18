package com.yorkwang.route;

import com.jfinal.config.Routes;
import com.yorkwang.controller.*;

public class WebsiteRoutes extends Routes {

    @Override
    public void config() {
        setBaseViewPath("/");
        
        add("/admin", AdminPageController.class, "/admin");
        /*
        add("/login", LoginController.class, "/login");
        add("/status", MDSStatusController.class, "/status");
        add("/popup", PopupPanelController.class, "/popup");
        
        add("/query", StudyQueryController.class, "/query");
        add("/submit", BurnController.class);
        
        add("/modify", ModifyInfoController.class, "/modify");
        add("/send", SendController.class);

        add("/job", JobController.class, "/job");
        
        add("/history", HistoryController.class, "/history");
        
        add("/worklist", WorklistController.class, "/worklist");

        add("/admin", AdminController.class);
        add("/admin/labelsetting", LabelSettingController.class, "/admin");
        add("/admin/accountsetting", UserController.class, "/admin");
        add("/admin/importsetting", ImportSettingController.class, "/admin");
        add("/admin/serversetting", ServerSettingController.class, "/admin");
        add("/admin/disksetting", DiskSettingController.class, "/admin");
        add("/admin/paramsetting", ParamSettingController.class, "/admin");
        */
	}

}
