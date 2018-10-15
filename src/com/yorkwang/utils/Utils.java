package com.yorkwang.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.stat.TableStat.Name;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class Utils {

    private static SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
    
    /**
     * Get real remote IP for client
     * @param request
     * @return
     */
    public static String getRemoteAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) ip = request.getHeader("Proxy-Client-IP");
        if(ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) ip = request.getHeader("WL-Proxy-Client-IP");
        if(ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) ip = request.getRemoteAddr();
        return ip;
    }
    
    /**
     * Check string is in string array or not
     * @param sa
     * @param s
     * @return
     */
    public static boolean stringArrayContains(String[] sa, String s) {
    	if (sa == null || s == null)
    		return false;
    	for(String aa : sa) {
    		if (s.indexOf(aa) > -1)
    			return true;
    	}
    	return false;
    }
    
    /**
     * Check if string is number
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) { 
        Pattern pattern = Pattern.compile("[0-9]*"); 
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false; 
        } 
        return true;
    }
    
    /**
     * Get today string
     * @return
     */
    public static String getToday() {
        return getSpecifiedDate(0);
    }
    
    /**
     * Get yesterday string
     * @return
     */
    public static String getYesterday() {
        return getSpecifiedDate(-1);
    }
    
    /**
     * Get last week date string
     * @return
     */
    public static String getLastWeek() {
        return getSpecifiedDate(-7);
    }
    
    /**
     * Get last month date string.
     * @return
     */
    public static String getLastMonth() {
        return getSpecifiedDate(-30);
    }
    
    /**
     * Get specified date string according to days from today.
     * @param days
     * @return
     */
    private static String getSpecifiedDate(int days) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_YEAR,days);
        return myFormatter.format(date.getTime());
    }
    
    /**
     * Get date format string.
     * @param date
     * @return
     */
    public static String getDateString(String date) {
        String dateStr = "";
        if (date != null && date.length() >= 8) {
            dateStr = date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8);
        }
        return dateStr;
    }
    
    /**
     * Get date format string without symbols.
     * @param date
     * @return
     */
    public static String getCureDateString(String date) {
        String dateStr = "";
        if (date != null && date.length() >= 8) {
            dateStr = date.replaceAll("/", "");
        }
        return dateStr;
    }
    
    /**
     * Get time format string.
     * @param time
     * @return
     */
    public static String getTimeString(String time) {
        String timeStr = "";
        if (time != null && time.length() >= 6) {
            timeStr = time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4, 6);
        }
        return timeStr;
    }
    
    /**
     * Get time format string without symbols.
     * @param time
     * @return
     */
    public static String getCureTimeString(String time) {
        String timeStr = "";
        if (time != null && time.length() >= 6) {
            timeStr = time.replaceAll(":", "");
        }
        return timeStr;
    }
    
    /**
     * Get MDS production name
     * @return
     */
    public static String getProductionName() {
//        if(SettingService.isVersion("TW")) {
            return "RMS";
//        } else {
//            return "MDS";
//        }
    }
    
    /**
     * Get current time string.
     * @return
     */
    public static String getCurrentTime() {
        Calendar date1 = Calendar.getInstance();
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMddhhmmssSS");
        return myFormatter.format(date1.getTime());
    }
    
    /**
     * Get current time string.
     * @return
     */
    public static String getCurrentTime2() {
        Calendar date1 = Calendar.getInstance();
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return myFormatter.format(date1.getTime());
    }
    
    /**
     * Get relative path for web from real image path.
     * @param imgPath physical image path
     * @return
     */
