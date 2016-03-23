/*Circle class.
 * Extends Shapes.
 * 
 * Contains Circle version
 * of Square methods, see
 * Square for details.
 * 
 * Also contains:
 * getX1() to get the center
 * x-coordinate.
 * getY1() to get the center
 * y-coordinate.
 * getRadius() to get the 
 * radius.
 * 
 */
package screenSaverPkg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Circle extends Shapes{

	private int x1, y1, radius;
	
	public Circle(int radius, int x1, int y1) {
		this.radius=radius;
		this.setPoints(x1, y1);
		this.setXPointsArray();
		this.setYPointsArray();
		setDXDY();
		this.shape = new Ellipse2D.Double(x1-radius/2, y1-radius/2, radius, radius);
		setArea();
	}

	public int getX1(){
		return x1;
	}
	public int getY1(){
		return y1;
	}
	public int getRadius(){
		return radius;
	}
	public void setPoints(int x1, int y1) {
		this.addToXPoints(x1);
		this.addToYPoints(y1);
		
	}
	public void render(Graphics2D g2, Color c, int[] x, int[] y, int r){
		g2.setColor(c);
		g2.fillOval(x[0]-r/2, y[0]-r/2, r, r);
		g2.setColor(Color.BLACK);
		g2.drawOval(x[0]-r/2, y[0]-r/2, r, r);
		this.shape = new Ellipse2D.Double(x[0]-r/2, y[0]-r/2, r, r);
		setArea();
		this.rendered=true;
	}
	public void adjust(int[] xPointsArr, int[] yPointsArr, Rectangle bound){
		int[] xPointsCir = xPointsArr, yPointsCir = yPointsArr;
		
		Rectangle bounds = bound;
		for(int x = 0; x<=xPointsCir.length-1; x++){	
			modifyXArray(x, xPointsCir[x]+dx);
			modifyYArray(x, yPointsCir[x]+dy);
			
		}
		for(int x=0; x<=xPointsCir.length-1; x++){

					if(this.getXPointsArray()[x]-this.getRadius()/2<=0){
						dx = -dx;
						break;
					}
	
					if(this.getYPointsArray()[x]-this.getRadius()/2<=0){
						dy = -dy;
						break;
					}
					
					if(this.getXPointsArray()[x]<=0 && this.getYPointsArray()[x]<=0){
						dx = -dx;
						dy = -dy;
						break;
					}
					if(this.getXPointsArray()[x]>=bounds.getHeight() && this.getYPointsArray()[x]>=bounds.getHeight()){
						dx = -dx;
						dy = -dy;
						break;
					}
					if(this.getXPointsArray()[x]+this.getRadius()/2>=bounds.getWidth()){
						dx = -dx;
						break;
					}
					if(this.getYPointsArray()[x]+this.getRadius()/2>=bounds.getHeight()){
						dy = -dy;
						break;
					}

		}
	}
}
