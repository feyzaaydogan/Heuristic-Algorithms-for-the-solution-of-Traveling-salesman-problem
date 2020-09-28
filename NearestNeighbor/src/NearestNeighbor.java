import java.util.ArrayList;
import java.util.Iterator;
public class NearestNeighbor {
public Route findRoute(ArrayList<City> cities,int i) {
	ArrayList<City> shortestRouteCities=new ArrayList<City>(cities.size());
	City city=cities.get(i);
	shortestRouteCities.add(city);
	cities.remove(city);
	
	while(cities.size()>0) {
		city=getNextCity(cities,city);
		shortestRouteCities.add(city);
		cities.remove(city);
	}
	
	return new Route(shortestRouteCities);
	
}


private City getNextCity(ArrayList<City> cities ,City city) {
	City result=null;
	City temp=null;
	double min=0;
	double newdistance;
	Iterator itr=cities.iterator();
	if(itr.hasNext()) {
		temp=(City)itr.next();
		min=city.distance(temp);
		result=temp;
		
	}
	while(itr.hasNext()) {
		temp=(City)itr.next();
		newdistance=city.distance(temp);
		if(newdistance<min) {
			min=newdistance;
			result=temp;
		}
	}
	return result;
	
}
}



