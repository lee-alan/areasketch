package javaapplication2;

import java.awt.Color;
import javaapplication2.NewJFrame.StartPoint;

/**
*
* @author Alan Lee 
*/

public class FirstFloor extends SkeletonObject {


	public FirstFloor(NewJFrame frame, StartPoint start) {
		super(frame, start);
		lineColor = Color.BLACK;
	}

	@Override
	public String getName() {
		return "FirstFloor";
	}
}
