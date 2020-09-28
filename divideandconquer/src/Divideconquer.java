import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Divideconquer {
	
	static int count=0;
	
	public ArrayList<City> divide_tsp(ArrayList <City> cities){
		if(cities.size()<=6) {
			ArrayList<City> small=new ArrayList();
            ArrayList<City>[] perm=new  ArrayList[factorial(cities.size())];
            count=0;
			permutation(small,cities,perm);
			return findsmallestroute(perm);
		}
		
		else {
			ArrayList<City> half1=new ArrayList();
			ArrayList<City> half2=new ArrayList();
			split(cities,half1,half2);
			
			return join_tours(divide_tsp(half1),divide_tsp(half2));
		}
	}
	
	public  void split(ArrayList<City> al,ArrayList<City> half1,ArrayList<City> half2) {
		int part1;
	    int part2;
		
		ArrayList<City>[] twopart =new ArrayList[2];
		int xmax=maxvalue(al,0);
		int xmin=minvalue(al,0);
	    int ymax=maxvalue(al,1);
	    int ymin=minvalue(al,1);
	    if((xmax-xmin)>=(ymax-ymin)) {sortX(al,0,al.size()-1);}
	    else {sortY(al,0,al.size()-1);}
	    
	    
	    part1=al.size()/2;
	    part2=al.size()-part1;
	    for(int i=0;i<part1;i++) {
	    half1.add(al.get(i));
	    		
	    }
	    for(int i=part1;i<al.size();i++) {
	        half2.add(al.get(i));
	        		
	        }

	}
	
	public  int minvalue(ArrayList<City> cities,int type) {
		   
		 // type means x or y
	    City city=null;
	    int min=Integer.MAX_VALUE;
	    int temp=0;
	    Iterator itr=cities.iterator(); 
	   
	    
	    while(itr.hasNext()) {
	    	city=(City)itr.next(); 
	    	if(type==0) {
		      temp=city.getX();
	         }
	    	else {
	         temp=city.getY();
	    	}
	    	
	    	if(temp<min) {
	    		
	    		min=temp;
	    	}
	    	}
	        
	    	return min;
	    }

	public  int maxvalue(ArrayList<City> cities,int type) {
	    City city=null;
	    int max=Integer.MIN_VALUE;
	    int temp=0;
	    
	    
	    Iterator itr=cities.iterator(); 
	    
	    
	    while(itr.hasNext()) {
	    	city=(City)itr.next();
	    	if(type==0) {
			      temp=city.getX();
		         }
		    	else {
		         temp=city.getY();
		    	}
	    	if(temp>max) {
	    		
	    		max=temp;
	    	}
	    	}
	        
	    	return max;
	    }
	
	
	public  void permutation(ArrayList<City> small,ArrayList<City> cities,ArrayList<City>[] perm) {
		
		if(cities.size()==0) {
			
			perm[count]=small;
			count++;
			return;
			
			
		}
		ArrayList<City> small1=new ArrayList<City>();
		ArrayList<City> cities1=new ArrayList<City>();


		City a=null;

		
		for(int i=0;i<cities.size();i++) {
			
			cities1=(ArrayList<City>) cities.clone();
			if(small==null) {
			small1=null;	
			}
			else {
			small1=(ArrayList<City>)small.clone();
			}
			a=cities1.get(i);
			small1.add(a);
			cities1.remove(i);
			
			
			permutation(small1,cities1,perm);
			
		}
			
		}	
	public  ArrayList<City> findsmallestroute(ArrayList<City>[] tours) {
		ArrayList<City> min=tours[0];
		Route temp=new Route(min);
		double result;
		double mindistance=temp.calculateTotalDistance();
		for(int i=1;i<tours.length;i++) {
			temp=new Route(tours[i]);
			result=temp.calculateTotalDistance();
			if(result<mindistance) {
				min=tours[i];
				mindistance=result;
				
			}
			
		}
		
		
		return min;
	}
	public  ArrayList<City> join_tours(ArrayList<City> tour1,ArrayList<City> tour2) {

		ArrayList<City>[] half1=new ArrayList[tour1.size()];
		ArrayList<City>[] half2=new ArrayList[tour2.size()];
		City temp;
		
		
		half1[0]=(ArrayList<City>)tour1 .clone();
		half2[0]=(ArrayList<City>)tour2 .clone();
		for(int i=1;i<tour1.size();i++) {
			temp=tour1.get(0);
			tour1.remove(0);
			tour1.add(temp);
			half1[i]=(ArrayList<City>)tour1 .clone();
			
		}
		
		
		for(int i=0;i<tour2.size();i++) {
			temp=tour2.get(0);
			tour2.remove(0);
			tour2.add(temp);
			half2[i]=(ArrayList<City>)tour2.clone();
		}
		
		
		
		double min=Double.MAX_VALUE;
		Route smallestroute;
		ArrayList<City> shortestpath=new ArrayList();
		double tempshortest;	
		ArrayList<City> temppath=new ArrayList();
		Route temproute;
		
		for(int i=0;i<tour1.size();i++) {
			for(int j=0;j<tour2.size();j++) {
				temppath.removeAll(temppath);
				temppath.addAll(half1[i]);
				temppath.addAll(half2[j]);
				temproute=new Route(temppath);
				tempshortest=temproute.calculateTotalDistance();
				
				if(tempshortest<min) {
					min=tempshortest;
					shortestpath=(ArrayList<City>) temppath.clone();
				}
			}
			
			
			
		}
		for(int i=0;i<half2.length;i++) {
			Collections.reverse(half2[i]);
			
		}
		for(int i=0;i<tour1.size();i++) {
			for(int j=0;j<tour2.size();j++) {
				temppath.removeAll(temppath);
				temppath.addAll(half1[i]);
				temppath.addAll(half2[j]);
				temproute=new Route(temppath);
				tempshortest=temproute.calculateTotalDistance();
				
				if(tempshortest<min) {
					min=tempshortest;
					shortestpath=(ArrayList<City>) temppath.clone();
				}
			}
			
		}
		
		
		
		
		return shortestpath;
		
		
		
	}
		
		

   
  public  void mergeX(ArrayList<City> cities,int left, int m, int right) {
	  int a = m - left + 1; 
      int b = right - m; 

      City Left[] = new City [a]; 
      City Right[] = new City [b]; 

     
      for (int i=0; i<a; ++i) 
          Left[i] = cities.get(left + i); 
      for (int j=0; j<b; ++j) 
          Right[j] = cities.get(m + 1+ j); 


      int i = 0, j = 0; 

      int k = left; 
      while (i < a && j < b) 
      { 
          if (Left[i].getX() <= Right[j].getX()) 
          { 
              cities.set(k,Left[i]); 
              i++; 
          } 
          else
          { 
              cities.set(k,Right[j]); 
              j++; 
          } 
          k++; 
      } 

  
      while (i < a) 
      { 
          cities.set(k,Left[i]); 
          i++; 
          k++; 
      } 

      while (j < b) 
      { 
          cities.set(k,Right[j]); 
          j++; 
          k++; 
      } 
	  
  }
public  void sortX(ArrayList<City> cities,int left, int right) {
	 if (left < right) 
     { 
         
         int m = (left+right)/2; 

         sortX(cities, left, m); 
         sortX(cities , m+1, right); 

         mergeX(cities, left, m, right); 
     } 
	  
  }	
public  void sortY(ArrayList<City> cities,int left, int right) {
	 if (left < right) 
    { 
         
        int m = (left+right)/2; 

        sortY(cities, left, m); 
        sortY(cities , m+1, right); 

        mergeY(cities, left, m, right); 
    } 
	  
 }	
public  void mergeY(ArrayList<City> cities,int left, int m, int right) {
	int a = m - left + 1; 
    int b = right - m; 

    City Left[] = new City [a]; 
    City Right[] = new City [b]; 

   
    for (int i=0; i<a; ++i) 
        Left[i] = cities.get(left + i); 
    for (int j=0; j<b; ++j) 
    	Right[j] = cities.get(m + 1+ j); 
    

    int i = 0, j = 0; 

    int k = left; 
    while (i < a && j < b) 
    { 
        if (Left[i].getY() <= Right[j].getY()) 
        { 
            cities.set(k,Left[i]); 
            i++; 
        } 
        else
        { 
            cities.set(k,Right[j]); 
            j++; 
        } 
        k++; 
    } 

    while (i < a) 
    { 
        cities.set(k,Left[i]); 
        i++; 
        k++; 
    } 

    while (j < b) 
    { 
        cities.set(k,Right[j]); 
        j++; 
        k++; 
    } 
	  
}


public int factorial(int n){    
	  if (n == 0)    
	    return 1;    
	  else    
	    return(n * factorial(n-1));    
	 } 

}
