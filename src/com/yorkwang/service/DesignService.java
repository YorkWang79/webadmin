package com.yorkwang.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.yorkwang.model.Design;
import com.yorkwang.utils.Utils;

public class DesignService {
    public static List<Design> getDesignByYear(int year) {
        List<Design> designs = Design.dao.find("select * from design where year = " + year);
        for (Design design : designs) {
            design.generatePaths();
        }
        return designs;
    }
    
    public static List<Integer> getDesignYears() {
        List<Record> results = Db.find("select distinct year from design");
        List<Integer> years = new ArrayList<Integer>();
        for (Record record : results) {
            int year = record.getInt("year");
            years.add(year);
        }
        if(results.size() == 0)
            years.add(Utils.getCurrentYear());
        return years;
    }
    
    public static void delete(int id) {
        Design.dao.deleteById(id);
    }
}
