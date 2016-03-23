/*Square class file. 
 * Extends Shapes.
 * 
 * setArea() method creates a 
 * new Area for this shape, to
 * be used in collision tests.
 * 
 * setPoints() adds the points to
 * an arraylist.
 * 
 * render() overrides Shapes' render(),
 * and takes care of rendering the square.
 * It also calls setArea() to set the Area
 * object and sets the rendered boolean to 
 * true.
 * 
 * adjust() changes the square's direction
 * if it hits an edge.
 * 
 * The Square() constructor sets the points
 * arraylist, the x and y arrays, calls
 * setDXDY() to set a random direction, creates
 * a new Shape object to pass to setArea,
 * and calls setArea to set an initial Area object.
 */
package screenSaverPkg;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.geom.Area;


public class Square extends Shapes{


	public void setPoints(int x1, int x2, int y1, int y2){
		this.addToXPoints(x1);
		this.addToXPoints(x2);
		this.addToYPoints(y1);
		this.addToYPoints(y2);
	}
	public void render(Graphics2D g2, GradientPaint grp, int[] x, int[] y){
		g2.setPaint(grp);
		g2.fillRect(x[0], y[0], x[1]-x[0], y[1]-y[0]);
		g2.setColor(Color.BLACK);
		g2.drawRect(x[0], y[0], x[1]-x[0], y[1]-y[0]);
		this.shape = new Rectangle(x[0], y[0], x[1]-x[0], y[1]-y[0]);
		setArea();
		this.rendered=true;
	}
	public void adjust(int[] xPointsArr, int[] yPointsArr, Rectangle bound){
		int[] xPointsSqu = xPointsArr, yPointsSqu = yPointsArr;
		
		Rectangle bounds = bound;
		for(int x = 0; x<=xPointsSqu.length-1; x++){	
			modifyXArray(x, xPointsSqu[x]+dx);
			modifyYArray(x, yPointsSqu[x]+dy);
		}
		for(int x=0; x<=xPointsSqu.length-1; x++){

					if(this.getXPointsArray()[x]<=0){
						dx = -dx;
						break;
					}
		
					if(this.getYPointsArray()[x]<=0){
						dy = -dy;
						break;
					}
						
					if(this.getXPointsArray()[x]<=0 && yPointsSqu[x]<=0){
						dx = -dx;
						dy = -dy;
					}
						
					if(this.getXPointsArray()[x]>=bounds.getWidth()){
						dx = -dx;
						break;
					}
					if(this.getYPointsArray()[x]>=bounds.getHeight()){
						dy = -dy;
						break;
					}

			}
	}
	public Square(int x1, int x2, int y1, int y2){

		this.setPoints(x1, x2, y1, y2);
		this.setXPointsArray();
		this.setYPointsArray();
		setDXDY();
		this.shape = new Rectangle(x1, y1, x2-x1, y2-y1);
		setArea();
	}
}

