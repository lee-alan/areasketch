package javaapplication2;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class LineObject extends Line2D.Float{
    
    private float length = -1;
	
	public LineObject(float x1, float y1, float x2, float y2){
		super(x1, y1, x2, y2);
	}

	
        
        public float CalculateLength() {
            if (length == -1){
                float x = (float)0.5;
                length = (float) (((float) Math.pow((Math.pow((Math.abs(x2-x1)),(float)2) + Math.pow(Math.abs(y2-y1),(float)2)),x))/16.25);
            }
            return Math.round(length * (float) 10) / (float) 10;
        }
        
        public float[] centreOfLine(){
            float[] x_y_point = new float[2];
            x_y_point[0] = ((x2+x1)/2);
            x_y_point[1] = ((y2 + y1)/2);
            return x_y_point;
        }
        
        public boolean hasPoint(float x, float y, float errorBound) {
        	
        	boolean hasPoint = false;
        	if (this.getP1().distance(new Point2D.Float(x, y)) < errorBound) {

				hasPoint = true;
				

			} else if (this.getP2().distance(new Point2D.Float(x, y)) < errorBound) {

				hasPoint = true;
				
			}
        	//System.out.println(hasPoint);
        	return hasPoint;
        }
}
