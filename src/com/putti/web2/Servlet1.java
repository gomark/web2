package com.putti.web2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("hey-z updated Servlet1 (web2) at: ").append(request.getContextPath());
		response.getWriter().append("\n");
		response.getWriter().append("env-LANGUAGE=" + System.getenv("LANGUAGE") + "\n");
		response.getWriter().append("env-API_KEY=" + System.getenv("API_KEY") + "\n");
		response.getWriter().append("env-RESTSERVICE1=" + System.getenv("RESTSERVICE1") + "\n");
		response.getWriter().append("calling some rest from restservice1:\n");
		
		this.testRest(request, response);
	}
	
	private void testRest(HttpServletRequest request, HttpServletResponse response) {
		String urlStr = System.getenv("RESTSERVICE1") + "Servlet1";
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

				response.getWriter().append("\nHTTP 200 OK");
				InputStream s = connection.getInputStream();
				InputStreamReader r = new InputStreamReader(s, StandardCharsets.UTF_8);
				
			} else {
				response.getWriter().append("\nHTTP Response: " + String.valueOf(connection.getResponseCode()));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
