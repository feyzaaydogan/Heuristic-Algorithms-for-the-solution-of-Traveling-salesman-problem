import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*; 
public class Main {

	public static void main(String[] args) throws IOException {
	long startTime = System.currentTimeMillis();
		
		String pth="C:\\Users\\feyza\\Desktop\\att48_xy.txt";
		
		BufferedReader reader = new BufferedReader(new FileReader(pth));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		
		City [] city=new City[lines];
		int count1=0;
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
            
            city[count1]=new City(count1+1,x,y);
            count1++;
           
        }
        
        ArrayList<City> al=new ArrayList<City>();
        for(int i=0;i<count1;i++) {
        	 al.add(city[i]);
        }
        
        double distance;
        Divideconquer result=new Divideconquer();
        
        Route r=new Route(result.divide_tsp(al));
        distance=r.calculateTotalDistance();
        System.out.println("Divide and Conquer");
        r.print();
        System.out.println("Total distance:" +distance);
        
      
        System.out.println();
        
        Badlinks improvedroute=new Badlinks();
        ArrayList<City> path=improvedroute.improve_tour(r.cities, distance);
        r=new Route(path);
        
        System.out.println("Divide and Conquer with improved bad links");
        
        r.print();
        System.out.println("Total distance:" + r.calculateTotalDistance());
        
        Visualition vis= new Visualition(r.cities);
        vis.setVisible(true);
        
        double time = ((System.currentTimeMillis() - startTime));
        System.out.println("Time:" + time/1000);

	}

}
