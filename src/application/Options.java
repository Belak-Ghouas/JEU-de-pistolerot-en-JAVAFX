package application;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Options {

	@FXML
	TextField droite;

	@FXML
	TextField gauche;
	
	@FXML
	Button save;
	
	@FXML
	TextField bas;
	
	@FXML
	TextField haut ;
	
	@FXML
	TextField tire ;
	
	static KeyCode down;
	static KeyCode right;
	static KeyCode left;
	static KeyCode up;
	static KeyCode shoot;
	
	
	@FXML
	public void initialize() {
		
		haut.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (haut.getText().length() >= 1) {
                    	
            haut.setText(haut.getText().substring(0, 1));
                    }
                }
            }

			
            }
		
		);
		
		
		
		bas.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (bas.getText().length() >= 1) {
                    	
          bas.setText(bas.getText().substring(0, 1));
                    }
                }
            }

			
            }
		);
		
		droite.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (droite.getText().length() >= 1) {
                    	
           droite.setText(droite.getText().substring(0, 1));
                    }
                }
            }

			
            }
		);
		
		tire.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tire.getText().length() >= 1) {
                    	
            tire.setText(tire.getText().substring(0, 1));
                    }
                }
            }

			
            }
		);
		
		
		
		gauche.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (gauche.getText().length() >= 1) {
                    	
            gauche.setText(gauche.getText().substring(0, 1));
                    }
                }
            }

			
            }
		);
		 ObservableValue<Boolean> bindingsave = Bindings.when(haut.lengthProperty().lessThan(1).or(bas.lengthProperty().lessThan(1).or(gauche.lengthProperty().lessThan(1).or(droite.lengthProperty().lessThan(1).or(tire.lengthProperty().lessThan(1))))))
				 .then(true).otherwise(false);
		 			
		save.disableProperty().bind(bindingsave);
	}
	
	@FXML
	public void KeyHaut (KeyEvent event) {
		up=event.getCode();
		haut.setText(event.getCode().toString());
	}
	@FXML
public void KeyBas (KeyEvent event) {
		down=event.getCode();
		bas.setText(event.getCode().toString());
	}
	@FXML
public void KeyDroite (KeyEvent event) {
		right=event.getCode();
	droite.setText(event.getCode().toString());
}
	@FXML
public void KeyGauche (KeyEvent event) {
		left=event.getCode();
	gauche.setText(event.getCode().toString());
}
	@FXML
	public void KeyTire (KeyEvent event) {
		shoot=event.getCode();
		tire.setText(event.getCode().toString());
	}
	
	@FXML
	public void sauvgarde(MouseEvent event) {
		
		Principale.accuei(down,up,right,left,shoot);
	}
}
