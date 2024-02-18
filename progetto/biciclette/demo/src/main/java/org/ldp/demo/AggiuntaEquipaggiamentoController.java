package org.ldp.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import singleton.Database;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AggiuntaEquipaggiamentoController {

    public ListView listViewEquipaggiamento;
    public ListView listViewEquipaggiamentoNome;
    public TextField TextFieldEquipaggiamentoNome;
    public Button btnAddEquipaggiamento;
    public TextField TextFieldEquipaggiamentoPrezzo;
    public ListView listViewEquipaggiamentoPrezzo;
    private final Database db = new Database();
    public void initialize() {
        try {
            listViewEquipaggiamentoNome.getItems().clear();
            listViewEquipaggiamentoPrezzo.getItems().clear();
            String query = "SELECT * FROM dbBike.Equipaggiamento";
            ResultSet rs = db.query(query);
            while (rs.next()) {
                listViewEquipaggiamentoNome.getItems().add(rs.getString("nome_equipaggiamento"));
                listViewEquipaggiamentoPrezzo.getItems().add(rs.getString("prezzo_equipaggiamento"));
            }
        } catch (SQLException e) {
            try {
                throw new EccezionePersonalizzata("Errore durante l'accesso al database: " + e.getMessage());
            } catch (EccezionePersonalizzata throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void addEquipaggiamento(ActionEvent actionEvent) {
        try {
            String queryMax = "select MAX(id_equipaggiamento) from dbBike.Equipaggiamento";
            ResultSet rs = db.query(queryMax);
            rs.next();
            int id_equipaggiamento = rs.getInt(1) + 1;
            String query = "INSERT INTO dbBike.Equipaggiamento (id_equipaggiamento,nome_equipaggiamento, prezzo_equipaggiamento) VALUES (?,?, ?)";
            PreparedStatement ps = db.insert(query);
            ps.setInt(1, id_equipaggiamento);
            ps.setString(2, TextFieldEquipaggiamentoNome.getText());
            ps.setString(3, TextFieldEquipaggiamentoPrezzo.getText());
            ps.executeUpdate();
            initialize();
        } catch (SQLException e) {
            try {
                throw new EccezionePersonalizzata("Errore durante l'accesso al database: " + e.getMessage());
            } catch (EccezionePersonalizzata throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void handleRetrunAdminHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin/adminHome.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
