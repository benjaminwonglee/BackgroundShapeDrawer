package shapes;

import java.awt.Point;

public abstract class ShapeAbstract implements Shape {
	public boolean fill(){
		double choice = Math.random();
		if(choice >= 0.5){
			return true;
		}
		return false;
	}
	
}
