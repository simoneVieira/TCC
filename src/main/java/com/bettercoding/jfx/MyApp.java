package com.bettercoding.jfx;

import com.bettercoding.jfx.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyApp extends Application {
    private ConfigurableApplicationContext springContext;
    private Parent rootNode;
    private FXMLLoader fxmlLoader;
    private static Scene cenaLogin;
    private static Stage stage;
    private static Scene scene;
    LoginController l = new LoginController();

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
       // fxmlLoader.setLocation(getClass().getResource("/fxml/sample.fxml"));
        // rootNode = fxmlLoader.load();
      Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        cenaLogin = new Scene(root);

        primaryStage.setTitle("Carteira");
//        Scene scene = new Scene(rootNode, 800, 600);
        primaryStage.setScene(cenaLogin);
        primaryStage.show();
       
      //primaryStage.close();
      
     
    }
    public void validaLogin(){
        //if(l.getIdUsuario().getText().equals("simone") &&(l.getIdSenha().getText().equals("123"))){
          // Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           //alert.setTitle("rodou");
           //alert.setHeaderText("bem vindaaaaaaaaaa");
           //alert.show();
      //  }else{
             //System.out.println ("erraadaddaadad");
      // }
    }
   
     public static void changescren(String src) {
        switch (src) {
            case "scene":
                stage.setScene(scene);
               break;
           // case"cenaLogin":
             //  stage.setScene(cenaLogin);
            // break;
            //case"cenaCliente":
               // stage.setScene(cenaCliente);
        }
     }
    
   // @Override
   // public void stop() {
     //  springContext.stop();
    //}

    
   


}

