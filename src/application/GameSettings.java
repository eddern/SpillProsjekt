package application;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class GameSettings extends VBox{
	
	//Labels
	private Label titleLabel = new Label("Options");
	private Label antallLabel = new Label("Sirkler i sekundet: ");
	private Label spawnLabel = new Label("Spawn i sekundet: ");
	
	//Controls
	private Slider antallSlider = new Slider(1, 30, 10);
	private Slider spawnSlider = new Slider(1, 10, 5);
	
	public GameSettings() {
		setSpacing(4);
		
		antallLabel.setText("Sirkler i sekundet: "+(int)antallSlider.getValue());
		antallSlider.setMinorTickCount(1);
		antallSlider.setMajorTickUnit(100);
		antallSlider.setShowTickLabels(true);
		antallSlider.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				antallLabel.setText("Spawn i sekundet: "+(int)antallSlider.getValue());
			}
		});
		
		spawnLabel.setText("Spawn i sekundet: "+(int)spawnSlider.getValue());
		spawnSlider.setMinorTickCount(1);
		spawnSlider.setMajorTickUnit(100);
		spawnSlider.setShowTickLabels(true);
		spawnSlider.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				spawnLabel.setText("Spawn i sekundet: "+(int)spawnSlider.getValue());
			}
		});
		
		//Styling
		getStyleClass().add("settings");
		titleLabel.getStyleClass().add("ttlLabel");
		
		
		//Adding to parent node
		getChildren().add(titleLabel);
		getChildren().addAll(antallLabel, antallSlider);
		getChildren().add(new Separator(Orientation.HORIZONTAL));
		getChildren().addAll(spawnLabel, spawnSlider);
	}
	
	
}
