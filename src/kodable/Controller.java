package kodable;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {

	private Path path = new Path();
	private ArrayList<String> seq = new ArrayList<String>();	// User-created sequence
	private ArrayList<String> soln = new ArrayList<String>();	// Solution sequence

	private String value = "";
	private String direction = "";


	@FXML
	private ImageView mario;
	
	@FXML
    private ImageView lvlComplete;
	
	@FXML
	private Button nextLevel2a;

	@FXML
	private ImageView move1, move2, move2b, move3, move4; // User-created moves by dropping arrows

	@FXML
	private ImageView left, up, right, down, yellowIf;	// Arrow options to drag

	// Drag and drop arrows to create sequence
	@FXML
	void handleDragDetected(MouseEvent event) {
		Image img = null;
		value = ((ImageView)event.getSource()).getId();
		switch(value) {
		case "left":
			img = new Image("ArrowLeft.jpg");
			direction = "left";
			break;
		case "up":
			img = new Image("ArrowUp.jpg");
			direction = "up";
			break;
		case "right":
			img = new Image("ArrowRight.jpg");
			direction = "right";
			break;
		case "down":
			img = new Image("ArrowDown.jpg");
			direction = "down";
			break;
		case "yellowIf":
			img = new Image("yellow-square.jpg");
			break;
		}

		Dragboard db = left.startDragAndDrop(TransferMode.COPY);
		ClipboardContent cb = new ClipboardContent();

		cb.putImage(img);
		db.setContent(cb);

		event.consume();
	}

	@FXML
	void handleDragDropped(DragEvent event) {
		Image img = event.getDragboard().getImage();

		value = ((ImageView)event.getSource()).getId();

		switch(value) {
		case "move1":
			move1.setImage(img);
			break;
		case "move2":
			move2.setImage(img);
			break;
		case "move3":
			move3.setImage(img);
			break;
		case "move4":
			move4.setImage(img);
			break;
		}
		
		seq.add(direction);	// create sequence based on user arrow input
		direction="";

	}

	@FXML
	void handleDragOver(DragEvent event) {
		if(event.getDragboard().hasImage()) {
			event.acceptTransferModes(TransferMode.COPY);
		}
	}
	
	// check if user sequence is correct
	boolean isCorrectSequence(ActionEvent event) {
		
		value = ((Button)event.getSource()).getId();
		
		switch(value) {
		case "playLvl1a":
			// Create solution sequence for level1a
			soln.add("right");
			soln.add("down");
			soln.add("right");
			soln.add("down");
			break;
		case "playLvl1b":
			// Create solution sequence for level1b
			soln.add("right");
			soln.add("up");
			soln.add("right");
			soln.add("down");
			soln.add("right");
			break;
		case "playLvl1c":
			// Create solution sequence for level1c
			soln.add("right");
			soln.add("up");
			soln.add("right");
			break;
		case "playLvl2a":
			// create solution sequence for level2a
			soln.add("right");
			soln.add("down");
			soln.add("right");
			break;
		case "playLvl2b":
			// Create solution sequence for level2b
			soln.add("right");
			soln.add("up");
			soln.add("right");
			soln.add("down");
			break;
		case "playLvl2c":
			// Create solution sequence for level2c
			break;
		case "playLvl3a":
			// Create solution sequence for level3a
			break;
		case "playLvl3b":
			// Create solution sequence for level3b
			soln.add("right");
			soln.add("down");
			soln.add("right");
			soln.add("down");
			soln.add("right");
			soln.add("down");
			soln.add("right");
			soln.add("up");
			soln.add("right");
			break;
		case "playLvl3c":
			// Create solution sequence for level3c
			break;
		}
		
		// if user sequence is empty
		if (seq.isEmpty())
			return false;
		
		// check user sequence (seq) against solution sequence (soln)
		for (int i = 0; i < soln.size()-1; i++) {
			if (!soln.get(i).equals(seq.get(i)))
				return false;
		}
		return true;
	}

	@FXML
	void createPathSolution(ActionEvent event) {
		value = ((Button)event.getSource()).getId();	// get id of button input to determine level
		
		// Set initial MoveTo object to initialize path
		path.getElements().add(new MoveTo(mario.getX()+27, mario.getY()+27));

		switch(value) {
		case "playLvl1a":
			// Create a solution path for level1a
			path.getElements().add(new HLineTo(250));
			path.getElements().add(new VLineTo(140));
			path.getElements().add(new HLineTo(540));
			path.getElements().add(new VLineTo(250));
			break;
		case "playLvl1c":
			// Create a solution path for level1c
			path.getElements().add(new HLineTo(310));
			path.getElements().add(new VLineTo(-80));
			path.getElements().add(new HLineTo(835));
			break;
		case "playLvl2a":
			// Create a solution path for level2a
			path.getElements().add(new HLineTo(240));
			path.getElements().add(new VLineTo(245));
			path.getElements().add(new HLineTo(835));
			break;
		case "playLvl2b":
			// Create a solution path for level2b
			path.getElements().add(new HLineTo(140));
			path.getElements().add(new VLineTo(-80));
			path.getElements().add(new HLineTo(835));
			path.getElements().add(new VLineTo(140));
			break;
		case "playLvl3b":
			// Create a solution path for level3b
			path.getElements().add(new HLineTo(250));
			path.getElements().add(new VLineTo(140));
			path.getElements().add(new HLineTo(310));
			path.getElements().add(new VLineTo(250));
			path.getElements().add(new HLineTo(360));
			path.getElements().add(new VLineTo(360));
			path.getElements().add(new HLineTo(480));
			path.getElements().add(new VLineTo(90));
			path.getElements().add(new HLineTo(835));
			break;
		}
			
	}
	
	@FXML
	void playPath(ActionEvent event) throws IOException {
			createPathSolution(event);
			
			if (!isCorrectSequence(event))
				return;
			
			PathTransition transition = new PathTransition();
			transition.setNode(mario);
			transition.setDuration(new Duration(6000));
			transition.setPath(path);
			transition.play();
			
			fadeIn(event, lvlComplete);
			
			value = ((Button)event.getSource()).getId();

			switch(value) {
			case "playLvl2a":
				fadeIn(event, nextLevel2a);
			}

	}

	// Fade in transition when level complete
	void fadeIn(ActionEvent event, Node node) {
		FadeTransition fadeIn = new FadeTransition(new Duration(2500), node);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setDelay(new Duration(6100));
		fadeIn.play();		
	}
	
    @FXML
    void nextLevel(ActionEvent event) throws IOException {
    	value = ((Button)event.getSource()).getId();
		
		switch(value) {
		case "nextLevel2a":
			changeScene(event, "Level2b.fxml");
		}
    }
	
	@FXML
	void handleButton(ActionEvent event) throws Exception {
		value = ((Button)event.getSource()).getText();	// get text of button input

		switch (value) {
		case "Play!":
			changeScene(event, "LevelSelector.fxml");
			break;
		case "Btn1a":		
			changeScene(event, "Level1a.fxml");
			break;
		case "Btn1b":
			changeScene(event, "Level1b.fxml");
			break;
		case "Btn1c":
			changeScene(event, "Level1c.fxml");
			break;
		case "Btn2a":
			changeScene(event, "Level2a.fxml");
			break;
		case "Btn2b":
			changeScene(event, "Level2b.fxml");
			break;
		case "Btn2c":
			changeScene(event, "Level2c.fxml");
			break;
		case "Btn3a":
			changeScene(event, "Level3a.fxml");
			break;
		case "Btn3b":
			changeScene(event, "Level3b.fxml");
			break;
		case "Btn3c":
			changeScene(event, "Level3c.fxml");
			break;
		case "Back":
			changeScene(event, "LevelSelector.fxml");
			break;
		}

	}

	void changeScene(ActionEvent event, String fileName) throws IOException { //Method to change scene
		Parent root = FXMLLoader.load(getClass().getResource(fileName));
		Scene sn = new Scene(root);
		Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.setScene(sn);
		stage.show(); 
	}
	
}

