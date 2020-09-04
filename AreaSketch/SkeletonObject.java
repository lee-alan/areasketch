/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import javaapplication2.NewJFrame.StartPoint;

/**
 *
 * @author Alan Lee 
 */
public abstract class SkeletonObject { // the base class defining ALL structure types.  If future structures do not follow this format, a new class must be made, but at t.o.c this class encumpuses all characteristics of the required structures.

	/**
	 * Line coordinates
	 */
	protected int fx, fy;

	protected NewJFrame frame;

	protected final int SNAP_RATIO = 20; // The snap radius in which we will start the line snap.

	protected ArrayList<Point2D.Float> points = new ArrayList<Point2D.Float>(); // an array of Point2D's, points which I have defined.  They will be for the vertexs' of each line and be used for things like snapping, traversals, area calculations.
	protected ArrayList<LineObject> lines = new ArrayList<LineObject>(); // an array of LineObject's, Lines as I have defined them.
	protected boolean inDrawProgress = false;  
	protected boolean drawFromCurrentNode = false; 

	protected float area = -1; // this is the default value I have set area to.  It is to let me know whether or not a structure is closed, if it is closed then area cannot be -1, obviously, but if it is -1 then we must call calculate area.
	protected StartPoint currentPoint; 
	protected StartPoint startPoint;
	public float newMouseX;  // second mouse X, current location
	public float newMouseY;  // second mouse Y, current location
	public Color lineColor;
	protected boolean shapeClosed;  // is the shape "closed" or not, this will affect whether or not you can edit that structure further.
	protected StartPoint realStartPoint;

	protected SkeletonObject(NewJFrame frame, StartPoint start) {  // construct each structure.  A new startpoint for drawing is created as well as the name of the structure.
		this.frame = frame;
		this.startPoint = start;
		this.currentPoint = start;
		this.shapeClosed = false;
		this.points.add(new Point2D.Float((float) start.xOrg, (float) start.yOrg));
		String name = this.getName();
		this.frame.g2.drawString(name, (float) start.xOrg, (float) start.yOrg);
	}

	/**
	 * Checks if (x, y) coordinate is close to the starting node.  Then we will know whether or not to let you draw as you can only draw from designated vertice points.
	 * 
	 * 
	 * 
	 * True if (x, y) is within the range of the starting node
	 */
	public boolean closeToCurrentPoint(float x, float y) {
		if (this.currentPoint == null)
			return false;
		return this.currentPoint.isSelected(new Point2D.Float(x, y));
	}

	public abstract String getName();

	public boolean closeToStartPoint(float x, float y) { 
		if (this.startPoint == null)
			return false;
		return this.startPoint.isSelected(new Point2D.Float(x, y));
	}

	public void startLineDraw(float x, float y) {  // Initiates the drawing process and displaying the lines as well as moving the current and starting draw points.
		if (this.closeToCurrentPoint(x, y)) {
			this.realStartPoint = this.currentPoint;
			this.currentPoint.highlight();
			this.inDrawProgress = true;
			this.drawFromCurrentNode = true;
		} else if (this.closeToStartPoint(x, y)) {
			this.realStartPoint = this.startPoint;
			this.startPoint.highlight();
			this.inDrawProgress = true;
			this.drawFromCurrentNode = false;
		}
	}

	public void dragLine(float x, float y) {  // line drawing function.  Also draws the assistant crosshair.
		JPanel mainPanel = frame.getPanel();
		if (this.inDrawProgress) {

			if (hasPointAt(x, y, 10)) {
				x = newMouseX;
				y = newMouseY;
			}
			StartPoint temp = frame.new StartPoint(x, y);
			temp.highlight();
			mainPanel.update(frame.g2);
			float xStart = (float) this.startPoint.xOrg;
			float yStart = (float) this.startPoint.yOrg;
			if (this.drawFromCurrentNode) {
				xStart = (float) this.currentPoint.xOrg;
				yStart = (float) this.currentPoint.yOrg;
				frame.g2.setColor(NewJFrame.dragColor);
				this.currentPoint.highlight();
			} else {
				frame.g2.setColor(this.lineColor);
				this.startPoint.highlight();

			}
			LineObject lineBuffer = new LineObject(xStart, yStart, (float) x, (float) y); // we create lines in advance then draw them all at once in effort to reduce lag.
			// System.out.println("Mouse Dragged");
			frame.g2.setColor(frame.dragColor);
			frame.g2.draw(temp.startPoint);
			frame.g2.fill(temp.startPoint);
			frame.g2.draw(lineBuffer);
			float[] center = lineBuffer.centreOfLine();
			frame.g2.drawString(lineBuffer.CalculateLength() + "", center[0], center[1]);
			float[] dash = {10.0f};
			frame.g2.setColor(Color.black);
			frame.g2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
			LineObject lineVertical = new LineObject(x, 0, x, (float) (frame.getSize().getHeight()));
			LineObject lineHorizontal = new LineObject(0, y, (float) (frame.getSize().getWidth()), y);
			frame.g2.draw(lineVertical);
			frame.g2.draw(lineHorizontal);
			frame.returnStroke();
		}
	}

