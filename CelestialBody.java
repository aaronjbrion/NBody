import java.util.*;

public class CelestialBody{
	private String name;
	private double mass;
	private double xCoord;
	private double yCoord;
	private double xVelocity;
	private double yVelocity;
	private int bodySize;
	public CelestialBody(String name, double mass, double xCoord, double yCoord, double xVelocity, double yVelocity, int size){
		this.name=name;
		this.mass=mass;
		this.xCoord=xCoord;
		this.yCoord=yCoord;
		this.xVelocity=xVelocity;
		this.yVelocity=yVelocity;
		this.bodySize=size;
	}
	public String giveName(){
		return this.name;
	}
	public double getXPos(){
		return this.xCoord;
	}
	public double getYPos(){
		return this.yCoord;
	}
	public int bodySize(){
		return this.bodySize;
	}

	public double getMass(){
		return	this.mass;
	}

	public double getXVelocity(){
		return this.xVelocity;

	}
	public double getYVelocity(){
		return this.yVelocity;
	}

	public void setxCoord(double position){
		this.xCoord=position;
	}

	public void setyCoord(double position){
		this.yCoord=position;
	}

	public void setXVelocity(double velocity){
		this.xVelocity=velocity;
	}
	public void setYVelocity(double	velocity){
		this.yVelocity=velocity;
	}
}