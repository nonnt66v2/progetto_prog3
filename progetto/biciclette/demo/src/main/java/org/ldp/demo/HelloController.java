package org.ldp.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    public Button showAddBike;
    public Button showAddEquip;
    public Button btnAddOrUpdateTariffa;
    public Button showAddOrUpdateTariffa;
    public Button btnPercentualeUtilizzo;
    public Button btnPrenotaBici;
    public Button btnRestituisciBici;
    public Button btnLogout;
    /***
     * Metodo per mostrare la finestra di aggiunta biciclette
     * @param event
     * @throws IOException
     */
    public void showAddBike(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin/aggiuntaBiciclette.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /***
     * Metodo per mostrare la finestra di aggiunta equipaggiamento
     * @param event
     * @throws IOException
     */
    public void showAddEquip(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin/aggiuntaEquipaggiamento.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /***
     * Metodo per mostrare la finestra di aggiunta o modifica tariffa
     * @param event
     * @throws IOException
     */

    public void showUpdateTariffa(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin/aggiuntaTariffa.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /***
     * Metodo per mostrare la finestra di prenotazione biciclette
     * @param event
     * @throws IOException
     */
    public void prenotaBici(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cliente/prenotazioneBiciclette.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /***
     * Metodo per mostrare la finestra di percentuale utilizzo
     * @param event
     * @throws IOException
     */
    public void showPercentualeUtilizzo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin/percentualeUtilizzo.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        /***
         * Metodo per mostrare la finestra di restituzione biciclette
         * @param event
         * @throws IOException
         */
    public void restituisciBici(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cliente/restituzioneBiciclette.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        /***
         * Metodo per mostrare la finestra di logout
         * @param event
         * @throws IOException
         */

    public void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login/login.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}