	public void endLineDraw(float x, float y) {
		JPanel mainPanel = frame.getPanel();
		if (this.inDrawProgress) {
			if (hasPointAt(x, y, 10)) {
				x = newMouseX;
				y = newMouseY;
			}
			if (x == this.realStartPoint.xOrg && y == this.realStartPoint.yOrg)
				return;
			mainPanel.update(frame.g2);
			float xStart = (float) this.startPoint.xOrg;
			float yStart = (float) this.startPoint.yOrg;
			if (this.drawFromCurrentNode) {
				xStart = (float) this.currentPoint.xOrg;
				yStart = (float) this.currentPoint.yOrg;
			}
			LineObject lineBuffer = new LineObject(xStart, yStart, (float) x, (float) y);
			this.lines.add(lineBuffer);
			frame.g2.setColor(this.lineColor);
			if (this.currentPoint != this.startPoint && drawFromCurrentNode) {
				this.currentPoint.hide();
				this.currentPoint = frame.new StartPoint(x, y);
			} else {
				this.startPoint.hide();
				this.startPoint = frame.new StartPoint(x, y);
			}
			this.points.add(new Point2D.Float(x, y));

			float[] center = lineBuffer.centreOfLine();
			frame.g2.drawString(lineBuffer.CalculateLength() + "", center[0], center[1]);
			if (this.currentPoint.equals(this.startPoint)) {
				this.currentPoint = null;
				this.startPoint = null;
				this.shapeClosed = true;
			}
		}
	}

	/**
	 * Givs a list of all the lines that constructs this object
	 */
	public ArrayList<LineObject> getLines() {
		return this.lines;
	}

	/**
	 * Adds line to shape
	 * 
	 * line Line to add
	 */
	public void addLine(LineObject line) {

	}

	/**
	 * Checks if the coordinate (x, y) is a on a line that belongs for this shape
	 * 
	 * x coordinate
	 * y y coordinate
	 * errorBound Closeness
	 * if there is a line at coordinate it is True, False otherwise
	 */
	public boolean hasLineAt(float x, float y, float errorBound) {
		boolean hasLine = false;
		for (LineObject line : this.lines) {
			if (line.ptLineDist(new Point2D.Float(x, y)) < errorBound) {
				hasLine = true;
				break;
			}

		}
		return hasLine;
	}

