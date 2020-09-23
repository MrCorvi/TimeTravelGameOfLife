package gameOfLife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class buildCSV extends HttpServlet{
	
	
	private void readWriteCSV() throws IOException{
		boolean[][] matrix = new boolean[20][20];
		try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/Francesco/Desktop/compiti/MachineLearning/Progetto/test.csv"))) {
		    String line = br.readLine();
		    
		    try (PrintWriter writer = new PrintWriter(new File("C:/Users/Francesco/Desktop/compiti/MachineLearning/Progetto/test1Step.csv"))) {

		    	StringBuilder sb = new StringBuilder();
		    	
		    	sb.append(line+"\n");
			    for(int i=1; i<100000; i++) {			   
			    	line = br.readLine();
			    	if(line == null || line.length() == 0)
			    		break;
			    	
			    	String[] values = line.split(",");
			    	if(Integer.parseInt(values[1]) == 1) {
			    		//System.out.println(values[1]);
			    		sb.append(line+"\n");
			    	}
			    }
		    	
		    	writer.write(sb.toString());

		    	System.out.println("done!");

		    } catch (FileNotFoundException e) {
		    	System.out.println(e.getMessage());
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
		
		readWriteCSV();
		
		
		System.out.println("Fine");
	}
}
