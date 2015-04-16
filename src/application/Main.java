package application;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;


public class Main extends Application {
	
	BorderPane root = new BorderPane();
	Stage primaryStage;
	Player p;
	
	
	ArrayList<Obstacle> blobs = new ArrayList<>();
	@Override
	public void start(Stage primaryStage) {
		try {
			
			this.primaryStage = primaryStage;
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			root.setCursor(Cursor.NONE);
			primaryStage.setTitle("Survival Of The Bubbliest version 0.0.1");
			p = new Player();
			root.getChildren().add(p);
			
			Timeline addNew = new Timeline(new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					for(int i = 0;i<10;i++){
						spawnObstacle();
					}
				}
				
			}));
			addNew.setCycleCount(Timeline.INDEFINITE);
			addNew.play();
			
			primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, stageKeyEventHandler);
			primaryStage.addEventHandler(MouseEvent.ANY, mouseMovedHandler);
			primaryStage.setScene(scene);
			//primaryStage.setFullScreen(true);
			primaryStage.show();
			centerMouse();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/* Eventhandler for tastetrykk på primarystage
	 * Implementerte funksjoner:
	 *  - Fullscreen
	 */
	private EventHandler stageKeyEventHandler = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			System.out.println(root.getWidth());
			switch (event.getCode()) {
			case ENTER:
				primaryStage.setFullScreen(!primaryStage.isFullScreen());
			case F11:
				primaryStage.setFullScreen(!primaryStage.isFullScreen());
				
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
			Obstacle toRemove = null;		//for å unngå concurrentmodificationexception
			for (Obstacle c:blobs){
				if (p.intersects(c.getBoundsInLocal()) && root.getChildren().contains(c)){
					
					// Game over attempt
					if(c.getRadius() > p.getRadius()){
						System.out.println("game over");
					}
					
					
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
	
	/**
	 *  Spawns one obstacle with a new timeline. The obstacle gets added to the blobs ArrayList
	 */
	void spawnObstacle(){
		final Timeline timeline = new Timeline();
		
		// Animation runs 1 time before it finishes
		timeline.setCycleCount(1);
		timeline.setAutoReverse(false);
		
		// Initializing the obstacle
		final Obstacle obs = new Obstacle();
		int[] cords = CoordinatePicker.getCoordinates((int)primaryStage.getWidth(), (int)primaryStage.getHeight());
		obs.setCenterX(cords[0]);
		obs.setCenterY(cords[1]);
		
		KeyValue kvX = new KeyValue(obs.centerXProperty(), cords[2]);
		KeyValue kvY = new KeyValue(obs.centerYProperty(), cords[3]);
		
		final KeyFrame kf = new KeyFrame(Duration.millis(10000),kvX,kvY);
		
		
		timeline.getKeyFrames().add(kf);
		timeline.play();
		EventHandler<ActionEvent> stopTimeline = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timeline.stop();
				root.getChildren().remove(obs);
				blobs.remove(obs);
			}
		};
		timeline.setOnFinished(stopTimeline);
		root.getChildren().add(obs);
		blobs.add(obs);
		
	}
}
