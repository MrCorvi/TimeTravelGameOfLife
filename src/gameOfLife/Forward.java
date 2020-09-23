package gameOfLife;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Forward extends HttpServlet{
	
	private int size = 20;
	
	private boolean[][] grid = new boolean[size][size];
	private boolean[][] nextGrid = new boolean[size][size];
	
	
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
	
	
	private int neighborsNumber(int x, int y){
		int count = (grid[x][y]) ? -1 : 0;
		for (int i = x-1; i <= x+1; i++) {
			if(i >= 0 && i < size){
	            for(int j = y-1; j <= y+1; j++) {
	            	if(j >= 0 && j < size && grid[i][j])
	    				count++;
	            }
			}
        }
		System.out.println(x + " , " + y +" : "+count);
		return count;
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		String temp = req.getParameter("grid");
		Random rd = new Random(); 
		Gson gson = new Gson();
		
		grid = gson.fromJson(temp, boolean[][].class);
		
		//Temp: create random value
		for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
            	//check number of neighbors
            	int count = neighborsNumber(i, j);
            	
            	if(grid[i][j]){
            		//kill check
            		if( count != 2 && count != 3){
            			nextGrid[i][j] = false;
            			System.out.println("Killed:" + i + " , " + j +" : "+count);
            		}else{
            			nextGrid[i][j] = true;
            		}
            	}else{
            		//born check
            		if(count == 3){
            			nextGrid[i][j] = true;
            		}else{
            			nextGrid[i][j] = false;
            		}
            	}
            }
        }
		

		
		PrintWriter out = res.getWriter();
		String send = print2D(nextGrid);

		//System.out.println(temp);
		out.write(send);
		
	}
}
