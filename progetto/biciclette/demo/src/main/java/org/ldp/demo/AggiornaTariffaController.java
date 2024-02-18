package org.ldp.demo;

import application.Amministratore;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import singleton.Database;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AggiornaTariffaController extends Amministratore {


    public ListView listViewNome;
    public ListView listViewOraInizio;
    public ListView listViewPrezzo;
    public ListView listViewOraFine;
    public ListView listViewCategoria;


    public TextField textViewNome;
    public TextField textViewOraInizio;
    public TextField textViewOraFine;
    public TextField textViewPrezzo;
    public TextField textViewCategoria;
    public Button btnAggiornaTariffa;
    Database db = new Database();

    public void initialize() {
        try {
            listViewCategoria.getItems().clear();
            listViewNome.getItems().clear();
            listViewOraInizio.getItems().clear();
            listViewOraFine.getItems().clear();
            listViewPrezzo.getItems().clear();
            String query = "SELECT * FROM dbBike.Tariffa";
            ResultSet rs = db.query(query);
            while (rs.next()) {
                listViewNome.getItems().add(rs.getString("nome_tariffa"));
                listViewOraInizio.getItems().add(rs.getString("orario_inizio_tariffa"));
                listViewOraFine.getItems().add(rs.getString("orario_fine_tariffa"));
                listViewPrezzo.getItems().add(rs.getString("prezzo_tariffa"));
                listViewCategoria.getItems().add(rs.getString("categoria_bici"));
            }
        } catch (SQLException e) {
            try {
                throw new EccezionePersonalizzata("Errore durante l'accesso al database: " + e.getMessage());
            } catch (EccezionePersonalizzata throwables) {
                throwables.printStackTrace();
            }
        }
    }

    int result;

    public void aggiornaTariffa() throws SQLException {
        System.out.println(textViewNome.getText());
        String queryCheck = "SELECT * FROM dbBike.Tariffa WHERE nome_tariffa = '" + textViewNome.getText() + "'";
        ResultSet rs = db.query(queryCheck);
        if (rs.next()) {
            System.out.println("Tariffa già presente, aggiorno il prezzo");
            try {
                //String queryUpdate = "UPDATE dbBike.Tariffa SET prezzo_tariffa = ?,orario_inizio_tariffa = ?, orario_fine_tariffa = ?, categoria_bici = ? WHERE nome_tariffa = ?";
                String queryUpdate = " UPDATE dbBike.Tariffa  SET prezzo_tariffa = ?,orario_inizio_tariffa = ?, orario_fine_tariffa = ? , prezzo_offerta = ? Where nome_tariffa = ?";

                PreparedStatement ps = db.insert(queryUpdate);
                ps.setString(5, textViewNome.getText());
                ps.setString(2, textViewOraInizio.getText() + ":00");
                ps.setString(3, textViewOraFine.getText() + ":00");
                ps.setString(4, textViewCategoria.getText());
                ps.setString(1, textViewPrezzo.getText());
                ps.execute();
            } catch (SQLException e) {
                try {
                    throw new EccezionePersonalizzata("Errore durante l'accesso al database: " + e.getMessage());
                } catch (EccezionePersonalizzata throwables) {
                    throwables.printStackTrace();
                }
            }
        } else {
            System.out.println("Tariffa non presente, inserisco la nuova tariffa");
                String queryInsert = "INSERT INTO dbBike.Tariffa (nome_tariffa, orario_inizio_tariffa, orario_fine_tariffa, categoria_bici,prezzo_tariffa) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = db.insert(queryInsert);
                ps.setString(1, textViewNome.getText());
            ps.setString(2, textViewOraInizio.getText() + ":00");
            ps.setString(3, textViewOraFine.getText() + ":00");
                ps.setString(4, textViewCategoria.getText());
                ps.setString(5, textViewPrezzo.getText());
                ps.execute();
                        }
        initialize();

    }

    public void handleRetrunAdminHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin/adminHome.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
