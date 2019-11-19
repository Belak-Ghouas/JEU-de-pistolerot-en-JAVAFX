package application;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;


public class Monstre extends ImageView{

	
    int index=0;
   
    private final int columns;

  final int width;
    final int height;
  
    private SimpleDoubleProperty vitesse = new SimpleDoubleProperty(5);
   
    private int lastIndex;
   
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
	


    public Monstre(  Image image, 
            int columns,
            int width,
            int height ,
            BorderPane br) {
        super(image);
       
        this.columns   = columns;
       
        this.width     = width;
        this.height    = height;
        this.terrain=br;
       
        this.setX( (Math.random())*1200);
        this.setY( (Math.random())*200);
     //  this.setLayoutX(this.getX());
      // this.setLayoutY(this.getY()); 
        X.set(this.getX());
        Y.set(this.getY());

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
	
	
	void move(KeyCode direc) {
		
		index++;
		
		
		
		switch (direc) {
			case DOWN :
		  if (index >=15 && CanDown.get()) {
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
				if (index >=15 && CanLeft.get()) {
					X.set( ((lastIndex++) % columns) * width  );
		           Y.set(this.width);
		           this.setViewport(new Rectangle2D(X.get(), Y.get(), width, height));
		           this.setX(this.getX()-vitesse.get());
		           this.spriteX.set( this.getX());
		            index=0;
		           
		            
		        }break;
			case RIGHT:  
				if (index >=15  && CanRight.get()) {
					X.set( ((lastIndex++) % columns) * width  );
			           Y.set(this.width*2);
			           this.setViewport(new Rectangle2D(X.get(), Y.get(), width, height));
		           this.setX( this.getX()+vitesse.get());
		           this.spriteX.set( this.getX());
		           index=0;
		        }
				break;
			case UP:
				if (index >=15  && CanUp.get() ) {
					X.set( ((lastIndex++) % columns) * width  );
			           Y.set(this.width*3);
			           this.setViewport(new Rectangle2D(X.get(), Y.get(), width, height));
		           this.setY( this.getY()-vitesse.get());
		           this.spriteY.set( this.getY());
		           index=0;
		        }
				break;
			
		}
	}
	
	public SimpleDoubleProperty getVitesse() {
		return vitesse ;
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
		 
		 ObservableValue<Boolean> bindingRight = Bindings.when(this.getSpriteX().lessThan(1200))
				 .then(true).otherwise(false);
		 			this.CanRight.bind(bindingRight);
		 			
		 	
	}
	

}