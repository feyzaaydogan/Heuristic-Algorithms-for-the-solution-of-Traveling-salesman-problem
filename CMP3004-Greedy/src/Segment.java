import java.util.ArrayList;


public class Segment {
	
	private int segmentnumber;
	
	
	ArrayList<City> segmentCities = new ArrayList<City>();
    
	public Segment(int segmentnumber){

		this.segmentnumber=segmentnumber;
	}
	
	public void addCity(City city){
		segmentCities.add(city);
	}
	
	public int getSegmentnumber() {
		return segmentnumber;
	}

	public City front(){
		return segmentCities.get(0);
		
	}
	public City end(){
		return segmentCities.get(segmentCities.size()-1);
		
	}
	
	
	
}