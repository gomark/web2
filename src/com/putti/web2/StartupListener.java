package com.putti.web2;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.hadoop.conf.Configuration;

import com.google.cloud.bigtable.hbase.BigtableConfiguration;

public class StartupListener implements ServletContextListener {
	private String projectId = "putti-project2";
	private String instanceId = "putti-bigtable1";	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialzied");
		Configuration config = BigtableConfiguration.configure(projectId, instanceId);
		System.out.println("Created BigTable Conf..");

	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}	
}