	public boolean hasPointAt(float x, float y, float errorBound) { 
	// function with 2 features.  First will check if current mouse location is nearby any other points, this is used for snapping. 
	// The next if statement is for snapping to special angles, 45 90...180... 270... 360/0 , algorithm is:

	// 1.) There are two sub cases with two cases in each for a total of four cases.  For vertical and horizontal snapping we will use basic 
		//x/y coordinates as the basis of determination instead of utilizing the slope which is for 45 degree snaps. determine the slope of the line you are currently creating.
		//the reason for this is because for vertical snapping, the problem of the inifite slope arises.  The algorithm I created cannot compliment this type of slope, therefore
		// we avert the problem by using primitive coordinates. 
	// 2.) If your slope is less than or greater than, certain SNAP RATIOS, this will indicate to me that you are drawing a line that is nearby a special angle. 
		// similiarly, for the vertical andhorizontal
	// 3.) Then if any of these cases are true, your mouse coordinates are updated to perform the snap to the fitting angle case.
	// 4.) Note that a layer of complexity is added due to the Java grid orientation.  More comments on that below.

		boolean hasPoint = false;

		for (Point2D.Float point : this.points) {
			if (point.distance(new Point2D.Float(x, y)) < errorBound) {
				hasPoint = true;
				newMouseX = point.x;
				newMouseY = point.y;
				break;
			}
		}
		if (!hasPoint) {
			// Inverse the y coordinates since, Graphics2D start its grid at (0, 0) at the top left corner
			// Basically we are flipping the grid along the x axis
			float slope = (float) (-(y - this.realStartPoint.yOrg) / (x - this.realStartPoint.xOrg));
			//System.out.println(slope);
			// Case 1: Vertical snap - difference in X calculated
			if (Math.abs(x - this.realStartPoint.xOrg) < SNAP_RATIO) {
				hasPoint = true;
				newMouseX = (float) this.realStartPoint.xOrg;
				newMouseY = y;
			} 
			// Case 2: Horizontal snap - difference in Y calculated
			else if (Math.abs(y - this.realStartPoint.yOrg) < SNAP_RATIO) {
				hasPoint = true;
				newMouseX = x;
				newMouseY = (float) this.realStartPoint.yOrg;
			} 
			// Case 3: Negative 45 degree snap - slope -1
			else if (slope < 0) {
				if (Math.abs(slope) < 1.2 && Math.abs(slope) > 0.8) {
					hasPoint = true;
					newMouseX = x;
					newMouseY = (float) (x - this.realStartPoint.xOrg + this.realStartPoint.yOrg);
				}
			} 
			// Case 4: Positive 45 degree snap - slope 1
			else if (slope > 0) {
				if (Math.abs(slope) < 1.2 && Math.abs(slope) > 0.8) {
					hasPoint = true;
					newMouseY = y;
					newMouseX = (float) (this.realStartPoint.yOrg - y + this.realStartPoint.xOrg);
					//System.out.println("(" + newMouseX + ", " + newMouseY + ")");
				}
			}
		}
		return hasPoint;
	}

	public ArrayList<Point2D.Float> getEndPoints() {
		return this.points;
	}

	public void addPoint(StartPoint point) {

	}

	/**
	 * Checks if the coordinate (x, y) is a line of this shape, if yes then returns that LineObject
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param errorBound Closeness
	 * @return Returns LineObject at coordinate
	 */
	public LineObject getLineAt(float x, float y, float errorBound) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Checks if the coordinate (x, y) is a line of this shape, if yes then returns that removes the line
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param errorBound Closeness
	 */
	public void eraseLineAt(float x, float y, float errorBound) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Draws the entire Object 
	 */
	public void draw() {
		frame.g2.setColor(this.lineColor);
		// Drawing start points
		if (this.currentPoint != null)
			this.currentPoint.show();
		if (this.startPoint != null)
			this.startPoint.show();
		// Drawing lines
		for (LineObject line : lines) {
			frame.g2.draw(line);
			float[] center = line.centreOfLine();
			frame.g2.drawString(line.CalculateLength() + "", center[0], center[1]);
		}

		if (this.shapeClosed) {  // if the structure is closed we need to display area and name.
			float[] center = this.getCenter();
			// System.out.println(this.points);
			frame.g2.drawString(this.getName() + ": ", center[0], (center[1] - 20));
			// System.out.println("x point: " + xSum / this.points.size() + " y point: " + ySum / this.points.size());
			frame.g2.drawString(this.calculateArea() + " units", center[0], center[1]);
		} else {  // just display name if not closed, no area.
			float[] center = this.getCenter();
			frame.g2.drawString(getName(), center[0], center[1]);
		}
	}

	protected float[] getCenter() { // function to find midpoint of lines; it is used for getting coordinates for printing strings.
		float xSum = 0;
		float ySum = 0;
		int totalPoints = this.points.size();
		if (this.shapeClosed)
			totalPoints -= 1;
		float[] x_y_points = new float[2];
		// AVG all the x and y points
		for (int i = 0; i < (totalPoints); i++) {
			Point2D.Float point = this.points.get(i);
			xSum += point.x;
			ySum += point.y;
		}
		x_y_points[0] = xSum / (totalPoints);
		x_y_points[1] = ySum / (totalPoints);
		return x_y_points;
	}

