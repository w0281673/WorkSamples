/*Abstract Shapes class.
 * All my shapes extend this.
 * 
 */
package screenSaverPkg;

import java.awt.GradientPaint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Random;
public abstract class Shapes {
	
	private ArrayList<Integer> xPoints = new ArrayList<Integer>(); //ArrayList to hold xPoints
	private ArrayList<Integer> yPoints = new ArrayList<Integer>(); //ArrayList to hold yPoints
	private int[] xPointsArr, yPointsArr; //Arrays which will be resized according to ArrayList sizes
										  //and then use the points in rendering methods.
	Area shArea; //Area object for collision detection
	Shape shape; //Shape object to pass to shArea
	Random rand = new Random(); //RNG to set directions
	
	int dx, dy; //ints to hold a shape's direction
	boolean rendered = false; //boolean to determine if shape has been rendered
							  //for use in safe rendering solution
	public void setDXDY(){
		this.dx = -2 + rand.nextInt(4);
		if(dx==0)
			dx++;
		this.dy = -2 + rand.nextInt(4);
		if(dy==0)
			dy++;
	}
	public void addToXPoints(int x){
		this.xPoints.add(x);
	}
	public void addToYPoints(int y){
		this.yPoints.add(y);
	}
	public ArrayList<Integer> getXPoints(){
		return xPoints;
	}
	public ArrayList<Integer> getYPoints(){
		return yPoints;
	}
	public void setXPointsArray(){
		this.xPointsArr = new int[xPoints.size()];
		for(int x=0; x<=xPointsArr.length-1; x++){
			this.xPointsArr[x]=xPoints.get(x);
		}
		
	}
	public void setYPointsArray(){
		this.yPointsArr = new int[yPoints.size()];
		for(int y=0; y<=yPointsArr.length-1; y++){
			this.yPointsArr[y]=yPoints.get(y);
		}
		
	}
	public void setArea(){
		this.shArea = new Area(shape);
	}
	public void modifyXArray(int x, int num){
		this.xPointsArr[x] = num;
	}
	public void modifyYArray(int x, int num){
		this.yPointsArr[x] = num;
	}
	public int[] getXPointsArray(){
		return xPointsArr;
	}
	public int[] getYPointsArray(){
		return yPointsArr;
	}
	public void adjust(int[] xPointsArr, int[] yPointsArr, Rectangle bound){
		
	}
	//overridden by Circle getRadius()
	public int getRadius(){
		return 0;
	}
	
	public void render(Graphics2D g2, Color c, int[] x, int[] y){
		//overriden by Triangle render()
	}
	
	public void render(Graphics2D g2d, Color cirCol, int[] xPointsArray, int[] yPointsArray, int radius) {
		//overridden by Circle render()
	}
	
	public void render(Graphics2D g2d, GeneralPath gp1, Color staCol, int[] xPointsArray, int[] yPointsArray) {
		//overridden by GPStar render()
	}
	
	public void render(Graphics2D g2d, GradientPaint greenToWhite, int[] xPointsArray, int[] yPointsArray) {
		//overridden by Square render()
	}

}
