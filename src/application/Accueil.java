package application;

import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

public class Accueil {

		@FXML 
	Pane Accueil ;
		
		private List<String> menuData = Arrays.asList(
	            "Single Player",
	            "Game Options",         
	            "Exit to Desktop"
	    );
		
		@FXML
		VBox menu;
		@FXML
		Line line ;
		
	public  SimpleBooleanProperty isPlay = new SimpleBooleanProperty(false);
	public  SimpleBooleanProperty isPause = new SimpleBooleanProperty(false);
	public  SimpleBooleanProperty isQuit = new SimpleBooleanProperty(false);
	public  SimpleBooleanProperty IsSave = new SimpleBooleanProperty(false);
	public  SimpleBooleanProperty isConfig = new SimpleBooleanProperty(false);
	
	
	
		@FXML
		public void initialize() {
				
			//isLogin=new SimpleBooleanProperty(false);
			 menuData.forEach(data -> {
			        Menuitem item = new Menuitem(data);
			           // item.setOnAction(data.getValue());
			            ((Node) item).setTranslateX(-300);

			            Rectangle clip = new Rectangle(300, 30);
			            clip.translateXProperty().bind(item.translateXProperty().negate());

			            item.setClip(clip);
			            item.setOnMouseClicked(new EventHandler<Event>(
			            		) {

									@Override
									public void handle(Event event) {
										// TODO Auto-generated method stub
										if (event.getSource().toString().equals("Single Player")) {
											isPlay.set(true);
										}else if (event.getSource().toString().equals("Game Options")) {
											isConfig.set(true);
										}else if (event.getSource().toString().equals("Exit to Desktop")) {
											isQuit.set(true);
										}
									}
						});
			            

			       menu.getChildren().addAll(item);
			        });
			  
			startAnimation();
			    
		}
	
	
	 void startAnimation() {
	        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
	        st.setToY(1);
	        st.setOnFinished(e -> {
	            for (int i = 0; i < menu.getChildren().size(); i++) {
	                Node n = menu.getChildren().get(i);

	                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
	                tt.setToX(0);
	                tt.setOnFinished(e2 -> n.setClip(null));
	                tt.play();
	            }
	        });
	        st.play();
	    }
	 
	
	 public ReadOnlyBooleanProperty isPlayProperty() {
		 	return isPlay;
	 }
	
	 
	 public ReadOnlyBooleanProperty isQuitProperty() {
		 	return isQuit;
	 }
	
	 
	 public ReadOnlyBooleanProperty isPauseProperty() {
		 	return isPause;
	 }
	
	 public ReadOnlyBooleanProperty isConfigProperty() {
		 	return isConfig;
	 }
	
}
