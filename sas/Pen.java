//This needs to be in sas to access the Shapes base class
package sas;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import sas.Scene;
import sas.Shapes;

public class Pen extends Shapes {
	private List<Line2D.Double> lines;
	private double direction = 0.0;
	private boolean up = true;

	public Pen() {
		super(0, 0, 1, 1, Color.black);
		//Create dummy shape to prevent errors
		Rectangle2D.Double rect = new Rectangle2D.Double(this.getXPosition(), this.getYPosition(), 0, 0);
		this.setShape(rect);
		this.getScene().insert(this);
		lines = new List<Line2D.Double>();
	}

	@Override
	public Pen clone() {
		return null;
	}

	public void down() {
		this.up = false;
	}

	public void up() {
		this.up = true;
	}

	public boolean isDown() {
		return !this.up;
	}

	public void turnTo(double pAngle) {
		this.direction -= pAngle;
		while (this.direction < 0.0) {
			this.direction += 360.0;
		}
		while (this.direction >= 720.0) {
			this.direction -= 360.0;
		}
	}

	public void turnBy(double pAngle) {
		this.direction += pAngle;
		while (this.direction < 0.0) {
			this.direction += 360.0;
		}
		while (this.direction >= 720.0) {
			this.direction -= 360.0;
		}
	}

	public void moveTo(double pX, double pY) {
		if(!this.up)
			lines.append(new Line2D.Double(this.getXPosition(), this.getYPosition(), pX, pY));
		this.setPosition(pX, pY);
		this.getScene().repaint();
	}

	public void moveFor(double pDistance) {
		double w = this.direction * Math.PI / 180.0;
		double x = this.getXPosition() + pDistance * Math.cos(w);
		double y = this.getYPosition() - pDistance * Math.sin(w);

		if(!this.up)
			lines.append(new Line2D.Double(this.getXPosition(), this.getYPosition(), x, y));
		this.setPosition(x, y);
		this.getScene().repaint();
	}

	protected void zeichne(Graphics2D g2d) {
		g2d.setPaint(this.getColor());
		lines.toFirst();
		while(lines.hasAccess()) {
			g2d.draw(lines.getContent());
			lines.next();
		}
	}
}
