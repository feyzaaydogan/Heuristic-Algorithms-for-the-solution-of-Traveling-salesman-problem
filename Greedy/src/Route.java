import java.util.ArrayList;
import java.util.*;  

public class Route {
 ArrayList<City> cities=new ArrayList<City>();
public Route(ArrayList<City> cities) {
	this.cities.addAll(cities);
	
	
}
public ArrayList<City> getCities(){
	return cities;
	}
public void print() {
Iterator<City> itr=cities.iterator();
while(itr.hasNext()) {
	City temp=null;
	temp=(City)itr.next();
	System.out.print(temp.getCitynumber()+"->");
}
System.out.println(cities.get(0).getCitynumber());
}
public double calculateTotalDistance() {
	
	 Iterator<City> itr=cities.iterator();
	 City st = null;
	 City temp=null;
	 double result=0;
	 if(itr.hasNext()) {
		 
		st=(City)itr.next();
		}
	 
	 while(itr.hasNext()) {
		 temp=(City)itr.next();
		 result=result+st.distance(temp);
		 st=temp;
	 
	 }
	 result=result+(cities.get(cities.size()-1)).distance(cities.get(0));
	 return result;
	
}

}