package org.ldp.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, RuntimeException, SQLException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cliente/pagamentoBiciclette.fxml"));
//        Email.getIstanza().setEmail("c1@c1.it");
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cliente/restituzioneBiciclette.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login/login.fxml"));
//

        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("LOGIN!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