	/**
	 * Scales the entire image from oldDimension to newDimension
	 * 
	 * We must have this because if we are drawing on different resolution screens, or different sized screens, images will not be uniform accross each. 
	 * problem also occurs when min/maxing a screen as such is changing screen size.  There fore this function will scale images accordingly. 
	 *  There are a few known bugs as of this moment 6/8/2017.
	 */
	public void scale(Dimension oldDimension, Dimension newDimension) {
		// Calculate ratios
		double heightRatio = (newDimension.getHeight() / oldDimension.getHeight());
		double widthRatio = (newDimension.getWidth() / oldDimension.getWidth());

		// Scale start and current points
		if (this.currentPoint != null) {
			double x = (this.currentPoint.xOrg * widthRatio);
			double y = (this.currentPoint.yOrg * heightRatio);
			this.currentPoint.move(x, y);
		}
		if (this.startPoint != null) {
			double x = (this.startPoint.xOrg * widthRatio);
			double y = (this.startPoint.yOrg * heightRatio);
			this.startPoint.move(x, y);
		}

		// System.out.println("move: (" + x +", " +y+")");
		int i = 0;
		// Scale all the lines
		ArrayList<LineObject> temp = new ArrayList<LineObject>(this.lines);
		for (Line2D line : temp) {
			double x1 = (line.getX1() * widthRatio);
			// x1 = this.roundPointTo((int) x1, defaultSnapValue);
			double x2 = (line.getX2() * widthRatio);
			// x2 = this.roundPointTo((int) x2, defaultSnapValue);
			double y1 = (line.getY1() * heightRatio);
			// y1 = this.roundPointTo((int) y1, defaultSnapValue);
			double y2 = (line.getY2() * heightRatio);
			// y2 = this.roundPointTo((int) y2, defaultSnapValue);
			// System.out.println("Point1: (" + x1 +", " +y1+")");
			// System.out.println("x1: " + x1 + " x2: " + x2 + " y1: " +y1 +" y2: " + y2);
			LineObject scaledLine = new LineObject((float) x1, (float) y1, (float) x2, (float) y2);
			this.lines.remove(i);
			this.lines.add(i, scaledLine);
			i++;
		}

		// Scale the points of the line
		int j = 0;
		ArrayList<Point2D.Float> tempPoints = new ArrayList<Point2D.Float>(this.points);
		for (Point2D.Float p : tempPoints) {
			float x = (float) (p.x * widthRatio);
			float y = (float) (p.y * widthRatio);
			this.points.remove(j);
			this.points.add(j, new Point2D.Float(x, y));
			j++;
		}
	}

	public float calculateArea() {
//  The area calculation mechanism.  I will use a matrix-based formula to accurately determine areas of ANY polygon (convex or concave); self-intersecting polygons excluded and will not return an accurate area measurement. 
//  The below is code-application of the algorithm I use to calculate area.  Mechanism explained: 

//  1.) We check, has the shaped been closed and has never been assigned a previous area (-1 by default). 
//  2.) This algorithm is a matrix-based formula. We take the endpoints of each line on the structure.  This means that there are 2 points per line, with 1 point overlapping between two lines, this is a must for self-enclosed shapes.
//  3.) Using this property we will now take the Y value of the first point and multiply by the X value of the next point, then take the X value of the first and multiply by Y value of the second... And so on for all of the points.
//  4.) This means we will get a set of two products.  We will take the value generated by performing 'Y value of the first point and multiply by the X value of the next point' and subtract by the value obtained by doing the other operation.
//  5.) The absoluate value of this number divided by 2 is the correct area.
//  5.5 ) Keeping in mind for the actual code, the point traversal is the tricky process, that I have not explained here, it involves sorting the points in such a way that we can do these cross-multiplications, this is just the mathematical backing. 
//  PROOF for mathematical formula, I will post a mathematical proof for this formula in the a document in the project folder to ensure belief that this algorithm will produce accurate results for the designated inputs.
//Shoelace formula
// Mathematical formula notation: Area = | [ (x1y2 - y1x2) + (x2y3 - y2x3)... + (xny1 - ynx1) ] / 2 |





		if (area == -1 && this.shapeClosed()) {
			ArrayList<Point2D.Float> pointsOrdered = new ArrayList<Point2D.Float>();
			ArrayList<LineObject> linesClone = new ArrayList<LineObject>(this.lines);
			LineObject firstLine = lines.get(0);
			Point2D.Float prevPoint = new Point2D.Float(firstLine.x1, firstLine.y1);
			pointsOrdered.add(prevPoint);
			// boolean exit = false;
			float multBlock1 = 0;
			float multBlock2 = 0;
			final float half = (float) 0.5;
			int i = 0;
			int lineIndex = 0;

			while (i < (this.points.size() - 1)) {
				// System.out.println(pointOrdered);
				// System.out.println(linesClone.size());
				LineObject currentLine = linesClone.get(lineIndex);
				Point2D.Float currentPoint = new Point2D.Float(currentLine.x1, currentLine.y1);
				if (currentPoint.x == prevPoint.x && currentPoint.y == prevPoint.y) {
					currentPoint = new Point2D.Float(currentLine.x2, currentLine.y2);
				}
				pointsOrdered.add(currentPoint);
				prevPoint = currentPoint;
				// System.out.println(linesClone);
				// System.out.println("REMOVING: " + linesClone.get(lineIndex));
				linesClone.remove(lineIndex);
				for (int j = 0; j < linesClone.size(); j++) {
					LineObject thisLine = linesClone.get(j);
					// System.out.println(thisLine.x1 + "--" + thisLine.x2);
					if (thisLine.hasPoint(prevPoint.x, prevPoint.y, 10)) {
						// System.out.println("FOUND: " + thisLine.x1 +"---"+thisLine.x2);
						lineIndex = j;
						break;
					}
				}
				i++;
			}
			// System.out.println(linesClone.size());
			// System.out.println(pointsOrdered.get(0).getX());

			for (int k = 0; k < (pointsOrdered.size() - 1); k++) {

				multBlock1 = multBlock1
						+ (float) (pointsOrdered.get(k).getX() / 16.25 * pointsOrdered.get(k + 1).getY() / 16.25);
				multBlock2 = multBlock2
						+ (float) (pointsOrdered.get(k).getY() / 16.25 * pointsOrdered.get(k + 1).getX() / 16.25);
			}

			area = (Math.abs((half * (multBlock1 - multBlock2))));
		}
		return Math.round(area * (float) 10) / (float) 10;
	}

