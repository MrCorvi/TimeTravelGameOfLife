package weka;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomTree;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class train extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
		res.setContentType("text/html");
	    PrintWriter out = res.getWriter();
	    
		try {
	        RandomTree J48 = new RandomTree();
	        if (J48 != null) {
	        	out.println("KLASSIFIZIERER IS LOADED");
	        }
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		//System.out.println(dataset.toSummaryString());
		System.out.println("Fine");
	}
}
