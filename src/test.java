

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

import javax.servlet.http.*;

import com.google.gson.Gson;

public class test extends HttpServlet{
	
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
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		boolean[][] grid = new boolean[20][20];
		String temp = req.getParameter("grid");
		Random rd = new Random(); 
		Gson gson = new Gson();
		
		System.out.println(gson.fromJson(temp, boolean.class));
		
		
		//Temp: create random value
		for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
            	grid[i][j] = rd.nextBoolean();
            }
        }
		
		
		String data = "Hello World!";
		PrintWriter out = res.getWriter();
		
		String send = print2D(grid);
		System.out.println(temp);
		out.write(send);
		
	}
}
