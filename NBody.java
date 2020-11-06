import java.util.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.io.IOException;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class NBody extends JPanel implements ActionListener{
	private String name;
	private double mass;
	private double xCoord;
	private double yCoord;
	private double xVelocity;
	private double yVelocity;
	private int bodySize;
	private List<String[]> info;
	private List<CelestialBody> newList;
	private double scale;
	private double xforce;
	private	double yforce;
	private double gravity=6.67*Math.pow(10,-11);
	
	//timer repaints planets
	Timer tm= new Timer(0, this);
	//initializes the shapes onto the Jframe
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		for(int i = 0;i<newList.size();i++){
			CelestialBody holder = newList.get(i);
			g.setColor(Color.BLUE);
			g.fillOval((int)holder.getXPos(),(int)holder.getYPos(),holder.bodySize(),holder.bodySize());
		}
		tm.start();
	}

	//Changes x and y coordinates of planet using Newton's Gravity equation
	public void actionPerformed(ActionEvent e){
		//loop gets force exprienced on each planet to other on the frame
		for(int i = 0; i<newList.size(); i++){
			// changes in velocity
			double xVelocityC = 0.0;
			double yVelocityC = 0.0;
			CelestialBody planet1 = newList.get(i);
			for(int m = 0; m<newList.size(); m++){
				if(i != m){
					CelestialBody planet2= newList.get(m);
					double xDist= findXDistance(planet1,planet2);
					double yDist= findYDistance(planet1,planet2);
					double zDist= findZDistance(xDist,yDist);

					//Does the math for x force and y force but will be same since hypotnuses distacne will be used for radius
					double xForce= gravity*((planet1.getMass()*planet2.getMass())/Math.pow(xDist,2));
					double yForce= gravity*((planet1.getMass()*planet2.getMass())/Math.pow(yDist,2));
					//using xDist, yDist or zDist gives same results

					if(planet1.getXPos()-planet2.getXPos()==0){
						xForce = 0.0;
					}
					if(planet1.getXPos()<planet2.getXPos()){
						xVelocityC -= xForce;
					}else{
						xVelocityC += xForce;
					}
					if(planet1.getYPos()-planet2.getYPos()==0){
						yForce = 0.0;
					}
					if(planet1.getYPos()<planet2.getYPos()){
						yVelocityC -= yForce;
					}else{
						yVelocityC += yForce;
					}
				}
			}
			//planet1.setXVelocity and setY Velocity cannot be removed becuase it won't update without it
			System.out.println(planet1.getXVelocity());
			planet1.setXVelocity(planet1.getXVelocity() + (xVelocityC/scale)/planet1.getMass());
			System.out.println(planet1.getYVelocity());
			planet1.setYVelocity(planet1.getYVelocity() + (yVelocityC/scale)/planet1.getMass());
			System.out.println("Origional X coordinate:");
			System.out.println(planet1.getXPos());

			//the planet1.getXPos()+ and getYPos()+ can be removed
			
			planet1.setxCoord(planet1.getXPos() + planet1.getXVelocity());
			System.out.println("X place Changes to:");
			System.out.println(planet1.getXPos());

			System.out.println("Origional Y Coordinate:");
			System.out.println(planet1.getYPos());
			planet1.setyCoord(planet1.getYPos() + planet1.getYVelocity());
			System.out.println("New Y Coordinate:");
			System.out.println(planet1.getYPos());
		}
		repaint();
	}

	//finds the x and y distance between two planets 
	public double findXDistance(CelestialBody b1,CelestialBody b2){
		return (b1.getXPos() - b2.getXPos())*scale;
	}

	public double findYDistance(CelestialBody b1,CelestialBody b2){
		return (b1.getYPos() - b2.getYPos())*scale;
	}

	//find hypotenuse distance to be used in Newton's gravitation equation since that is correct radius distance
	public double findZDistance(double distance1, double distance2){
		double zDistance = Math.pow(distance1,2)+Math.pow(distance2,2);
		zDistance=Math.sqrt(zDistance);
		return zDistance;
	}
	public void NBodyCreator(String fileName) throws IOException{
		String fileInput = fileName;
		info = new ArrayList<String[]>();
		try{
			BufferedReader read = new BufferedReader(new FileReader(fileInput));
			String line = "";
			while((line = read.readLine())!= null){
				info.add(line.split(","));
			}
		}catch(Exception ex){
			System.out.println("File Not Found");
		}
		System.out.println(info.get(0)[0]);

		//checks if first line states make LL or Arraylist
		if(info.get(0)[0].equals("ArrayList")){
			System.out.println("Making ArrayList");
			newList = new ArrayList<CelestialBody>();
		}
		if(info.get(0)[0].equals("LinkedList")){
			System.out.println("Making LinkedList");
			newList = new LinkedList<CelestialBody>();
		}
		//Set constant scale to second item in file
		scale = Double.parseDouble(info.get(1)[0]);
		//Planets are created after reading the file
		for(int i = 2; i<info.size(); i++){
			name = info.get(i)[0];
			mass = Double.parseDouble(info.get(i)[1]);
			xCoord = Integer.parseInt(info.get(i)[2]);
			yCoord = Integer.parseInt(info.get(i)[3]);
			xVelocity = Double.parseDouble(info.get(i)[4]);
			yVelocity = Double.parseDouble(info.get(i)[5]);
			bodySize = Integer.parseInt(info.get(i)[6]);
			CelestialBody planet = new CelestialBody(name,mass,xCoord,yCoord,xVelocity,yVelocity,bodySize);
			newList.add(planet);
		}
	}

	public static void main(String[] args) throws IOException{
		String fileName="nbody_input.txt";
		NBodies t = new NBodies();
		t.NBodyCreator(fileName);
		JFrame jf = new JFrame();
		jf.setTitle("Canvas");
		jf.setSize(768,768);
		jf.add(t);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}