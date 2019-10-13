package com.bettercoding.jfx;

import com.bettercoding.jfx.controller.UsuarioController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyApp extends Application {
     @FXML
    private AnchorPane pai;
    public static ConfigurableApplicationContext springContext;
    private Parent rootNode;
    private FXMLLoader fxmlLoader;
    private static Scene cenaLogin;
    private  Stage stage;
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
