package application;



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;







import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class Principale extends Application {

	final Image IMAGE = new Image("application/joueur.png");
	final Image IMAGEF=new Image("application/fille.png");
	final Image IMAGEM=new Image("application/maman.png");
	final Image IMAGEP=new Image("application/papa.png");

	private static final int COLUMNSF  =3;
	private static final int COUNTF    =3;
	private static final int OFFSET_XF=0;
	private static final int OFFSET_YF =0;
	private static final int WIDTHF  =64;
	private static final int HEIGHTF  = 64;
	private static Stage ps;
	private  static AnchorPane  sc;
	private static Pane ac;
	private static final int COLUMNS  =12;
	private static final int COUNT    =12;
	private static final int OFFSET_X =0;
	private static final int OFFSET_Y =0;
	private static final int WIDTH    =95;
	private static final int HEIGHT   = 158;
	private static final int COLUMNSP =4;
	private static final int COUNTP    =4;
	private static final int OFFSET_XP=0;
	private static final int OFFSET_YP =0;
	private static final int WIDTHP  =120;
	private static final int HEIGHTP  = 126;
	private ObservableList<Monstre> ListMonstre = FXCollections.observableArrayList();
	private static  PrincipaleController controller;    
	private List<String> menuData = Arrays.asList(
			"Single Player",
			"Multiplayer",
			"Game Options",
			"Additional Content",
			"Tutorial",
			"Benchmark",
			"Credits",
			"Exit to Desktop"
			);
	ListView<String> list = new ListView<>();
	ListChangeListener<Monstre> removedListenerMonstre ;




	public void start(Stage primaryStage) throws IOException {

		list.setItems(FXCollections.observableArrayList("rouge","blanc","noir","blue","jaune")); 
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.getItems().addAll("red", "blue",
				"yellow", "white", "black",
				"violet");
		/* trois items visibles en pop-up */
		comboBox.setVisibleRowCount(3);
		comboBox.setPromptText("couleur");
		comboBox.setEditable(true); 


		primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		ps=primaryStage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Principale1.fxml"));
		FXMLLoader loaderA = new FXMLLoader(getClass().getResource("AccueilFxml.fxml"));
		FXMLLoader loaderO = new FXMLLoader(getClass().getResource("Options.fxml"));
		AnchorPane root = (AnchorPane)loader.load();
		Pane accueil =(Pane)loaderA.load();
		AnchorPane options= (AnchorPane)loaderO.load();

		Accueil accueilController = loaderA.getController();
		controller = (PrincipaleController)loader.getController();
		Options optionController =(Options) loaderO.getController();


		controller.setBorderPane((BorderPane)root.getChildren().get(0));

		for (int i = 0; i <5; i++) {


			if (Math.random() < 0.5)
			{
				Monstre monstrep=new Monstre(IMAGEP, COLUMNSP, WIDTHP, HEIGHTP,controller.root);
				monstrep.setViewport(new Rectangle2D(OFFSET_XP, OFFSET_YP, WIDTHP, HEIGHTP));
				ListMonstre.add(monstrep);
			}else {
				Monstre monstreM=new Monstre(IMAGEM, COLUMNSF, WIDTHF, HEIGHTF,controller.root);
				monstreM.setViewport(new Rectangle2D(OFFSET_XF, OFFSET_YF, WIDTHF, HEIGHTF));
				ListMonstre.add(monstreM);
			}

		} 



		sc=root;
		ps=primaryStage;
		ac=accueil;


		Joueur joueur = new Joueur(IMAGE, COLUMNS, WIDTH, HEIGHT,controller.root,ListMonstre);

		joueur.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
		joueur.bindAll();
		joueur.setDirection(KeyCode.UP);
		joueur.setX(600);
		joueur.setY(500);


		controller.setJoueur(joueur);

		controller.root.getChildren().add(joueur);
		controller.root.getChildren().addAll(ListMonstre);

		joueur.setListMonstre(ListMonstre);
		int j=0;

		AnimationTimer move= new AnimationTimer() {
			long temps=0;
			KeyCode direc;
			@Override
			public void handle(long now) {
				//System.out.println(ListMonstre.size());
				joueur.move();

				if(temps >2) {

					//	for (int j = 0; j < 2; j++) {


					for (int i = 0; i < ListMonstre.size(); i++) {

						double s = Math.random();
						if(s<0.6) {
							direc=KeyCode.DOWN;
						}else if (s>0.6 && s<0.7) {
							direc=KeyCode.UP;
						}else if(s>0.7 && s<0.8) {
							direc=KeyCode.LEFT;
						}else if(s>0.8) {
							direc=KeyCode.RIGHT;
						}
						ListMonstre.get(i).move(direc);

					}
					//	}

					temps=0;
				}
				temps++;

				/*     for (int i = 0; i < ListMonstre.size(); i++) {
						ListMonstre.get(i).move(direc);

					}*/


			}
		};

		move.start();






		controller.setmySlider((Slider)(((HBox)root.getChildren().get(1)).getChildren().get(0)));
		controller.setProgressBar((ProgressBar)(((HBox)root.getChildren().get(1)).getChildren().get(1)));  
		Scene scene = new Scene(accueil,1310,700);
		controller.bindAll();

		primaryStage.setScene(scene);
		primaryStage.show();
		accueilController.isPlayProperty().addListener((observable, oldVal, newVal) -> {
			Scene s1 = new Scene(root,1310,720);
			primaryStage.setScene(s1);
		});
		accueilController.isQuit.addListener((observable, oldVal, newVal) -> {

			primaryStage.close();
		});
		accueilController.isConfig.addListener((observable, oldVal, newVal) -> {

			Scene s1 = new Scene(options,1310,720);
			primaryStage.setScene(s1);
		});


	}


	public static void accuei(KeyCode d ,KeyCode u,KeyCode r ,KeyCode l,KeyCode t) {
		controller.down=d;
		controller.up=u;
		controller.right=r;
		controller.left=l;
		controller.tire=t;
		Scene s1 = new Scene(sc,1310,720);
		ps.setScene(s1); 

	}


	public static void main(String[] args) {
		launch(args);
	}
}




















