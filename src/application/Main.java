package application;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	Stage primarystage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primarystage = primaryStage;
			primaryStage.setTitle("BobleSpillet 0.0000001");
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, stageKeyEventHandler);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