	public boolean shapeClosed() {
		return this.shapeClosed;
	}

	public void drawFromXml(List<LineObject> lines) { //  a draw function specifically for when you open an XML of my format.

// An array of lines is passed into this function.  It is then parsed and points retrieved and each line is drawn after the points are sorted.

		int i = 0;
		int lineIndex = 0;
		boolean isOpen = false;
		ArrayList<Point2D.Float> pointsOrdered = new ArrayList<Point2D.Float>();
		LineObject firstLine = lines.get(0);
		Point2D.Float prevPoint = new Point2D.Float(firstLine.x1, firstLine.y1);
		pointsOrdered.add(prevPoint);
		List<LineObject> linesClone = new ArrayList<LineObject>(lines);
		while (i < (lines.size())) {
			// System.out.println(pointOrdered);
			// System.out.println(linesClone.size());
			LineObject currentLine = linesClone.get(lineIndex);
			Point2D.Float currentPoint = new Point2D.Float(currentLine.x1, currentLine.y1);
			if (currentPoint.x == prevPoint.x && currentPoint.y == prevPoint.y) {
				currentPoint = new Point2D.Float(currentLine.x2, currentLine.y2);
			}
			pointsOrdered.add(currentPoint);
			prevPoint = currentPoint;
			// System.out.println(linesClone);
			// System.out.println("REMOVING: " + linesClone.get(lineIndex));
			linesClone.remove(lineIndex);
			//System.out.println(linesClone);
			for (int j = 0; j < linesClone.size(); j++) {
				LineObject thisLine = linesClone.get(j);
				// System.out.println(thisLine.x1 + "--" + thisLine.x2);
				if (thisLine.hasPoint(prevPoint.x, prevPoint.y, 10)) {
					// System.out.println("FOUND: " + thisLine.x1 +"---"+thisLine.x2);
					lineIndex = j;
					break;
				} else {
					isOpen = true;
				}
			}
			i++;
		}
		System.out.println("DRAWING: " + pointsOrdered.size());
		System.out.println(pointsOrdered);
		int p = 0;
		int e = 1;
		this.startPoint = this.frame.new StartPoint(pointsOrdered.get(0).getX(), pointsOrdered.get(0).getY());
		while (e < pointsOrdered.size()) {
			this.startLineDraw(pointsOrdered.get(p).x, pointsOrdered.get(p).y);
			this.endLineDraw(pointsOrdered.get(e).x, pointsOrdered.get(e).y);
			p++;
			e++;
		}
	}

}
