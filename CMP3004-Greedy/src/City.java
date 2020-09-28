import java.lang.Math; 
public class City {
private int citynumber;
private int x;
private int y;
private int citysegmentnumber;
public City(int citynumber, int x, int y) {
	super();
	this.citynumber = citynumber;
	this.x = x;
	this.y = y;
}

public int getCitysegmentnumber() {
	return citysegmentnumber;
}

public void setCitysegmentnumber(int citysegmentnumber) {
	this.citysegmentnumber = citysegmentnumber;
}

public int getCitynumber() {return citynumber;}

public void setCitynumber(int citynumber) {this.citynumber = citynumber;}

public int getX() {return x;}

public void setX(int x) {this.x = x;}

public int getY() {return y;}

public void setY(int y) {this.y = y;}

public double distance(City city) {
	double deltax=this.x-city.getX();
	double deltay=this.y-city.getY();
	double result=Math.sqrt(deltax*deltax+deltay*deltay);
return result;	
}
}


