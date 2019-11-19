package application;
/*
 * 
 * 
 * 
 * */


import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class PrincipaleController {

	private static final int COLUMNS  =12;
	private static final int COUNT    =12;
	private static final int OFFSET_X =0;
	private static final int OFFSET_Y =0;
	private static final int WIDTH    =96;
	private static final int HEIGHT   = 158;
	KeyCode down=KeyCode.DOWN;
	KeyCode up=KeyCode.UP;
	KeyCode left=KeyCode.LEFT;
	KeyCode right=KeyCode.RIGHT;
	KeyCode tire=KeyCode.ENTER;
	
	final Image IMAGE = new Image("application/joueur.png");
	Joueur joueur;


	@FXML
	Text score;

	@FXML
	Text démons;
	@FXML
	private ImageView imageView;
	@FXML
	AnchorPane principale;
	@FXML
	BorderPane root;
	@FXML
	Slider mySlider;
	@FXML
	HBox barHaut;

	@FXML
	Button pause;

	@FXML
	ProgressBar BarVie;
	AnimationTimer move;



	public void initialize() {

		move= new AnimationTimer() {
			long temps=0;
			@Override
			public void handle(long now) {
				joueur.move1();

			}
		};




	}
	public void setJoueur(Joueur joueur) {
		this.joueur= joueur;
	}
	public void setProgressBar(ProgressBar BarVie) {
		this.BarVie=BarVie;
	}


	@FXML
	public void relacher(KeyEvent event) {

		if(down.equals(event.getCode())) 
			move.stop();
		if(up.equals(event.getCode()))
			move.stop();
		if(left.equals(event.getCode()))
			move.stop();
		if(right.equals(event.getCode()))
			move.stop();

		/*	System.out.println();
		switch (event.getCode()) {
		  case  DOWN :
          		move.stop();
          		break;
		  case LEFT :
			  move.stop();
		 			break;
		  case RIGHT :
			  move.stop();
	 			break;
		  case UP :
			  move.stop();

	 			break;
		  }*/
	}
	public void setmySlider(Slider mySlider) {
		this.mySlider=mySlider;
	}

	public Slider getmySlider() {
		return this.mySlider;
	}

	public void setBorderPane(BorderPane br) {
		this.root=br;
	}

	@FXML
	public void presser(KeyEvent event) {


		if(down.equals(event.getCode())) {
			joueur.setDirection(KeyCode.DOWN);
			//monstre.setDirection(KeyCode.DOWN); 
			move.start();
		}

		if(up.equals(event.getCode())) {
			joueur.setDirection(KeyCode.UP);
			//	monstre.setDirection(KeyCode.UP);
			move.start();
		}

		if(left.equals(event.getCode())) {
			joueur.setDirection(KeyCode.LEFT);
			//monstre.setDirection(KeyCode.LEFT);
			move.start();
		}
		if(right.equals(event.getCode())) {
			joueur.setDirection(KeyCode.RIGHT);


			move.start();
		}
		if(tire.equals(event.getCode())) {
			joueur.tire(root);
		}

		/* switch (event.getCode()) {
		  case  DOWN :
          			joueur.setDirection(KeyCode.DOWN);
          			//monstre.setDirection(KeyCode.DOWN); 
          			move.start();

          			break;
		  case LEFT :
			  joueur.setDirection(KeyCode.LEFT);
			  		//monstre.setDirection(KeyCode.LEFT);
			  		move.start();		  			
			  		break;
		  case RIGHT :
			  		joueur.setDirection(KeyCode.RIGHT);


			  		move.start();
			  		break;
		  case UP :
			  	joueur.setDirection(KeyCode.UP);
			  //	monstre.setDirection(KeyCode.UP);
			  	move.start();
			  	break;

		  case ENTER:
			  joueur.tire(root);

				break;
		  }*/

	}
	@FXML
	void cliquer(MouseEvent event) {
		this.root.requestFocus();
		joueur.tire(root);



	}
	public void bindAll() {
		this.joueur.getVitesse().bind(mySlider.valueProperty());
		BarVie.progressProperty().unbind();;
		this.BarVie.progressProperty().bind(joueur.vie);
		score.textProperty().bind(joueur.score.textProperty());
		démons.textProperty().bind(joueur.démons.textProperty());	
	}

	public void setImage(ImageView image) {
		imageView=image;
	}

	@FXML
	public void Pause(MouseEvent event) {


	}

}
