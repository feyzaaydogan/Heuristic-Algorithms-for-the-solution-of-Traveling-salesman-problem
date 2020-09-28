import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Visualition extends JFrame {
	
	ArrayList<City> Cities;
	
	public Visualition(ArrayList<City> cityArray){
		this.Cities = cityArray;
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		setSize(1800,900);
        panel.setAutoscrolls(true);
        
        Route x=new Route(Cities);
        
   
        JLabel lblTotalDistance = new JLabel("Total Distance= "+Double.toString(x.calculateTotalDistance()));
        lblTotalDistance.setBounds(1500,100, 300, 20);
        panel.add(lblTotalDistance);		
	}
	
	 public void paint(Graphics g) {
	        super.paint(g);  // fixes the immediate problem.
	        Graphics2D g2 = (Graphics2D) g;
	       
	        for(int i = 0;i<Cities.size()-1;i++){
	        	
	        	Graphics2D g3 = (Graphics2D) g;
	        	g2.setColor(Color.blue);
	        	g2.fillOval(Cities.get(i).getX()/8+93, Cities.get(i).getY()/8+93, 15, 15);
	        	g2.setColor(Color.black);
	 	        Line2D lin = new Line2D.Float(Cities.get(i).getX()/8+100, Cities.get(i).getY()/8+100
	 	        		, Cities.get(i+1).getX()/8+100, Cities.get(i+1).getY()/8+100);
	 	        g2.draw(lin);
	        }
	      
	        g2.setColor(Color.blue);
        	g2.fillOval(Cities.get(Cities.size()-1).getX()/8+93, Cities.get(Cities.size()-1).getY()/8+93, 15, 15);
        	g2.setColor(Color.black);
 	        Line2D lin = new Line2D.Float(Cities.get(0).getX()/8+100, Cities.get(0).getY()/8+100
 	        		, Cities.get(Cities.size()-1).getX()/8+100, Cities.get(Cities.size()-1).getY()/8+100);
 	        g2.draw(lin);
	        
	        g2.setColor(Color.red);
	        g2.fillOval(Cities.get(0).getX()/8+93, Cities.get(0).getY()/8+93, 15, 15);
	       
	    }

}