import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFrame;

public class Main{
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		String pth="C:\\Users\\feyza\\Desktop\\att48_xy.txt";
		BufferedReader reader = new BufferedReader(new FileReader(pth));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		
		
		City [] city=new City[lines];
		int count=0;
		Scanner input = null;
		 try {
	          input = new Scanner(new File(pth));
	        } catch (Exception ex) {
	            System.out.println("Can not open file.");
	            System.exit(0);
	        }
	        while(input.hasNextInt()) {
	            int x = input.nextInt();
	            int y= input.nextInt();
	            city[count]=new City(count+1,x,y);
	            count++;    
	        }
	        ArrayList<City> cities=new ArrayList<City>();
	        for(int i=0;i<count;i++) {
	        	 cities.add(city[i]);
	        }
	                
	        Route route=null;
	        Route minroute1=null;
	        Route minroute=null;
	        Badlinks improvedroute=new Badlinks();
	        ArrayList<City> path;
	        double mindistance=Double.MAX_VALUE;
	        double mindistance1=Double.MAX_VALUE;
	        
	        int nbr;
	        if(lines<=50) {
	        	nbr=lines;
	        }
	        else {
	        	nbr=50;
	        }
	      
	        for(int i=0;i<nbr;i++) {
	        route =new NearestNeighbor().findRoute(cities,i);
            if((route.calculateTotalDistance())<mindistance1) {
            	mindistance1=route.calculateTotalDistance();
            	minroute1=route;
            }
	        path=improvedroute.improve_tour(route.cities, route.calculateTotalDistance());
	        route=new Route(path);
            if((route.calculateTotalDistance())<mindistance) {
            	
            	mindistance=route.calculateTotalDistance();
            	minroute=route;
            }
	        
            for(int j=0;j<count;j++) {
	        	 cities.add(city[j]);
	        }
            
	        }
	        
	        
	        System.out.println("Nearest Neighbor");
	        minroute1.print();
	        System.out.println("Total distance:"+minroute1.calculateTotalDistance());
	        
	        System.out.println();
	        
	        
	        System.out.println("Nearest Neighbor with badlinks improvement");
	        minroute.print();
	        System.out.println("Total distance:"+minroute.calculateTotalDistance());
	        
	    
	        
	        
	        
	        Visualition vis= new Visualition(minroute.cities);
	        vis.setVisible(true);
	        
	   
	        double time = ((System.currentTimeMillis() - startTime));
	        System.out.println("Time:" + time/1000);
	        
	        
	        
	}
	

}
