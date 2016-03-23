/*Screen Saver
 * by: Isaac Gannon
 * 
 * 
 * DrawPanel extends JPanel. The four
 * initial shapes are created in the createShapes() method.
 * The paintComponents() method overrides paintComponents().
 * It handles rendering of shapes by calling their render()
 * methods. It also tries to safely add shapes to the panel
 * when the mouse is clicked. 
 * 
 * The addRandomShape() method is called on mouse click. It
 * adds a triangle, circle, square, or star based on a RNG. 
 * All shapes except stars can be different sizes, triangles
 * can also be different shapes, stars flash different colors.
 * 
 * The shapeCollision() and renderCollision() methods attempt
 * bounce shapes on collision, and stop shapes from rendering 
 * over another shape, respectively. 
 * 
 * The adjust() method here gets the bounds of the panel and
 * calls the shape's adjust() method.
 * 
 * The move() method calls adjust(), to set the changes in the 
 * shapes coordinates, calls shapeCollision() to check for 
 * collisions, and calls repaint() to render changes.
 * 
 * The DrawPanel() constructor adds a mouse listener.
 * 
 * NOTE: If a shape doesn't appear on mouse click, it is waiting
 * to render safely.
 * Also: Shapes can stick sometimes, but usually will fix themselves.
 */


package screenSaverPkg;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel {
	Random rand = new Random();
	Timer timer;
	Rectangle bounds;
	ArrayList<Shapes> s = new ArrayList<Shapes>();
	Triangle t;
	Circle c;
	Square sq;
	GPStar st;
	GeneralPath gp1 = new GeneralPath();
	
	Color triCol, cirCol, squCol, staCol;
	
	int offset=250;
	
	public void createShapes(){
		Random rand = new Random();
		int mod=rand.nextInt(101);
	
				t = new Triangle(20+offset, 40+mod+offset, 30+(mod/2)+offset, 20+offset, 20+offset, 40+mod+offset);
				triCol = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
				s.add(t);
				
				c = new Circle(100+mod, 100+mod+offset, 100+mod);
				cirCol = new Color(0+rand.nextInt(256), 0+rand.nextInt(256), 0+rand.nextInt(256));
				s.add(c);
	
				
				sq = new Square(100+offset, 150+mod+offset, 110+offset, 160+mod+offset);
				squCol = new Color(0+rand.nextInt(256), 0+rand.nextInt(256), 0+rand.nextInt(256));
				s.add(sq);
				
				st = new GPStar();
				staCol = new Color(0+rand.nextInt(256), 0+rand.nextInt(256), 0+rand.nextInt(256));
				s.add(st);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		boolean collision = false;
		Random r = new Random();
		
		//attempt at a safe rendering solution,
		//to stop shapes being rendered over 
		//each other.
		for(Shapes sh : s){
			for(int x = 0; x<s.size(); x++)
				if(!(sh.equals(s.get(x))) || x==s.size()-1){
					for(int y = 0; y<s.size(); y++){
						if(!sh.equals(s.get(y)))
							collision = renderCollision(sh, s.get(y));
					}
					if(collision==false){
						try{
							try{
								
								sh.render(g2d, triCol, sh.getXPointsArray(), sh.getYPointsArray());
							
							}catch(NullPointerException npe){
							
							}
							try{
								
								sh.render(g2d, cirCol, sh.getXPointsArray(), sh.getYPointsArray(), sh.getRadius());
								
							}catch(NullPointerException|IndexOutOfBoundsException ie){
								
							}
							try{
								GradientPaint greenToWhite = new GradientPaint(50.0f, 50.0f, Color.GREEN, 75.0f, 75.0f, Color.WHITE, true);
								g2d.setPaint(greenToWhite);
								sh.render(g2d, greenToWhite, sh.getXPointsArray(), sh.getYPointsArray());
								
							}catch(NullPointerException|IndexOutOfBoundsException ie){
								
							}
							try{
								staCol = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
								sh.render(g2d, gp1, staCol, sh.getXPointsArray(), sh.getYPointsArray());
									
									
							}catch(NullPointerException|IndexOutOfBoundsException ie){
								
							}
							sh.rendered = true;
						}catch(NullPointerException npe){
							
						}
					}
					else
						sh.rendered = false;
				}
			
		}
		g2d.dispose();
	}
	
	public void addRandomShape(){
		Random r = new Random();
		int rand = r.nextInt(4);
		
		switch(rand){
			case 0:
				Triangle t = new Triangle(r.nextInt(51)+30, r.nextInt(51)+30, r.nextInt(51)+30, r.nextInt(51)+30, r.nextInt(51)+30, r.nextInt(51)+30);
				s.add(t);
				triCol = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
				break;
			case 1:
				Circle c = new Circle(r.nextInt(51)+30, r.nextInt(51)+30, r.nextInt(51)+30);
				s.add(c);
				break;
			case 2:
				Square sq = new Square(r.nextInt(51)+50, r.nextInt(51)+80, r.nextInt(51)+130, r.nextInt(51)+160);
				s.add(sq);
				break;
			case 3:
				GPStar st = new GPStar();
				s.add(st);
				break;
		}
		
	}
	
	public void shapeCollision(){

		for(int x=0; x<s.size(); x++)
			for(int y=x+1; y<s.size(); y++){
					
						Area collide1 = new Area(s.get(x).shArea);
						collide1.subtract(s.get(y).shArea);
						if(!collide1.equals(s.get(x).shArea) && s.get(y).rendered==true){
							s.get(x).dx = -(s.get(x).dx);
							s.get(x).dy = -(s.get(x).dy);
							
						}
						Area collide2 = new Area(s.get(y).shArea);
						collide2.subtract(s.get(x).shArea);
						if(!collide2.equals(s.get(y).shArea) && s.get(x).rendered==true){
							s.get(y).dx = -(s.get(y).dx);
							s.get(y).dy = -(s.get(y).dy);
						}
					}
						
	}
	
	public boolean renderCollision(Shapes s, Shapes h){
		boolean collision = false;
					try{
						Area collide1 = new Area(s.shArea);
						collide1.subtract(h.shArea);
						if(!collide1.equals(s.shArea) && s.rendered==false){
							
							collision = true;
						}
					
					}catch(NullPointerException npe){
						
					}
		return collision;
						
	}

	public void adjust() {
	
		bounds = new Rectangle(this.getBounds());
		for(Shapes sh : s){
			sh.adjust(sh.getXPointsArray(), sh.getYPointsArray(), bounds);
	
		}
	}
	
	public void move() {
		timer = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			
				shapeCollision();
				adjust();
				repaint();
				
			}
		});
		timer.start();
	}

	public DrawPanel() {
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRandomShape();
			}
		});
	
	}
}
