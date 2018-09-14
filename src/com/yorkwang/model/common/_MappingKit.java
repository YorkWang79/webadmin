package com.yorkwang.model.common;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.yorkwang.model.*;

/**
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

    public static void mapping(ActiveRecordPlugin arp) {
        /*
        //Study tables
        arp.addMapping("patient", "PAT_ID", Patient.class);
        arp.addMapping("study", "STUDY_UID", Study.class);
        arp.addMapping("series", "SERIES_UID", Series.class);
        arp.addMapping("image", "IMAGE_UID", Image.class);
        
        //Delete tables
        arp.addMapping("deleteinstruction", "JOB_ID", DeleteInstruction.class);
        
        //Cfind tables
        arp.addMapping("cfindinstruction", "JOB_ID", CFindInstruction.class);
        arp.addMapping("cfindresult", "JOB_ID", CFindResult.class);
        
        //Job progress tables
        arp.addMapping("webinstruction", "JOB_ID", Job.class);
        arp.addMapping("webinstruction_details", "JOB_ID", JobDetails.class);
        
        //History tables
//        arp.addMapping("recordhistory", "jobid", History.class);
        
        //worklist tables
        arp.addMapping("mwlinstruction", "JOB_ID", NWLInstruction.class);
        arp.addMapping("mwlresult", "JOB_ID", NWLResult.class);
        
        //User tables
        arp.addMapping("logininfo", "LOGIN_ID", User.class);
        arp.addMapping("user_group", "GROUP_ID", Group.class);
        arp.addMapping("user_role", "ROLE_ID", Role.class);
        
        //Setting tables
        arp.addMapping("mds_settings", "CONFIG_SECTION,CONFIG_KEY", Setting.class);
        
        arp.addMapping("labelsetting", "NAME", Label.class);
        arp.addMapping("serverinfo", "ALIASNAME", Server.class);
        arp.addMapping("cechoinstruction", "JOB_ID", CEcho.class);
        
        //Viewers table
        arp.addMapping("viewers", "ID", Viewer.class);
        */
    }
}

