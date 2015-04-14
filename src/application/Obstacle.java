package application;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Obstacle extends Circle{
	private double[] sizes = { 10.0, 15.0, 20.0, 25.0 };
	private double size;
	private Color color;
	
	private Random rand = new Random();
	
	public Obstacle(){
		size = sizes[rand.nextInt(4)];
		color = new Color(rand.nextDouble(),rand.nextDouble(),rand.nextDouble(), 1);
		this.setFill(color);
		this.setRadius(size);
	}
}
