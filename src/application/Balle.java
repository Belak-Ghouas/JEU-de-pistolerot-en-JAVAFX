package application;

import java.util.Observable;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

public class Balle extends ImageView {
	//private final static String BALE_PATH="balle.png";
   
	private DoubleProperty posX = new SimpleDoubleProperty();
	private DoubleProperty posY=new SimpleDoubleProperty();
	private DoubleProperty vitesseY=new SimpleDoubleProperty();
	private DoubleProperty  vitesseX=new SimpleDoubleProperty();
	private final double HauteurTerrain=680;
	private final double LargeurTerrain=1300;
	
	BooleanProperty  CanTire= new SimpleBooleanProperty(false);
	BooleanProperty  CanUp= new SimpleBooleanProperty(true);
	BooleanProperty  CanDown= new SimpleBooleanProperty(true);
	BooleanProperty  CanLeft= new SimpleBooleanProperty(true);
	BooleanProperty  CanRight= new SimpleBooleanProperty(true);
	KeyCode direction;
	 
	
	public Balle (double posX,double posY, Image image, double vitesseX, double vitesseY,KeyCode direction) {
		super(image);
		this.posX.set(posX);
		this.posY.set(posY);
		this.vitesseX.set(vitesseX);
		this.vitesseY.set(vitesseY);
		this.direction=direction;
		
		
		
	}
	public boolean controleFrame(BorderPane br) {
		// TODO Auto-generated method stub
		return true ;
		
	}
	
	public boolean BalleControleTerrain() {
	
		if(this.getX()>LargeurTerrain||this.getY()>HauteurTerrain || this.getX()<0 ||this.getY()<0) {
			
			
			return true ;
		}
			
		return false;
		
	}
	
	public Boolean ColisionMonstreBalle(Monstre monstre) {
		
		 Rectangle2D rectangle2D=new Rectangle2D( this.getX(),this.getY(),50,50);
		 boolean colision = false;	
		 
		 
		 		colision=rectangle2D.intersects(monstre.getX(),monstre.getY(),monstre.width-10,monstre.height-10);
		 	


	return colision;	
	}
	
	void moveBalle() {
	
		switch(this.direction) {
		case RIGHT:
			this.setX(this.getX() + this.vitesseY.get());
			break;
		case LEFT:
			this.setX(this.getX() - this.vitesseY.get());
			break;
		case UP:
			this.setY(this.getY() - this.vitesseY.get());
			break;
		case DOWN:
			this.setY(this.getY() + this.vitesseY.get());
			
			break;
		}
		
             
	}

	private void setPosX(double d) {
		this.posX.set(d);
		
	}
public void setPosY(Double posY) {
	this.posY.set(posY);
}
	private double getPosX() {
		// TODO Auto-generated method stub
		return this.posX.get();
	}

	private double getPosY() {
		// TODO Auto-generated method stub
		return this.posX.get();
	}
	
}
