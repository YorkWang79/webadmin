package com.yorkwang.model.common;

import javax.sql.DataSource;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;
import com.rimage.config.RimageConfig;

/**
 * Generator all DB model class
 */
public class _JFinalDemoGenerator {
	
	public static DataSource getDataSource() {
		PropKit.use("db_config.txt");
		DruidPlugin druidPlugin = RimageConfig.createDruidPlugin();
		druidPlugin.start();
		return druidPlugin.getDataSource();
	}
	
	public static void main(String[] args) {
		String baseModelPackageName = "com.rimage.model.base";
		String baseModelOutputDir = PathKit.getWebRootPath() + "/../src/com/rimage/model/base";
		
		String modelPackageName = "com.demo.model";
		String modelOutputDir = baseModelOutputDir + "/..";
		
		Generator generator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		generator.setGenerateChainSetter(false);
		//generator.addExcludedTable("adv");
		generator.setGenerateDaoInModel(true);
		generator.setGenerateChainSetter(true);
		generator.setGenerateDataDictionary(false);
		//generator.setRemovedTableNamePrefixes("t_");
		generator.generate();
	}
}




