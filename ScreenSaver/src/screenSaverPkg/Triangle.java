/*Triangle class file.
 * Extends Shapes. 
 * 
 * Contains Triangle version
 * of Square methods.
 * See Square for details. 
 * 
 */
package screenSaverPkg;


import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import java.awt.geom.Area;


public class Triangle extends Shapes {
	
	
	public void setPoints(int x1, int x2, int x3, int y1, int y2, int y3){
		this.addToXPoints(x1);
		this.addToXPoints(x2);
		this.addToXPoints(x3);
		this.addToYPoints(y1); 
		this.addToYPoints(y2);
		this.addToYPoints(y3); 
	}
	

	public void render(Graphics2D g2, Color c, int[] x, int[] y){
		g2.setColor(c);
		g2.fillPolygon(x, y, 3);
		g2.setColor(Color.BLACK);
		g2.drawPolygon(x, y, 3);
		this.shape = new Polygon(x, y, 3);
		setArea();
		this.rendered=true;
	}
	
	public void adjust(int[] xPointsArr, int[] yPointsArr, Rectangle bound){
		int[] xPointsTri = xPointsArr, yPointsTri = yPointsArr;

		Rectangle bounds = bound;
		for(int x = 0; x<=xPointsTri.length-1; x++){	
			xPointsTri[x] = xPointsTri[x]+dx;
			yPointsTri[x] = yPointsTri[x]+dy;
		}
		for(int x=0; x<=xPointsTri.length-1; x++){

					if(xPointsTri[x]<=0){
						dx = -dx;
						break;
					}
	
					if(yPointsTri[x]<=0){
						dy = -dy;
						break;
					}
					
					if(xPointsTri[x]<=0 && yPointsTri[x]<=0){
						dx = -dx;
						dy = -dy;
						break;
					}
					
					if(xPointsTri[x]>=bounds.getWidth()){
						dx = -dx;
						break;
					}
					if(yPointsTri[x]>=bounds.getHeight()){
						dy = -dy;
						break;
					}

		}
	}
	public Triangle(int x1, int x2, int x3, int y1, int y2, int y3){

		setPoints(x1, x2, x3, y1, y2, y3);
		this.setXPointsArray();
		this.setYPointsArray();

		setDXDY();
		this.shape = new Polygon(this.getXPointsArray(), this.getYPointsArray(), 3);
		setArea();
	}

}