//    public static String getWebImagePath(String imgPath) {
//        String imageDir = SettingService.getSetting("ImageDir");
//        if(StrKit.notBlank(imgPath) && StrKit.notBlank(imageDir) && imgPath.toUpperCase().indexOf(imageDir.toUpperCase()) > -1) {
//            imgPath = imgPath.substring(imageDir.length()+1,imgPath.length());
//        } else {
//            imgPath = "";
//        }
//        return imgPath;
//    }
    
    /**
     * Trim the array symbol [ ] of cfind id array string.
     * @param s
     * @return
     */
    public static String trimArrayString(String s) {
        s = s.replaceAll("\\[","");
        s = s.replaceAll("\\]","");
        return s;
    }
    
    /**
     * Join string with character
     * @param join
     * @param strAry
     * @return
     */
    public static String join(String join,Object[] strAry){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<strAry.length;i++){
            if(strAry[i] != null && StrKit.notBlank(strAry[i].toString())) {
             if(i==(strAry.length-1)){
                 sb.append(strAry[i].toString());
             }else{
                 sb.append(strAry[i].toString()).append(join);
             }
            }
        }
        return new String(sb);
    }
    
    /**
     * Get dicom format patient name
     * @param s
     * @return
     */
    public static String getDicomPatientName(String s) {
        String name_part1 = "";
        String name_part2 = "";
        String name_part3 = s;
        String Patient_name = "";
        String[] pname_temp = (StrKit.isBlank(s) ? "" : s).split("=");
        if(pname_temp.length == 3) {
            for(int x=1; x <= pname_temp.length; x++) {
                if(x == 1) name_part1 = pname_temp[0].replaceAll("\\^"," ");
                if(x == 2) name_part2 = pname_temp[1].replaceAll("\\^","");
                if(x == 3) name_part3 = pname_temp[2].replaceAll("\\^","");
            }
            if(name_part2.equals("")) {
                if(name_part3.equals("")) {
                    if(!name_part1.equals("")) {
                        Patient_name = name_part1;
                    }
                } else {
                    Patient_name = name_part3;
                }
            } else {
                Patient_name = name_part2;
                if(!name_part3.equals("")) {
                    Patient_name += "(" + name_part3 + ")"; 
                }
            }
        } else {
            Patient_name = s.replaceAll("\\^"," ");
        }
        return Patient_name;
    }
    
    /**
     * Get html multiple content.
     * Convert line to <BR>
     * @param s
     * @return
     */
    public static String getHTMLContent(String s) {
        char charDA [] = {0x0D, 0x0A};
        String strDA = String.copyValueOf (charDA);
        String content = "";
        if (StrKit.notBlank(s)) {
          content = s.replaceAll (strDA, "<BR>");
        }
        return content;
    }
    
    /**
     * Get last insert id for sql auto increment id
     * @return
     */
    public static Integer getLastInsertID() {
        String sql = "select last_insert_id() as id";
        Record record = Db.findFirst(sql);
        return record.getInt("id");
    }
    
    /**
     * Get file size string from byte to other format.
     * @param size
     * @return
     */
    public static String getFileSizeStr(String size) {
        if (StrKit.notBlank(size)) {
            long i = Long.parseLong(size);
            if (i < 1024*1024) {
                return Math.round(((double)i)/1024) + "KB";
            } else {
                return Math.round(((double)i)/(1024*1024)) + "MB";
            }
        } else {
            return "0MB";
        }
    }
    
    /**
     * Get HMTL converted content.
     * @param s normal text message with multiple lines
     * @return html format content
     */
    public static String getHTMLConvertedContent(String s) {
        s = s.replaceAll ("(\r\n|\r|\n|\n\r)", "<br>");
        s = s.replaceAll ("\"", "&quot;");
        s = s.replaceAll ("'", "&apos;");
        return s;
    }
    
    /**
     * Get locale string by version setting.
     * @return
     */
//    public static String getLocale() {
//        String version = SettingService.getSetting("version");
//        if("JP".equals(version)) return "ja_JP";
//        if("CN".equals(version)) return "zh_CN";
//        if("TW".equals(version)) return "zh_TW";
//        if("US".equals(version)) return "en_US";
//        return "en_US";
//    }
    
    /**
     * Make the string into multiple lines.
     * @param s
     * @return
     */
    public static String getMultipleLineHTMLString(String s) {
        if(StrKit.isBlank(s))
            return "";
        String[] ss = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (String str : ss) {
            sb.append(getSplitHTML(str));
            sb.append(" ");
        }
        return sb.toString();
    }
    
    private static String getSplitHTML(String s) {
        if(StrKit.isBlank(s))
            return "";
        int length = 12;
        int stringLength = s.length();
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for(; i < stringLength / 12; i++) {
            sb.append(s.substring(i*12, i*12 +11));
            sb.append("<br>");
        }
        sb.append(s.substring(i*12));
        return sb.toString();
    }
    
    public static int getIntParaValue(Controller controller, String name) {
        String paraValue = controller.getPara(name);
        return StrKit.isBlank(paraValue) ? 0 : Integer.parseInt(paraValue);
    }
    
    public static String getArrayString(String[] arr) {
        StringBuffer sb = new StringBuffer();
        for (String str : arr) {
            sb.append(str);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
