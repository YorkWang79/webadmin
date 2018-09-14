package com.yorkwang.config;


import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.i18n.I18nInterceptor;
import com.jfinal.kit.Base64Kit;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.yorkwang.model.common._MappingKit;
import com.yorkwang.route.WebsiteRoutes;

public class WebsiteConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants arg0) {
		arg0.setDevMode(false);
		arg0.setEncoding("utf-8");
		arg0.setViewType(ViewType.JFINAL_TEMPLATE);
		PropKit.use("config.txt");
		
		arg0.setI18nDefaultBaseName("lang");

	}

	@Override
	public void configEngine(Engine arg0) {
		arg0.addSharedStaticMethod(StrKit.class);
        arg0.addSharedStaticMethod(PropKit.class);
		
        arg0.addSharedFunction("/common/_layout.html");
        arg0.addSharedFunction("/common/_paginatejs.html");
	}

	@Override
	public void configHandler(Handlers arg0) {
		arg0.add(new ContextPathHandler("basePath"));
	}

	@Override
	public void configInterceptor(Interceptors arg0) {
		arg0.add(new I18nInterceptor());
		arg0.add(new SessionInViewInterceptor());
	}
	
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}
	
	@Override
	public void configPlugin(Plugins arg0) {
		//Ehcache plugin
		arg0.add(new EhCachePlugin());
		
		// Set C3p0 db pool plugin
		DruidPlugin druidPlugin = createDruidPlugin();
		arg0.add(druidPlugin);
		
		// Set ActiveRecord Plugin
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setDialect(new MysqlDialect());
		// All mapping auto done in MappingKit
		_MappingKit.mapping(arp);
		arp.setShowSql(true);
		arg0.add(arp);
	}

	@Override
	public void configRoute(Routes arg0) {
		arg0.add(new WebsiteRoutes());
	}

    @Override
    public void afterJFinalStart() {
        super.afterJFinalStart();
    }

    @Override
    public void beforeJFinalStop() {
        super.beforeJFinalStop();
    }

}
