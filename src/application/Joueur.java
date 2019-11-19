package application;



import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class Joueur extends ImageView{



	int index;
	ObservableList<Monstre> ListMonstre = FXCollections.observableArrayList();
	private final int columns;

	BooleanProperty  CanTire= new SimpleBooleanProperty(false);
	private final int width;
	private final int height;
	private final int largeurJoueur= 50;
	private final int hauteurJoueur=60;
	private SimpleDoubleProperty vitesse = new SimpleDoubleProperty(5);
	ObservableList<Balle> ListBalles=FXCollections.observableArrayList();

	int VIE =100 ;

	private int lastIndex;
	int value=0;
	int valueD=0;

	Text score = new Text(""+value);
	Text démons = new Text(""+valueD);
	private DoubleProperty balles;
	DoubleProperty vie=new SimpleDoubleProperty(1);
	BooleanProperty  CanUp= new SimpleBooleanProperty(true);
	BooleanProperty  CanDown= new SimpleBooleanProperty(true);
	BooleanProperty  CanLeft= new SimpleBooleanProperty(true);
	BooleanProperty  CanRight= new SimpleBooleanProperty(true);
	private DoubleProperty X = new SimpleDoubleProperty();
	private DoubleProperty Y=new SimpleDoubleProperty();
	KeyCode direction;
	final double TAILLE=156+25;
	DoubleProperty spriteX = new SimpleDoubleProperty(156);
	DoubleProperty spriteY = new SimpleDoubleProperty(156);
	BorderPane terrain ;


	public Joueur(  Image image, 
			int columns,
			int width,   int height ,BorderPane br, ObservableList<Monstre> m) {

		super(image);

		this.columns   = columns;
		this.width     = width;
		this.height    = height;
		this.terrain=br;


		X.set(this.getX());
		Y.set(this.getY());
		this.ListMonstre.addAll(m);
		ListChangeListener<Monstre> removedListenerMonstre=new ListChangeListener<Monstre>() {

			@Override
			public void onChanged(Change<? extends Monstre> c) {
				// TODO Auto-generated method stub
				while(c.next()){
					if(c.wasRemoved()){
						for(Monstre monstre : c.getRemoved())
							br.getChildren().remove(monstre);
					}	
				}
			}

		};
		ListChangeListener<Balle> removedListnerBalle=new ListChangeListener<Balle>(){
			@Override
			public void onChanged(Change<? extends Balle> c) {
				// TODO Auto-generated method stub
				while(c.next()){
					if(c.wasRemoved()){
						for(Balle balle : c.getRemoved())
							br.getChildren().remove(balle);
					}	
				}
			}


		};
		valueD=ListMonstre.size();
		démons.setText(""+valueD);
		ListMonstre.addListener(removedListenerMonstre);
		ListBalles.addListener(removedListnerBalle);
	}


	public void setSpriteX(DoubleProperty spriteX) {
		this.spriteX = spriteX;
	}
	public void setSpriteY(DoubleProperty spriteY) {
		this.spriteY = spriteY;
	}
	public DoubleProperty getSpriteX() {
		return spriteX;
	}
	public DoubleProperty getSpriteY() {
		return spriteY;
	}
	public void setListMonstre(ObservableList<Monstre> ListMonste ) {
		this.ListMonstre=ListMonstre;

	}


	public void move1() {
		//move();
		ColisionJoueurVampire();
		index++;
		switch (direction) {

		case DOWN :

			if (index >=6 && CanDown.get()) {
				X.set( ((lastIndex++) % columns) * width  );
				Y.set(0);


				this.setViewport(new Rectangle2D(X.get(), Y.get(), width, height));
				this.setY(this.getY()+vitesse.get());
				this.spriteY.set(this.getY()+TAILLE);
				//lastIndex = index;
				index=0;
			}

			break;
		case LEFT:
			if (index >=6 && CanLeft.get()) {
				X.set( ((lastIndex++) % columns) * width  );
				Y.set(158);
				this.setViewport(new Rectangle2D(X.get(), Y.get(), width, height));
				this.setX(this.getX()-vitesse.get());
				this.spriteX.set( this.getX());
				index=0;


			}break;
		case RIGHT:  
			if (index >=6  && CanRight.get()) {
				X.set( ((lastIndex++) % columns) * width  );
				Y.set(316);

				this.setViewport(new Rectangle2D(X.get(), Y.get(), width, height));
				this.setX( this.getX()+vitesse.get());
				this.spriteX.set( this.getX());
				index=0;
			}
			break;
		case UP:
			if (index >=6  && CanUp.get() ) {
				X.set( ((lastIndex++) % columns) * width  );
				Y.set(474);
				this.setViewport(new Rectangle2D(X.get(), Y.get(), width, height));
				this.setY( this.getY()-vitesse.get());
				this.spriteY.set( this.getY());
				index=0;
			}
			break;
		}

	}

	public DoubleProperty BallesProprety() {
		return balles;
	}
	public double getNbrBalles() {
		return balles.get();
	}

	public void setNbrBalles(double nbrBalles) {
		this.balles.set(nbrBalles);
	}

	public DoubleProperty vieProprety() {
		return vie;
	}

	public double getVie() {
		return vie.get();
	}

	public void setVie(double vie) {
		this.vie.set(vie);;
	}

	public SimpleDoubleProperty getVitesse() {
		return vitesse ;
	}

	public void move() {
		if(ListMonstre.size()<1) {
			success();
		}

		for (Balle balle : ListBalles) {

			if(balle.BalleControleTerrain()) {

				Platform.runLater(()->{
					ListBalles.remove(balle);

				});
				//continue;
			}else {

				for (Monstre monstre :ListMonstre) {

					if(balle.ColisionMonstreBalle(monstre))

						Platform.runLater(()->{



							this.ListBalles.remove(balle);
							valueD-=1;
							value+=5;
							ListMonstre.remove(monstre);
							score.setText(""+value);
							démons.setText(""+valueD);

							boom(monstre);


						});
				}					
				balle.moveBalle();

			}

		}


	}
	public void boom(Monstre monstre ) {
		ImageView i=new ImageView(new Image("application/explosion.gif"));
		i.setX(monstre.getX());	
		i.setY(monstre.getY());

		terrain.getChildren().add(i);


		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.6), event -> {

			terrain.getChildren().remove(i);   

		}    

				));
		timeline.setCycleCount(1);
		timeline.play();

	}

	public void success() {
		ImageView i=new ImageView(new Image("application/win.png"));
		i.setX(0);	
		i.setY(0);
		i.setFitHeight(720);
		i.setFitWidth(1300);

		terrain.getChildren().add(i);


		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

			//terrain.getChildren().remove(i);  
			//Principale.accueilsuccess();
		}    

				));
		timeline.setCycleCount(1);
		timeline.play();

	}
	public void setDirection(KeyCode direction) {
		this.direction=direction;
	}

	public void bindAll() {
		ObservableValue<Boolean> bindingDown = Bindings.when(this.getSpriteY().lessThan(680))
				.then(true).otherwise(false);
		this.CanDown.bind(bindingDown);

		ObservableValue<Boolean> bindingUp = Bindings.when(this.getSpriteY().greaterThan(0))
				.then(true).otherwise(false);
		this.CanUp.bind(bindingUp);

		ObservableValue<Boolean> bindingLeft = Bindings.when(this.getSpriteX().greaterThan(-15))
				.then(true).otherwise(false);
		this.CanLeft.bind(bindingLeft);

		ObservableValue<Boolean> bindingRight = Bindings.when(this.getSpriteX().lessThan(1220))
				.then(true).otherwise(false);
		this.CanRight.bind(bindingRight);


	}
	public void setCanTire(Boolean can) {
		this.CanTire.set(can);
	}

	public void ColisionJoueurVampire() {
		boolean colision = false;	
		for(Monstre monstre : ListMonstre) {
			Rectangle2D rectangle2D=new Rectangle2D( this.getX(),this.getY(),25,130);

			if(rectangle2D.intersects(monstre.getX(),monstre.getY(),15,48)) {
				vie.set(vie.get()-0.001);

			}
		}

	}
	public void attente(int i) {
		for (int j = 0; j <i; j++) {

		}
	}
	public void tire (BorderPane br) {

		Image im = null;



		switch (direction) {
		case UP :
			im = new Image("application/tirehaut.png");


			break;
		case DOWN :
			im = new Image("application/tirebas.png");

			break;
		case LEFT :
			im = new Image("application/tiregauche.png");

			break;

		case RIGHT :

			im = new Image("application/tiredroit.png");
			break;
		}

		Balle balle=new Balle(this.spriteX.get()+largeurJoueur,
				this.spriteY.get()+hauteurJoueur, 
				im, 4, 4,this.direction);


		balle.setX(this.getX()+largeurJoueur);
		balle.setY(this.getY()+hauteurJoueur);
		br.getChildren().add(balle);
		ListBalles.add(balle);



	}
	public Boolean ColisionMonstreBalle(Balle balle) {

		Rectangle2D rectangle2D=new Rectangle2D( balle.getX(),balle.getY(),50,50);
		boolean colision = false;	
		System.out.println(balle.getLayoutX());
		for (int i = 0; i <ListMonstre.size(); i++) {
			colision=rectangle2D.intersects(ListMonstre.get(i).getX(),ListMonstre.get(i).getY(),50,50);
			break;
		}


		return colision;	
	}


}