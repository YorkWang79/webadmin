package com.yorkwang.service;

import java.util.HashMap;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.yorkwang.model.Setting;

public class SettingService {
    public static final SettingService service = new SettingService();
    
    private static HashMap<String, String> settings = new HashMap<String, String>();
    
    static {
        getAllSettingFromDB();
    }
    
    /**
     * Reload all settings
     */
    public static void reloadSettings() {
        getAllSettingFromDB();
    }

    /**
     * Get setting by name
     * @param settingName setting name
     * @return setting value
     */
    public static String getSetting(String settingName) {
        String setting = settings.get(settingName);
        return setting == null ? "" : setting;
    }
    
    /**
     * Get setting by name
     * @param settingName setting name
     * @return setting value
     */
    public static Integer getSettingInt(String settingName) {
        String setting = getSetting(settingName);
        return  StrKit.isBlank(setting) ? 0 : Integer.parseInt(setting);
    }
    
    /**
     * Get setting by name
     * @param settingName setting name
     * @return setting value
     */
    public static Boolean getSettingBool(String settingName) {
        String setting = getSetting(settingName);
        return  StrKit.isBlank(setting) ? false : setting.equals("1");
    }
    
    /**
     * Get all settings in DB
     */
    private static void getAllSettingFromDB() {
        settings.clear();
        List<Setting> list = Setting.dao.find("select * from settings");
        for (Setting setting : list) {
            settings.put(setting.getStr("name"), setting.getStr("value"));
        }
    }
    
    public static boolean updateSetting(String name, Object value) {
        Setting setting = Setting.dao.findById(name);
        boolean isAdd = setting == null;
        if (isAdd) {
            setting = new Setting();
            setting.set("name", name);
            setting.set("value", value);
        }
        
        if (value instanceof String) {
            setting.set("value", value);
        }
        if (value instanceof Integer) {
            setting.set("value", value);
        }
        if (value instanceof Boolean) {
            setting.set("value", value);
        }
        
        if (isAdd) {
            return setting.save();
        } else {
            return setting.update();
        }
    }
}
