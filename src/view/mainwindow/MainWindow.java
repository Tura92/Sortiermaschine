package view.mainwindow;

import java.util.Collections;
import java.util.Vector;

import control.sortcontrol.BubbleSort;
import control.unitControl.Swap;
import control.unitControl.UnitControl;
import control.unitControl.UnitValues;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;





public class MainWindow {
	
	private static Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	
	//public static double mainWindowHeight = primaryScreenBounds.getHeight();
	//public static double mainWindowWidth  = primaryScreenBounds.getWidth();
	public static double mainWindowHeight = 600;
	public static double mainWindowWidth  = 1200;
	
	private Stage primaryStage;
	private BorderPane root;
	private Scene scene;
	private HBox menuBox;
	private Pane background;	
	private Button generateBtn;
	private Button backwardBtn;
	private Button pauseBtn;
	private Button forwardBtn;
	private ToggleButton stepByStepToggle;
	private TextField txtfSampleSize;
	private ComboBox<String> algorithmsCB;
	private ComboBox<String> ordersCB;
	private Button startBtn;
	private Label delayLbl;	
	
	private Vector<Rectangle> units;
	private Vector<Swap> swaps;
	private Vector<UnitValues> unitValues;
	private boolean unitsAreGenerated = false;
	
	
	
	public MainWindow() {
		
		initComponents();	
		posComponents();
		styleComponents();
		addFunctionality();
		//testComponents();
		primaryStage.setScene(scene);
		//primaryStage.setResizable(false);
		
		
		

        //set Stage boundaries to visible bounds of the main screen
       // primaryStage.setX(primaryScreenBounds.getMinX());
      //  primaryStage.setY(primaryScreenBounds.getMinY());
       // primaryStage.setWidth(primaryScreenBounds.getWidth());
      //  primaryStage.setHeight(primaryScreenBounds.getHeight());
        
		primaryStage.show();
		
	}
	private void initComponents() {
		//Initialisierung der Oberfl�chenkomponenten
		//Oberfl�che wird erzeugt
		primaryStage 		= new Stage();
		root			= new BorderPane();
		menuBox				= new HBox();
		background 			= new Pane();
		//scene 				= new Scene(background, mainWindowWidth, mainWindowHeight);
		scene 				= new Scene(root, mainWindowWidth, mainWindowHeight);
		generateBtn			= new Button("Generate");
		backwardBtn			= new Button();
		pauseBtn 			= new Button();
		forwardBtn			= new Button();
		stepByStepToggle	= new ToggleButton("Step-By-Step");
		txtfSampleSize		= new TextField();
		algorithmsCB		= new ComboBox<>();
		
		new Vector<Swap>();
			algorithmsCB.getItems().addAll(
							"BubbleSort",
							"Quicksort",
							"Countingsort" );
			algorithmsCB.getSelectionModel().selectFirst();
		ordersCB 			= new ComboBox<>();
			ordersCB.getItems().addAll(
							"Ordered",
							"Random"
					);
			ordersCB.getSelectionModel().select(1);;
		startBtn				= new Button("Start");
		
		delayLbl			= new Label("Delay: "+BubbleSort.delay);
		//Hier neue Elemente zum Hauptfenster hinzuf�gen
		//vvvvvvvvvvvvvvvvvvvvvvv
		background.getChildren().addAll(generateBtn, backwardBtn, pauseBtn, forwardBtn, 
				stepByStepToggle, txtfSampleSize, algorithmsCB, ordersCB, startBtn, delayLbl);
		
		//Sonstige Initialisierungen
		root.setTop(menuBox);
		
		// vvvvvvvvv Wenn ich die Elemente in die HBox einf�ge fehlt mir immer das kleinste Unit-Element im Fenster
		//menuBox.getChildren().addAll(startBtn, backwardBtn, pauseBtn, forwardBtn, stepByStepToggle, txtfSampleSize, algorithmsCB, ordersCB, testBtn);
		menuBox.setPrefSize(mainWindowWidth, (1/5)*mainWindowHeight);
		
		root.setCenter(background);
		background.setPrefSize(mainWindowWidth, (4/5)*mainWindowHeight);

		txtfSampleSize.setPromptText("Anzahl Elemente");
		txtfSampleSize.setPrefWidth(55);
		
		algorithmsCB.setPromptText("Algorithmus");
		ordersCB.setPromptText("Anordnung");
		
		
	}
	private void posComponents() {
		generateBtn.relocate(5, 5);
		backwardBtn.relocate(700, 5);
		pauseBtn.relocate(760, 5);
		forwardBtn.relocate(820, 5);
		stepByStepToggle.relocate(900, 5);
		txtfSampleSize.relocate(500, 5);
		algorithmsCB.relocate(250, 5);
		ordersCB.relocate(100, 5);
		startBtn.relocate(400, 5);
		delayLbl.relocate(580, 5);
	}	
	private void styleComponents(){
		scene.getStylesheets().add("util/style.css");
		background.getStyleClass().add("pane");
		//Style pausebutton
		Image pause=new Image("util/images/pause.png");
		ImageView iv1=new ImageView(pause);
		iv1.setFitHeight(30);
		iv1.setFitWidth(30);
		pauseBtn.setGraphic(iv1);
		
		//Style playbutton
		Image play=new Image("util/images/play.png");
		ImageView iv2=new ImageView(play);
		iv2.setFitHeight(30);
		iv2.setFitWidth(30);
		forwardBtn.setGraphic(iv2);
		
		//Style backbutton
		Image back=new Image("util/images/back.png");
		ImageView iv3=new ImageView(back);
		iv3.setFitHeight(30);
		iv3.setFitWidth(30);
		backwardBtn.setGraphic(iv3);
		
		menuBox.setStyle("-fx-background-color: black");
		menuBox.setPadding(new Insets(5, 10, 5, 10));
		
		delayLbl.setTextFill(Color.WHITE);
	}
	
