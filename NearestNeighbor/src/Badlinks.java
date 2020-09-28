import java.util.ArrayList;
import java.util.Collections;

public class Badlinks {
public ArrayList<City> improve_tour(ArrayList<City> tour,double result) {
	
	ArrayList <City> temp=new ArrayList<City>();//to reverse
	ArrayList <City> temptour=new ArrayList<City>();
	double tmpdistance;
	int count=1;
	 
	 
	City A;
	City B;
	City C;
	City D;
	
	while(count!=0) {
		count=0;
		
	for(int i=1;i<tour.size()-2;i++) {
		for(int j=i+1;j<tour.size()-1;j++) {
		A=tour.get(i);
		B=tour.get(i-1);
		C=tour.get(j+1);
		D=tour.get(j);
		
		temptour=(ArrayList<City>) tour.clone();
		
		
		if(A.distance(B)+C.distance(D)>=A.distance(C)+B.distance(D)) {
           temp.removeAll(temp);
			
			for(int k=j;k>=i;k--) {
				temp.add(tour.get(k));
			}
			int m=0;
		   for(int l=i;l<=j;l++) {
			   
			temptour.set(l,temp.get(m) );
			m++;	
		    }
			
			
		 Route r=new Route(temptour);
		 tmpdistance=r.calculateTotalDistance();
		 if (tmpdistance<result) {
		  
		  tour=temptour;
		  result=tmpdistance;
		  count++;		
		}		
		}	
		}
		}

	}
	return tour;
}

}

