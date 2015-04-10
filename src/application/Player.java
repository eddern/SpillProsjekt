package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player extends Circle{
	
	private int width = 5;
	private Color color;
	
	public Player() {
		color = Color.CADETBLUE;
		setFill(color);
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
	
	public void invertColors(){
		if (getFill().equals(Color.WHITE)){
			setFill(color);
		} else {
			setFill(Color.WHITE);
		}
	}
	
}
