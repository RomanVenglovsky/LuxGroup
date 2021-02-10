package ru.myhome.LuxGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.myhome.DataFile.DateBuilder;

public class App extends Application{
	
	@Qualifier("mainView")
    @Autowired
	private Builder.View view;
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {

		try(AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(Builder.class, DateBuilder.class)){
			context.getAutowireCapableBeanFactory().autowireBean(this);
			
			stage.setScene(new Scene(view.getView()));
			stage.initStyle(StageStyle.UTILITY);
			stage.setResizable(false);
			stage.setAlwaysOnTop(true);
			stage.show();
		}
		
	}

}
