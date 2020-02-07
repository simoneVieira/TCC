package com.pacote.jfx;

import com.pacote.jfx.controller.UsuarioController;
import java.util.TimeZone;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.yuequan.jpa.soft.delete.repository.EnableJpaSoftDeleteRepositories;

@EnableJpaSoftDeleteRepositories
@SpringBootApplication
public class MyApp extends Application {

    @FXML
    private AnchorPane pai;
    public static ConfigurableApplicationContext springContext;
    private Parent rootNode;
    private FXMLLoader fxmlLoader;
    private static Scene cenaLogin;
    private Stage stage;
    private static Scene scene;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(MyApp.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        
    }
//    @PostConstruct
//    public void started(){
//       // TimeZone.setDefault(TimeZone.getTimeZone("Etc/GMT-2"));
//    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root;
        
        FXMLLoader fxml = new FXMLLoader();
        fxml.setControllerFactory(MyApp.springContext::getBean);
        fxml.setLocation(getClass().getResource("/fxml/login.fxml"));
        
        root = fxml.load();
        
        cenaLogin = new Scene(root);
        
        primaryStage.setTitle("TELA LOGIN");
        
        primaryStage.setScene(cenaLogin);
        
        primaryStage.show();
        
    }
    
}
