package org.ldp.demo;

import application.Amministratore;
import builder.Bicicletta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import singleton.Database;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class AggiuntaBicicletteController extends Amministratore {

    public TextField prezzoField;
    @FXML
    private TextField idField;

    @FXML
    private TextField tipoField;

    @FXML
    private TextField parcheggioIdField;

    Database db = new Database();
    @FXML
    /***
     * Metodo per aggiungere una bicicletta al parcheggio
     * @throws SQLException
     * @throws EccezionePersonalizzata
     */
    public void aggiungiBiciclettaAParcheggio() throws SQLException, EccezionePersonalizzata {

        int id_bici = parseInt(idField.getText());
        String categoria_bici = tipoField.getText();
        int id_parcheggio = parseInt(parcheggioIdField.getText());
        int prezzo_bici = parseInt(prezzoField.getText());
        Bicicletta bicicletta = new Bicicletta.BiciclettaBuilder(id_bici, categoria_bici)
                .prezzo(prezzo_bici)
                .idParcheggio(id_parcheggio)
                .build();
        String query = "INSERT INTO dbBike.Bicicletta (id_bici, categoria_bici, prezzo_bici, id_parcheggio,disponibile,km_effettuati) VALUES (?, ?, ?,?,1,0)";
        PreparedStatement preparedStatement = db.insert(query);

        preparedStatement.setInt(1, bicicletta.getId());
        preparedStatement.setString(2, bicicletta.getCategoria());
        preparedStatement.setInt(3, bicicletta.getPrezzo());
        preparedStatement.setInt(4, bicicletta.getIdParcheggio());
        try {
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "\nCliente registrato correttamente!");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EccezionePersonalizzata("\nErrore durante l'inserimento," + e.getMessage());
        }
    }

    /***
     * Metodo per tornare alla home dell'amministratore
     * @param event
     * @throws IOException
     */

    public void handleRetrunAdminHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin/adminHome.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
