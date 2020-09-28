import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

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
        
        ArrayList<City> al=new ArrayList<City>();
        for(int i=0;i<count;i++) {
        	 al.add(city[i]);
        }
        ArrayList<City> a =new ArrayList();
        GreedyAlgorithm b=new GreedyAlgorithm(al);
        
        Combination []c= b.findcombination();
        b.calculatelink(c);
        b.sortcombination(c, 0, c.length-1);
          
        double distance;
        	
        a=b.result(c);
        
        Route x=new Route(a);
        System.out.println("Greedy Algorithm");
        x.print();
        distance=x.calculateTotalDistance();
        System.out.println("Total distance:"+distance);
        
        
        System.out.println();
        
        
        
        Badlinks improvedroute=new Badlinks();
        ArrayList<City> path=improvedroute.improve_tour(x.cities, distance);
        x=new Route(path);
         
        System.out.println("Greedy Algorithm with improved bad links");
        x.print();
        System.out.println("Total distance:"+ x.calculateTotalDistance());
        
        Visualition vis= new Visualition(x.cities);
        vis.setVisible(true);

        double time = ((System.currentTimeMillis() - startTime));
        System.out.println("Time:" + time/1000);    
		
}
}