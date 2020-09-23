package gameOfLife;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import weka.Model;

public class Backward  extends HttpServlet{
	
	private int size = 20;
	private int closeRange = 3;
	private int closeDim;
	
	private Model mod;
	private double[][] grid = new double[size][size];
	private boolean[][] gridBack = new boolean[size][size];
	
	
	
	public void init() throws ServletException  {
		mod = new Model();
		closeDim = 2*closeRange+1;
	}
	
	
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{

		String temp = req.getParameter("grid");
		Gson gson = new Gson();
		
		//grid = gson.fromJson(temp, double[][].class);
	    
	    System.out.println("Prediction previus grid");
	    
	    boolean[][] tempGrid = gson.fromJson(temp, boolean[][].class);
		
		//converting grid from boolean to double 
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				grid[x][y] = (tempGrid[x][y]) ? 1 : 0;
			}
		}
	    
	    //double[] input = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	    //cycle all grid cells
	    double[] input = new double[closeDim*closeDim];
	    for(int x = 0; x < size; x++){
	    	for(int y = 0; y < size; y++){
		    		    		
	    		//cycle close cells to setup input field
	    		for(int cy = y-closeRange; cy < y+(closeRange+1); cy++){
	    			for(int cx = x-closeRange; cx < x+(closeRange+1); cx++){
	    		    	//input[y*size + x] = 
	    				int index = (cy+closeRange-y)*closeDim+(cx+closeRange-x);
	    				//System.out.println(index);
	    				if(cx< 0 || cx >= size) {
	    					input[index] = 2;
	    	    		}else if(cy < 0 || cy >= size) {
	    	    			input[index] = 2;
	    	    		}else {	    	    			
	    	    			input[index] = grid[cx][cy];
	    	    		}
	    				//System.out.println("cellx: "+cx+" celly: "+cellY+" x: "+x +" y : "+y+" value: "+ val);
	    		    }
	    	    }
	    		System.out.println(Arrays.toString(input));
	    		
	    		//predict cell
	    		double pred = 3;
	    		try {
	    			pred = mod.predict(input);	    	
	    		}catch(Exception e) {
	    			System.out.println(e.toString());
	    		}
	    		
	    		
	    		//set back state grid respective cell
	    		gridBack[x][y] = (pred == 0) ? false : true;
		    }
	    }
	    
	    
	    //Sending predicted back state grid
	    PrintWriter out = res.getWriter();
		String send = gson.toJson(gridBack);

		//System.out.println(temp);
		out.write(send);	
		System.out.println("Fine");
	}
}
