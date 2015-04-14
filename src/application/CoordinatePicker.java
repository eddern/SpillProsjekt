package application;

import java.util.Random;

/**
 * Generates random start coordinates and end coordinates for the obstacle
 * @author eddern <edvard.schreiner.sjoeblom@gmail.com>
 *
 */
public class CoordinatePicker{
	
	/**Generates and array with coordinates for the obstacle
	 * 
	 * @param width
	 * @param height
	 * @return int[] = {startXcord, startYcord, endXcord, endYcord }
	 */
	public static int[] getCoordinates(int width, int height){
		
		Random rand = new Random();
		int startX, startY, endX, endY, XorYrand, MaxOrMin, sidePicker;
		
		// Decides whether the X or the Y cord should be random
		XorYrand = rand.nextInt(2);
		// Decides whether the other cord should be MAX or MIN
		MaxOrMin = rand.nextInt(2);
				
		// Creates random starting point for the obstacle at the edge of the screen
		if(XorYrand==1){
			
			startX = rand.nextInt(width);
			
			// Checks if Y should me maximum or minimum
			if(MaxOrMin == 1){
				startY = height;
			}else{
				startY = 0;
			}
		}else{
			
			startY = rand.nextInt(height);
			
			// Checks if X should me maximum or minimum
			if(MaxOrMin == 1){
				startX = width;
			}else{
				startX = 0;
			}
		}
		
		// sidePicker decides which side of the screen the obstacle will end at
		sidePicker = rand.nextInt(3);
		
		// Creates pseudo random end cords which takes all cases in consideration
		if(startX==0){
			if(sidePicker==0){
				endY = 0;
				endX = rand.nextInt(width);
			}
			else if(sidePicker==1){
				endY = rand.nextInt(height);
				endX = width;
			}
			else{
				endY = height;
				endX = rand.nextInt(width);
			}
		}
		else if(startX==width){
			if(sidePicker==0){
				endY = height;
				endX = rand.nextInt(width);
			}
			else if(sidePicker==1){
				endY = rand.nextInt(height);
				endX = 0;
			}
			else{
				endY = 0;
				endX = rand.nextInt(width);
			}	
		}
		else if(startY==0){
			if(sidePicker==0){
				endY = rand.nextInt(height);
				endX = width;
			}
			else if(sidePicker==1){
				endY = height;
				endX = rand.nextInt(width);
			}
			else{
				endY = rand.nextInt(height);
				endX = 0;
			}
		}
		else{
			if(sidePicker==0){
				endY = rand.nextInt(height);
				endX = 0;
			}
			else if(sidePicker==1){
				endY = 0;
				endX = rand.nextInt(width);
			}
			else{
				endY = rand.nextInt(height);
				endX = width;
			}
		}
		
		// prepares the array to be returned
		int[] coordinates = {startX, startY, endX, endY};
		
		// return the array
		return coordinates;
	}
}
