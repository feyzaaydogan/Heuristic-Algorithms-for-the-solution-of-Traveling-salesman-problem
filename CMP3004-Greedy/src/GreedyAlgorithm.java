import java.util.ArrayList;
import java.util.Collections;


public class GreedyAlgorithm {
	private int n;
	ArrayList<City> CityList=new ArrayList<City>();
	Segment [] segmentArrayList ;
	
	public GreedyAlgorithm(ArrayList<City> cityList){
		this.CityList=cityList;       
	}
	public  Combination[] findcombination(){
		int numberofcombination=(CityList.size()*(CityList.size()-1))/2;
		Combination [] com=new Combination[numberofcombination];

		int n=0;

		for(int i=0;i<CityList.size();i++){
			for(int j=i+1;j<CityList.size();j++){
				
				com[n]=new Combination();
				com[n].setFirstcity(CityList.get(i));
				com[n].setSecondcity(CityList.get(j));
				
				n++;
			}
			
		}
		
		return com;
		
	}
	public void calculatelink(Combination [] c){
		for(int i=0;i<c.length;i++){
			c[i].setDistance(c[i].getFirstcity().distance(c[i].getSecondcity()));
			
			
		}
		
		
	}
	
	public  void sortcombination(Combination[] c,int left, int right) {
		 if (left < right) 
	     { 
	         int m = (left+right)/2; 
	         sortcombination(c, left, m); 
	         sortcombination(c , m+1, right); 
	         mergecombination(c, left, m, right); 
	     } 
		  
	  }
	
	public  void mergecombination(Combination[] c,int left, int m, int right) {
		  int a = m - left + 1; 
	      int b = right - m; 

	    
	      Combination Left[] = new Combination[a]; 
	      Combination Right[] = new Combination [b]; 

	     
	      for (int i=0; i<a; ++i) {
	          Left[i] = c[left + i];
	      }
	      for (int j=0; j<b; ++j) {
	          Right[j] = c[m + 1+ j]; 
	      }


	     
	      int i = 0, j = 0; 

	      
	      int k = left; 
	      while (i < a && j < b) 
	      { 
	          if (Left[i].getDistance() <= Right[j].getDistance()) 
	          { 
	        	  c[k]=Left[i];
	              
	              i++; 
	          } 
	          else
	          { 
	        	  c[k]=Right[j];
	              j++; 
	          } 
	          k++; 
	      } 

	  
	      while (i < a) 
	      { 
	    	  c[k]=Left[i];
	          i++; 
	          k++; 
	      } 

	      while (j < b) 
	      { 
	    	  c[k]=Right[j];
	          j++; 
	          k++; 
	      } 
		  
	  }
	
	
	public ArrayList<City> result(Combination[] c){
		
		
		
		n=CityList.size();
		
		segmentArrayList = new Segment[n];
		
		for(int i = 0;i<CityList.size();i++){
			segmentArrayList[i]=new Segment(i);
			segmentArrayList[i].addCity(CityList.get(i));
			CityList.get(i).setCitysegmentnumber(i);
		}
		
		
		int snumber;
		int number;
	
        boolean flag=true;
        ArrayList <City> a=new ArrayList<City>();

	
			
			for(int i=0;i<c.length;i++){
				
				snumber=c[i].getFirstcity().getCitysegmentnumber();
				number=c[i].getSecondcity().getCitysegmentnumber();
				
		if(snumber!=number){
			if(segmentArrayList[snumber].end().getCitynumber()==c[i].getFirstcity().getCitynumber()){
			if(segmentArrayList[number].front().getCitynumber()==c[i].getSecondcity().getCitynumber()){
				for(int j=0;j<segmentArrayList[number].segmentCities.size();j++){
					segmentArrayList[snumber].addCity(segmentArrayList[number].segmentCities.get(j));
					segmentArrayList[number].segmentCities.get(j).setCitysegmentnumber(snumber);
					
				}
				segmentArrayList[number].segmentCities.clear();
				
			}
			else if(segmentArrayList[number].end().getCitynumber()==c[i].getSecondcity().getCitynumber()){
				Collections.reverse(segmentArrayList[number].segmentCities);		
				for(int j=0;j<segmentArrayList[number].segmentCities.size();j++){
					segmentArrayList[snumber].addCity(segmentArrayList[number].segmentCities.get(j));	
					segmentArrayList[number].segmentCities.get(j).setCitysegmentnumber(snumber);
				}
				segmentArrayList[number].segmentCities.clear();
			}
			
			}
			
	
			
			else if(segmentArrayList[snumber].front().getCitynumber()==c[i].getFirstcity().getCitynumber()){ 
				
				if(segmentArrayList[number].front().getCitynumber()==c[i].getSecondcity().getCitynumber()){
					Collections.reverse(segmentArrayList[snumber].segmentCities);
					
					for(int j=0;j<segmentArrayList[number].segmentCities.size();j++){
						segmentArrayList[snumber].addCity(segmentArrayList[number].segmentCities.get(j));
						segmentArrayList[number].segmentCities.get(j).setCitysegmentnumber(snumber);
						
					}
					segmentArrayList[number].segmentCities.clear();
					
				}
				
				else if(segmentArrayList[number].end().getCitynumber()==c[i].getSecondcity().getCitynumber()){
					Collections.reverse(segmentArrayList[snumber].segmentCities);
					Collections.reverse(segmentArrayList[number].segmentCities);
					
					
					for(int j=0;j<segmentArrayList[number].segmentCities.size();j++){
						segmentArrayList[snumber].addCity(segmentArrayList[number].segmentCities.get(j));
						segmentArrayList[number].segmentCities.get(j).setCitysegmentnumber(snumber);
						
					}
					
					segmentArrayList[number].segmentCities.clear();
					
				}
				
			}
		
			
			for(int j=0;j<segmentArrayList.length;j++){
				
			if(segmentArrayList[j].segmentCities.size()==CityList.size()){
				a=segmentArrayList[j].segmentCities;
				flag=false;
				
			}
		}
			if(flag==false){
				break;
			}
			
			
			}
			}
			
			
		
		
		return a;
		
		
			
		
	}
	
	

}