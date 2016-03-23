/*GPStar class.
 * Extends Shapes.
 * 
 * This renders a Star 
 * using a GeneralPath
 * and a specific set 
 * of points.
 * Otherwise, contains
 * GPStar version of Square
 * methods, see Square for
 * details.
 */
package screenSaverPkg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.geom.Area;

public class GPStar extends Shapes{

	private int x1, x2, x3, x4, x5, x6, x7, x8, x9, y1, y2, y3, y4, y5, y6;
	GeneralPath gp = new GeneralPath();

	public GPStar(){
		this.x1 = 0+50;
		this.x2 = 75+50;
		this.x3 = 100+50;
		this.x4 = 125+50;
		this.x5 = 200+50;
		this.x6 = 150+50;
		this.x7 = 160+50;
		this.x8 = 40+50;
		this.x9 = 50+50;
		this.y1 = 85+50;
		this.y2 = 75+50;
		this.y3 = 10+50;
		this.y4 = 125+50;
		this.y5 = 190+50;
		this.y6 = 150+50;
		this.setPoints();
		this.setXPointsArray();
		this.setYPointsArray();
		setDXDY();
		this.gp.moveTo(x1, y1);
		this.gp.lineTo(x2, y2);
		this.gp.lineTo(x3, y3);
		this.gp.lineTo(x4, y2);
		this.gp.lineTo(x5, y1);
		this.gp.lineTo(x6, y4);
		this.gp.lineTo(x7, y5);
		this.gp.lineTo(x3, y6);
		this.gp.lineTo(x8, y5);
		this.gp.lineTo(x9, y4);
		this.gp.lineTo(x1, y1);
		this.gp.closePath();
		this.shape = new GeneralPath(gp);
		setArea();
	}

	public void setPoints(){
		this.addToXPoints(x1);
		this.addToXPoints(x2);
		this.addToXPoints(x3);
		this.addToXPoints(x4);
		this.addToXPoints(x5);
		this.addToXPoints(x6);
		this.addToXPoints(x7);
		this.addToXPoints(x8);
		this.addToXPoints(x9);
		this.addToYPoints(y1);
		this.addToYPoints(y2);
		this.addToYPoints(y3);
		this.addToYPoints(y4);
		this.addToYPoints(y5);
		this.addToYPoints(y6);
		
	}
	public void render(Graphics2D g2, GeneralPath p, Color c, int[] x, int[] y){
		g2.setColor(c);
		p.moveTo(x[0], y[0]);
		p.lineTo(x[1], y[1]);
		p.lineTo(x[2], y[2]);
		p.lineTo(x[3], y[1]);
		p.lineTo(x[4], y[0]);
		p.lineTo(x[5], y[3]);
		p.lineTo(x[6], y[4]);
		p.lineTo(x[2], y[5]);
		p.lineTo(x[7], y[4]);
		p.lineTo(x[8], y[3]);
		p.lineTo(x[0], y[0]);
		p.closePath();
		g2.fill(p);
		g2.setColor(Color.BLACK);
		g2.draw(p);
		this.shape = new GeneralPath(p);
		setArea();
		p.reset();
		this.rendered=true;
	}
	public void adjust(int[] xPointsArr, int[] yPointsArr, Rectangle bound){
		int[] xPointsSta = xPointsArr, yPointsSta = yPointsArr;
		
		Rectangle bounds = bound;
		for(int x = 0; x<=xPointsSta.length-1; x++){
			modifyXArray(x, xPointsSta[x]+dx);
		}
		for(int y = 0; y<=yPointsSta.length-1; y++){
			modifyYArray(y, yPointsSta[y]+dy);
		}
		for(int x=0; x<=xPointsSta.length-1; x++){
			
			if(this.getXPointsArray()[x]<=0){
				dx = -dx;
				break;
			}
			if(this.getXPointsArray()[x]>=bounds.getWidth()){
				dx = -dx;
				break;
			}
		}
		for(int y=0; y<=yPointsSta.length-1; y++){
			
			if(this.getYPointsArray()[y]<=0){
				dy = -dy;
				break;
			}
			if(this.getYPointsArray()[y]>=bounds.getHeight()){
				dy = -dy;
				break;
			}
		}

	}
}
