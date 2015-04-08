package application;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Obsticle extends Circle{
	private double[] sizes = { 5.0, 10.0, 15.0, 20.0};
	private double size;
	private int speed;
	private Color color;
	
	Random rand = new Random();
	
	public Obsticle(){
		size = sizes[rand.nextInt(4)];
		color = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1);
		this.setFill(color);
		this.setRadius(size);
	}

}
