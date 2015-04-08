package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player extends Circle{
	
	private int width = 5;
	
	public Player() {
		setFill(Color.CADETBLUE);
		setRadius(width);
	}
	
	
	public void updateLocation(int x, int y){
		setCenterX(x);
		setCenterY(y);
	}
	
	public void grow(){
		width += 5;
		setRadius(width);
	}
	
}
