package javaapplication2;

import java.awt.Color;
import javaapplication2.NewJFrame.StartPoint;

/**
*
* @author Alan Lee 
*/

public class SecondFloor extends SkeletonObject {


	public SecondFloor(NewJFrame frame, StartPoint start) {
		super(frame, start);
		lineColor = Color.RED;
	}

	@Override
	public String getName() {
		return "SecondFloor";
	}
}
