package com.putti.web2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;

import com.google.cloud.bigtable.data.v2.models.Filters;
import com.google.cloud.bigtable.hbase.BigtableConfiguration;
import com.google.cloud.bigtable.hbase.adapters.filters.TimestampFilterUtil;
import com.google.cloud.bigtable.hbase.filter.BigtableFilter;
import com.google.cloud.bigtable.hbase.filter.TimestampRangeFilter;

/**
 * Servlet implementation class TestBigTableAPI
 */
@WebServlet("/TestBigTableAPI")
public class TestBigTableAPI extends HttpServlet {
	private final Logger log = Logger.getLogger(TestBigTableAPI.class.getName());
	private static final long serialVersionUID = 1L;
	
	private String projectId = "putti-project2";
	private String instanceId = "putti-bigtable1";
	private byte[] tableName = Bytes.toBytes("my-table");
	private byte[] columeFamily = Bytes.toBytes("cf1");
	private byte[] columnName = Bytes.toBytes("greeting");
	private static final String[] GREETINGS =
	      { "Hello World!", "Hello Cloud Bigtable!", "Hello HBase!" };	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestBigTableAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void readSingleRowFromKey(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	try {
    		log.info("before connect..");
    		Configuration config = BigtableConfiguration.configure(projectId, instanceId);
    		Connection conn = BigtableConfiguration.connect(config);
    		
    		//Connection conn = BigtableConfiguration.connect(this.projectId, this.instanceId);
    		
    		//Connection conn = ConnectionFactory.createConnection(config);
    		
    		
    		log.info("connected?");
    		Table table = conn.getTable(TableName.valueOf(this.tableName));
        	String rowKey = "greeting1";
            Result getResult = table.get(new Get(Bytes.toBytes(rowKey)));
            if (getResult.isEmpty() == false) {
            	            	            	
            	String greeting = Bytes.toString(getResult.getValue(this.columeFamily, this.columnName));
            	response.getWriter().append("\nGreeting={" + greeting + "}");
            	log.info("txt=" + greeting);

            } else {
            	response.getWriter().append("\nNot found rowKey=" + rowKey);
            }
            
            response.setStatus(200);
            
            long mil = System.currentTimeMillis();
            log.info("currentMil " + String.valueOf(mil));
            conn.close();
                		
    	} catch (Exception e) {
    		log.log(Level.SEVERE, e.toString());
    		response.getWriter().append("\ngot exception: " + e.toString());
    	}
    	    	
    }    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.readSingleRowFromKey(request, response);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.readSingleRowFromKey(request, response);
		
	}

}