	public static void delayms(int delay) {
		long now = 0;
		long later = System.currentTimeMillis()+delay;
		while (now < later) {
			now = System.currentTimeMillis();
			
		}
		
		
	}
	
	public void initOrderCB() {
		switch(ordersCB.getValue()) {
		case "Ordered":
			UnitControl.posNormal(units); 
			unitValues = UnitControl.initUnitValues(units);
			break;
			
		case "Random":
			UnitControl.posRandom(units); 
			unitValues = UnitControl.initUnitValues(units);
			break;
			
		default: 
			if(ordersCB.getValue() == null)
				UnitControl.posRandom(units);
			break;
		}
	}
	
	
	
	private void addFunctionality() {	
		generateBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(unitsAreGenerated) {		
					UnitControl.removeUnitsFromWindow(background, units);
				}
				int sampleSize = Integer.parseInt(txtfSampleSize.getText());
				units = UnitControl.generateUnits(sampleSize);
				UnitControl.addUnitsToWindow(background, units);
				
				initOrderCB();
				
				
				txtfSampleSize.setText("");
				txtfSampleSize.setPromptText(Integer.toString(sampleSize));
				unitsAreGenerated = true;
				
				
				//bubbleSort();
						
			}				
		});
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				switch(algorithmsCB.getValue()) {
					case "BubbleSort":
						BubbleSort sort = new BubbleSort(units, unitValues, 200);
						delayLbl.setText("Delay: "+BubbleSort.delay);
						sort.start();
				}
				
			}
		});	
		
		forwardBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				BubbleSort.delay -= 10;
				delayLbl.setText("Delay: "+BubbleSort.delay);
			}
		});
		
		backwardBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				BubbleSort.delay += 10;
				delayLbl.setText("Delay: "+BubbleSort.delay);
			}
		});
	}	
}
