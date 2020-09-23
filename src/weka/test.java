package weka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

import javax.servlet.http.*;

import com.google.gson.Gson;


public class test extends HttpServlet{
	
	private boolean[][] matrixStart = new boolean[20][20];
	private boolean[][] matrixStop = new boolean[20][20];
	private boolean isStart = true;
	private int currentLine = 1;
	
	
	public String print2D(boolean mat[][]){ 
		String ret = "[";
		
        // Loop through all rows 
        for (int i= 0; i < mat.length; i++) {
        	ret += "[";
        	
            // Loop through all elements of current row 
            for (int j = 0; j < mat[i].length; j++) {
            	ret += mat[i][j];   
            	if(j != (mat[i].length-1)){
            		ret += ",";
            	}
            }
            ret += "]";
            if(i != (mat.length-1)){
        		ret += ",";
        	}
        }
        ret += "]";
        
        return ret;
    } 
	
	private void readCSV() throws IOException{
		boolean[][] matrix = new boolean[20][20];
		try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/Francesco/Desktop/compiti/MachineLearning/Progetto/train1Step.csv"))) {
		    String line = br.readLine();
		    line = br.readLine();
		    for(int i=1; i<currentLine; i++)
		    	line = br.readLine();
		    
		    if(!isStart)
		    	currentLine++;
		    
	        String[] values = line.split(",");
	        for(int i=0; i<values.length; i++) {
	        	int j = i-2;
	        	//System.out.println(j/20);
	        	if(j<0)
	        		continue;
	        	if(j/20 < 20)
	        		matrixStart[j/20][j%20] = (Integer.parseInt(values[i]) == 1);
	        	else {	        		
	        		matrixStop[(j/20)-20][j%20] = (Integer.parseInt(values[i]) == 1);
	        	}
	        }
		}
		//System.out.println(matrix[0][0]);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		boolean[][] grid = new boolean[20][20];
		String temp = req.getParameter("grid");
		Random rd = new Random(); 
		Gson gson = new Gson();
		
		grid = gson.fromJson(temp, boolean[][].class);
		//System.out.println(grid[0][0]);
		
		readCSV();
		
		PrintWriter out = res.getWriter();
		String send;
		if(isStart) {			
			send = print2D(matrixStart);
		}else {
			send = print2D(matrixStop);
		}
		isStart = !isStart;
		//System.out.println(temp);
		out.write(send);
		
	}
}
