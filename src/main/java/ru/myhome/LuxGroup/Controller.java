package ru.myhome.LuxGroup;

import java.util.ArrayList;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ru.myhome.DataFile.CutPriceList;
import ru.myhome.DataFile.MaterialsList;

public class Controller implements InitializingBean{
	
	@FXML
	private AnchorPane firstForm;

    @FXML
    private ComboBox<String> cbMList;
    private TextField cBoxText;

    private String lastMaterial;
    
    @FXML
    private CheckBox checkBoxM;

    @FXML
    private HBox sqContainer;
    
    @FXML
    private TextField tfThickness;

    @FXML
    private TextField tfWidth;

    @FXML
    private TextField tfHeight;

    @FXML
    private TextField tfSquare;

    @FXML
    private TextField tfLength;

    @FXML
    private TextField tfRerult;

    @FXML
    private Button btExit;

    @FXML
    private Button calcButton;

    @FXML
    private TextArea tAreaInform;
    
    private ObservableList<String> filter;
    
    @Autowired
    private MaterialsList mList;
    
    
    @Override
	public void afterPropertiesSet() throws Exception {
    	System.out.println("After properties set");
    	filter = FXCollections.observableArrayList(mList.getmList());
    	cbMList.setItems(filter);
    	//cbMList.getEditor().setText(filter.isEmpty() ? "":filter.get(0));
    	cBoxText = cbMList.getEditor();
    	//showInformation();
	}

    @FXML
    public void initialize() {
    	cBoxText = cbMList.getEditor();
    	cBoxText.textProperty().addListener((observable, oldValue, newValue) -> {
    		if(cBoxText.getText().isEmpty()) {
    			filter.setAll(mList.getmList());
    			cbMList.show();
    			return;
    		}
    		if(filter.contains(cBoxText.getText())) {
    			//tfThickness.requestFocus();
    			cBoxText.setText(newValue);
    			return;
    		}
    		filter.clear();
    		ArrayList<String> temp = mList.getmList();
    		for(String item: temp) {
    			if(item.toLowerCase().contains(newValue.toLowerCase()))
    				filter.add(item);
    		}
    		cBoxText.setText(newValue);
    		cbMList.hide();
    		cbMList.show();
    	});
    	cbMList.focusedProperty().addListener((observable, oldValue, newValue) -> {
    		
    		if(!newValue) {
    			if(filter.isEmpty()) {
    				alert("Введёный материал не найден.");
    				cBoxText.clear();
    				filter.setAll(mList.getmList());
    				cBoxText.requestFocus();
    				return;
    			}
    			if(cBoxText.getText().isEmpty()) {
    				//alert("Введите материал");
    				//cBoxText.requestFocus();
    				cBoxText.setText(filter.isEmpty() ? "":filter.get(0));
    				return;
    			}
    			if(filter.size() == 1) {
    				cBoxText.setText(filter.get(0));
    				return;
    			}
    		}
    		else {
    			String s = cBoxText.getText();
    			cBoxText.setText(s);
    			
    		}
    	});
    	/*ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {
			
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				
			}
		};*/
    	
    	tfWidth.textProperty().addListener( (observable, oldValue, newValue) -> {
    		
    		tfWidth.setText(getNewValue(oldValue, newValue));
            if(tfWidth.isFocused()) {
            	double square, height, width;
            	width = newValue.isEmpty()?0:Double.parseDouble(newValue);
		    	height = tfHeight.getText().isEmpty()?0:Double.parseDouble(tfHeight.getText());
		    	square = width*height;
		    	tfSquare.setText(String.format("%.2f",square).replace(",", "."));
		    	calcPrice(square);
		    }
		});
    	
    	tfHeight.textProperty().addListener( (observable, oldValue, newValue) -> {
    		
    		tfHeight.setText(getNewValue(oldValue, newValue));
            if(tfHeight.isFocused()) {
            	double square, height, width;
            	height = newValue.isEmpty()?0:Double.parseDouble(newValue);
		    	width = tfWidth.getText().isEmpty()?0:Double.parseDouble(tfWidth.getText());
		        square = width*height;
	        	tfSquare.setText(String.format("%.2f",square).replace(",", "."));
	        	calcPrice(square);
	       }
	    });
    	
    	tfSquare.textProperty().addListener( (observable, oldValue, newValue) -> {
    		double square;
    		tfSquare.setText(getNewValue(oldValue, newValue));
            
            if(!newValue.isEmpty()) {
            	square = Double.parseDouble(newValue);
            	if(tfSquare.isFocused()) {
            		tfWidth.setText(newValue);
	            	tfHeight.setText("1");
	            	calcPrice(square);
	            }
            }
        });
    	
    	tfLength.textProperty().addListener( (observable, oldValue, newValue) -> {
    		
    		tfLength.setText(getNewValue(oldValue, newValue));
            //double square = tfSquare.getText().isEmpty()?0:Double.parseDouble(tfSquare.getText());
            calcPrice();
        });
    	
    	tfThickness.textProperty().addListener( (observable, oldValue, newValue) -> {
    		
    		tfThickness.setText(getNewValue(oldValue, newValue));
    		if(tfThickness.getText().isEmpty()) return;
    		calcPrice();
        });
    }
    private void alert(String alertText) {
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Waring...");
		alert.setHeaderText(null);
		alert.setContentText(alertText);
		firstForm.setVisible(false);
		alert.showAndWait();
		firstForm.setVisible(true);
    }
    private void calcPrice() {
    	double square = tfSquare.getText().isEmpty()?0:Double.parseDouble(tfSquare.getText());
        calcPrice(square);
    }
    private void calcPrice(double square) {
    	double l = tfLength.getText().isEmpty()?0.0:Double.parseDouble(tfLength.getText());
    	//Material m = mList.getMaterial(cBoxText.getText()); 
    	double mCost, cutCost, t;
    	t = tfThickness.getText().isEmpty()?0:Double.parseDouble(tfThickness.getText());
    	mCost = mList.getmCost(cBoxText.getText(), t);
    	cutCost = mList.getCutCost(cBoxText.getText(), t, l);
    	//showInformation(mCost, cutCost, square, l);
    	double res = mCost*square + cutCost*l;//m == null ? 0:(l*m.getCutPrice(l) + (checkBoxM.isSelected()?0:square*m.getPrice()));
    	tfRerult.setText(String.format("%.2f",res).replace(",", "."));
    	
    	String info = String.format(""
    			+ "Материал:\n\t%s\n"
    			+ "Цена:\n\tза 1 кв.м: %.2f р.\n"
    			+ "\tза %.2f кв.м: %.2f р.\n"
    			+ "Стоимость резки:\n\t"
    			+ "за 1 м.п.: %.2f\n\t"
    			+ "за %.2f м.п.: %.2f\n"
    			+ "ИТОГО: %.2f р.", cBoxText.getText(), mCost, square, mCost*square, cutCost, l, cutCost*l, res);
    	tAreaInform.setText(info);
    }
    private String getNewValue(String oldValue, String newValue) {
    	
    	if(newValue.matches("^[\\.,]")) newValue = "0.";
		if(newValue.endsWith(",")) newValue = newValue.replace(",", ".");
        if (!newValue.matches("^\\d*\\.?\\d{0,2}")) {
        	return oldValue;
        }
        else return newValue;
    }
    
