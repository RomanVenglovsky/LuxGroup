package ru.myhome.LuxGroup;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import ru.myhome.DataFile.CutPriceList;
import ru.myhome.DataFile.MaterialPriceList;
import ru.myhome.DataFile.MaterialsList;

@Configuration
@ComponentScan("ru.myhome.DateFile")
public class Builder {
	
	@Bean(name = "mainView")
	@Scope("singleton")
	public View getMainView() throws IOException {
		//System.out.println(getClass().getResource("mainScene.fxml") + "!");
        return loadView("mainScene.fxml");
    }
	
	@Bean(name = "mPrice")
	@Scope("singleton")
	//@Lazy
	public MaterialPriceList getMPriceList() {
		return loadDataFromExel.loadMaterialPriceList(); 
	}
	
	@Bean(name = "cutPrice")
	@Scope("singleton")
	//@Lazy
	public CutPriceList getCutPriceList() {
		return loadDataFromExel.loadCutPriceList(); 
	}
	
	@Bean(initMethod = "loadMList")
	@Scope("singleton")
	@Lazy
	public MaterialsList getMaterialList() {
		MaterialsList m = new MaterialsList();
		//m.setCutPriceList(getCutPriceList());
		if(m.getCutPriceList() == null) System.out.println("Load cut price is failed");
		//m.setmPriceList(getMPriceList());
		if(m.getmPriceList() == null) System.out.println("Load mat. price is failed");
		return  m;
	}

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его произвести все необходимые инъекции.
     */
    @Bean
    public Controller getMainController() throws IOException {
        return (Controller) getMainView().getController();
    }

    /**
     * Самый обыкновенный способ использовать FXML загрузчик.
     * Как раз-таки на этом этапе будет создан объект-контроллер,
     * произведены все FXML инъекции и вызван метод инициализации контроллера.
     */
    protected View loadView(String url) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mainScene.fxml"));
            //loader.setLocation(getClass().getResource(url));
            loader.load();
            return new View(loader.getRoot(), loader.getController());
        } finally {
            
        }
    }
    
    public class View {
        private Parent view;
        private Object controller;

        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }
	

}
