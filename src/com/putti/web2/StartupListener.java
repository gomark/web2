package com.putti.web2;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.hadoop.conf.Configuration;

import com.google.cloud.bigtable.hbase.BigtableConfiguration;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.putti.web2.pubsub.MessageReceiverExample;

public class StartupListener implements ServletContextListener {
	private String projectId = "putti-project2";
	private String instanceId = "putti-bigtable1";	
	
	private Subscriber subscriber = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialzied");
		Configuration config = BigtableConfiguration.configure(projectId, instanceId);
		System.out.println("Created BigTable Conf..");

		//this.doPubSub();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
		if (this.subscriber != null) {
			this.subscriber.stopAsync();
		}
	}
	
	private void doPubSub() {
		System.out.println("working for pubsub");
		
		String subscriptionId = "bq-results-sub1";  // atest-pub-java1
		String projectId = "putti-project2";
		
		
		
		try {
			ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
			subscriber = Subscriber.newBuilder(subscriptionName, new MessageReceiverExample()).build();
			subscriber.startAsync();
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString());
		}
		
		System.out.println("done: pubsub");
	}
}
