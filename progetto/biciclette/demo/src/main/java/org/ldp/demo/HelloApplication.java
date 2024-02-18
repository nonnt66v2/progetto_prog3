package org.ldp.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    /***
     * Metodo per inizializzare la finestra con i dati presenti nel database
     * @throws SQLException
     * @throws IOException
     * @throws EccezionePersonalizzata
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public void start(Stage stage) throws IOException, RuntimeException, SQLException {
 //      FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cliente/restituzioneBiciclette.fxml"));
//        Email.getIstanza().setEmail("c1@c1.it");
//       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cliente/restituzioneBiciclette.fxml"));
       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login/login.fxml"));
//

        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Biciclette");
        stage.setScene(scene);
        stage.show();
    }

    /***
     * Metodo per inizializzare la finestra con i dati presenti nel database
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}
