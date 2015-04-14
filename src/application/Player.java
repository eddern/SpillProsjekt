package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/** The Player circle
 * 
 * @author Espen Meidell <espen.meidell@gmail.com>
 *
 */
public class Player extends Circle{
	
	// width = radius
	private int width = 10;
	
	// color of the player
	private Color color;
	
	// growDelay = how many obstacles should be consumed before it grows?
	private int growDelay = 3;
	private int growDelayOriginal = growDelay;
	
	// The maximum radius the player can reach
	private int maxRadius = 100;
	
	// The amount it grows each time
	private int growFactor = 20;
	
	
	
	public Player() {
		color = Color.CADETBLUE;
		setFill(color);
		setRadius(width);
	}
	
	
	/**
	 * Updates the location of the player to the coordinate (x,y) given as parameter
	 * @param x
	 * @param y
	 */
	public void updateLocation(int x, int y){
		setCenterX(x);
		setCenterY(y);
	}
	
	
	/**
	 * Changes the radius of the player
	 */
	public void grow(){
		growDelay--;
		
		// Each time the given growDelay reaches zero it grows a certain amount
		if(growDelay == 0){
			
			// calculating the new radius
			width += growFactor;
			
			// checks to see if it doesn't overrides the maxRadius
			if(width < maxRadius+1){
				
				// sets the new radius of the player
				setRadius(width);
			}
			
			// Resets the growDelay
			growDelay = growDelayOriginal;
		}
	}
	
}