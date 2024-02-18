package org.ldp.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import singleton.Database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class PercentualeUtilizzoController {

    public Label labelPercentualeUtilizzoCategoria;
    public ChoiceBox<String> biciclettaChoiceBox;
    public Label labelPercentualeUtilizzoBicicletta;
    @FXML
    private ChoiceBox<String> categoriaChoiceBox;
    Database db = new Database();

    public void initialize() throws SQLException {
        inizializzaCategorie();
        inizializzaBicilette();
    }
    private void inizializzaCategorie(){
        try {
            String queryCategoria = "SELECT DISTINCT categoria_bici FROM Bicicletta";
            ResultSet rs = db.query(queryCategoria);
            while (rs.next()) {
                categoriaChoiceBox.getItems().add(rs.getString("categoria_bici"));
            }
            categoriaChoiceBox.setValue("Mountain Bike");
            categoriaChoiceBox.setOnAction(event -> {
                String categoriaSelezionata = categoriaChoiceBox.getValue();
                String queryPercentualeUtilizzoCategoria = "SELECT *\n" +
                        "FROM (\n" +
                        "    SELECT\n" +
                        "        B.categoria_bici,\n" +
                        "        SUM(B.km_effettuati) / (SELECT SUM(km_effettuati) FROM Bicicletta) * 100 AS percentuale_utilizzo\n" +
                        "    FROM\n" +
                        "        Bicicletta B\n" +
                        "    GROUP BY\n" +
                        "        B.categoria_bici\n" +
                        ") AS risultati\n" +
                        "WHERE\n" +
                        "    risultati.categoria_bici = '"+categoriaSelezionata+"';\n";
                try {
                    ResultSet rsPercentualeUtilizzoCategoria = db.query(queryPercentualeUtilizzoCategoria);
                    while (rsPercentualeUtilizzoCategoria.next()) {
                        labelPercentualeUtilizzoCategoria.setText(rsPercentualeUtilizzoCategoria.getString("percentuale_utilizzo")+"%");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    private void inizializzaBicilette(){
        try {
            String queryBicicletta = "SELECT id_bici FROM Bicicletta";
            ResultSet rs2 = db.query(queryBicicletta);
            while (rs2.next()) {
                biciclettaChoiceBox.getItems().add(rs2.getString("id_bici"));
            }
            biciclettaChoiceBox.setValue("1");
            biciclettaChoiceBox.setOnAction(event -> {
                String queryPercentualeUtilizzoBicicletta = getString();
                try {
                    ResultSet rs2PercentualeUtilizzoBicicletta = db.query(queryPercentualeUtilizzoBicicletta);
                    while (rs2PercentualeUtilizzoBicicletta.next()) {
                        labelPercentualeUtilizzoBicicletta.setText(rs2PercentualeUtilizzoBicicletta.getString("percentuale_utilizzo")+"%");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getString() {
        String biciSelezionata = biciclettaChoiceBox.getValue();
        return "SELECT *\n" +
                "FROM (\n" +
                "    SELECT\n" +
                "        B.id_bici,\n" +
                "        SUM(B.km_effettuati) / (SELECT SUM(km_effettuati) FROM Bicicletta) * 100 AS percentuale_utilizzo\n" +
                "    FROM\n" +
                "        Bicicletta B\n" +
                "    GROUP BY\n" +
                "        B.id_bici\n" +
                ") AS risultati\n" +
                "WHERE\n" +
                "    risultati.id_bici = "+biciSelezionata+";\n";
    }

    public void handleRetrunAdminHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin/adminHome.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

