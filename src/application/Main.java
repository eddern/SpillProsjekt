package application;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Main extends Application {
	
	Stage primarystage;
	Pane root;
	Player p;
	ArrayList<Circle> blobs = new ArrayList<>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primarystage = primaryStage;
			primaryStage.setTitle("BobleSpillet 0.0000001");
			root = new Pane();
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			root.setCursor(Cursor.NONE);
			
			p = new Player();
			root.getChildren().add(p);

			
			for (int i = 0; i < 50; i++){
				addRandomBlob();
			}
			
			primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, stageKeyEventHandler);
			primaryStage.addEventHandler(MouseEvent.MOUSE_MOVED, mouseMovedHandler);
			primaryStage.setScene(scene);
			primaryStage.show();
			centerMouse();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addRandomBlob(){
		int[] sizes = {5, 10, 15, 20};
		Random rand = new Random();
		Color color = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1);
		int size = sizes[rand.nextInt(4)];
		Circle c = new Circle(rand.nextInt(1900), rand.nextInt(1000), size);
		c.setFill(color);
		blobs.add(c);
		root.getChildren().add(c);
		
	}
	
	
	/* Eventhandler for tastetrykk på primarystage
	 * Implementerte funksjoner:
	 *  - Fullscreen
	 */
	private EventHandler stageKeyEventHandler = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			switch (event.getCode()) {
			case F11:
				primarystage.setFullScreen(!primarystage.isFullScreen());
				
				break;
			default:
				break;
			}
		}
	};
	
	/* Eventhandler for movement of mouse
	 * oppdaterer posisjonen til Player
	 */
	private EventHandler mouseMovedHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			p.updateLocation((int) event.getSceneX(), (int)event.getSceneY()); 
			Circle toRemove = null;		//for å unngå concurrentmodificationexception
			for (Circle c:blobs){
				if (p.intersects(c.getBoundsInLocal()) && root.getChildren().contains(c)){
					root.getChildren().remove(c);
					toRemove = c;	
					p.grow();
				}
			}
			if (toRemove != null) {
				blobs.remove(toRemove);
			}
		}
	};
	
	/* Flytter posisjonen til musepekeren til
	 * midt i skjermen når programmet startes
	 */
	private void centerMouse(){
		try {
			Robot r = new Robot();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			r.mouseMove((int) screenSize.getWidth()/2, (int) screenSize.getHeight()/2);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