    /*private void showInformation(double mCost, double cutCost, double sq, double l) {
    	
    	String info = String.format(""
    			+ "Материал:\n\t%s\n"
    			+ "Цена:\n\tза 1 кв.м: %f р.\n"
    			+ "\tза %f кв.м: %f р.\n"
    			+ "Стоимость резки:\n\t"
    			+ "%f р.\n"
    			+ "ИТОГО: %f р.", cBoxText, mCost, sq, mCost*sq, cutCost, l, cutCost*l);
    	
    }*/
    
    @FXML
    void btExitOnAction(ActionEvent event) {
    	Stage stage = (Stage) btExit.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onCalcButtonAction(ActionEvent event) {
    	calcPrice();
    }

    @FXML
    void onCheckBoxAction(ActionEvent event) {
    	if(checkBoxM.isSelected())
    		sqContainer.setDisable(true);
    	else
    		sqContainer.setDisable(false);
    	double d = tfSquare.getText().isEmpty()?0:Double.parseDouble(tfSquare.getText());
    	calcPrice(d);
    }
    
    @FXML
    void onMListAction(ActionEvent event) {
    	//cBoxText.selectEnd();
    }

    @FXML
    void onMListKeyReleased(KeyEvent event) {
    	
    }

    @FXML
    void onTFHeightChange(KeyEvent event) {

    }

    @FXML
    void onTFSquareChange(KeyEvent event) {

    }

    @FXML
    void onTFWidthChange(KeyEvent event) {

    }

}
