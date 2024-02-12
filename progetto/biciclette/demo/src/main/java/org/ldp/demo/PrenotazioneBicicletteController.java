package org.ldp.demo;

import Mediator.ClienteReal;
import Mediator.Prenota;
import application.Cliente;
import application.Email;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import singleton.Database;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrenotazioneBicicletteController {
    public ListView listViewCategoria;
    public TextField textFieldCategoria;
    public TextField textFieldOrario;
    public Button btnSMS;
    public Button btnEmail;
    public VBox vBoxcheckboxContainer;
    Database db = new Database();

    private JCheckBox[] checkBoxes;
    private ArrayList<String> categorieSelezionate = new ArrayList<>();


    public void initialize() {
        clearAll();
        aggiungiCheckBoxes();
        try {

            listViewCategoria.getItems().clear();
            String query = "SELECT DISTINCT categoria_bici FROM Bicicletta B JOIN Parcheggio P ON B.id_parcheggio = P.id_parcheggio WHERE disponibile = true";
            ResultSet rs = db.query(query);
            while (rs.next()) {
//                listViewEquipaggiamentoNome.getItems().add(rs.getString("nome_equipaggiamento"));
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

    private void clearAll() {
        textFieldOrario.clear();
        textFieldCategoria.clear();
        listViewCategoria.getItems().clear();
        vBoxcheckboxContainer.getChildren().clear();
    }

    public void inviaSMS(ActionEvent actionEvent) {
        Prenota p = new Prenota();
        Cliente cliente = new ClienteReal(p);
        p.aggiungiCliente(cliente);
        try {
            cliente.inviaRichiesta("SMS", textFieldCategoria.getText(), textFieldOrario.getText());
        } catch (EccezionePersonalizzata | SQLException eccezionePersonalizzata) {
            eccezionePersonalizzata.printStackTrace();
        }
        eseguiQuery(p);

    }

    public void inviaEmail(ActionEvent actionEvent) {
        Prenota p = new Prenota();
        Cliente cliente = new ClienteReal(p);
        p.aggiungiCliente(cliente);
        try {
            cliente.inviaRichiesta("Email", textFieldCategoria.getText(), textFieldOrario.getText());
        } catch (EccezionePersonalizzata | SQLException eccezionePersonalizzata) {
            eccezionePersonalizzata.printStackTrace();
        }
        eseguiQuery(p);

    }

    private void eseguiQuery(Prenota p) {
        if (p.getEsitoPrenotazione()) {
            String querySelectBici = "SELECT * FROM dbBike.Bicicletta WHERE categoria_bici = '" + textFieldCategoria.getText() +
                    "' AND disponibile = true";
            try {
                ResultSet rs = db.query(querySelectBici);
                rs.next();
                int idBici = rs.getInt("id_bici");
                for (String x : categorieSelezionate) {

                    String queryAggiungiEquipaggiamento = "INSERT INTO Possiede (id_bici, id_equipaggiamento)\n" +
                            "SELECT B.id_bici, E.id_equipaggiamento\n" +
                            "FROM Bicicletta B, Equipaggiamento E\n" +
                            "WHERE B.id_bici = ? AND E.nome_equipaggiamento = ?;\n";
                    PreparedStatement ps = db.insert(queryAggiungiEquipaggiamento);
                    ps.setInt(1, idBici);
                    ps.setString(2, x);
                    ps.executeUpdate();

                }
                String queryInsertPrenota = "INSERT INTO Prenota (email_cliente, id_bici, ora_inizio_prenota)" +
                        "VALUES (?, ?, ?)";
                PreparedStatement ps = db.insert(queryInsertPrenota);
                ps.setString(1, Email.getIstanza().getEmail());
                ps.setInt(2, idBici);
                ps.setString(3, textFieldOrario.getText() + ":00");
                ps.executeUpdate();
                String queryRemoveFormParcheggio = "UPDATE Bicicletta " +
                        "SET disponibile = false " +
                        "WHERE id_bici = ?";
                PreparedStatement ps2 = db.insert(queryRemoveFormParcheggio);
                ps2.setInt(1, idBici);
                ps2.executeUpdate();
                initialize();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Errore durante l'accesso al database: " + e.getMessage());
                throw new RuntimeException(e);
            }

        }
    }

    void aggiungiCheckBoxes() {
        try {
            String query = "SELECT * FROM dbBike.Equipaggiamento";
            ResultSet rs = db.query(query);
            while (rs.next()) {
                String categoria = rs.getString("nome_equipaggiamento");
                CheckBox checkBox = getCheckBox(categoria);
                vBoxcheckboxContainer.getChildren().add(checkBox); // Aggiunge il checkbox al VBox
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CheckBox getCheckBox(String categoria) {
        CheckBox checkBox = new CheckBox(categoria);
        checkBox.setOnAction(event -> {
            if (checkBox.isSelected()) {
                categorieSelezionate.add(checkBox.getText());
//                        System.out.println(checkBox.getText() + " is selected");
            } else {
                categorieSelezionate.remove(checkBox.getText());
//                        System.out.println(checkBox.getText() + " is not selected");
            }
            System.out.println(categorieSelezionate);
        });
        return checkBox;
    }

}
