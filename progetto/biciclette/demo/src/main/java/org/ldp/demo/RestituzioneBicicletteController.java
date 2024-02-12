package org.ldp.demo;

import application.Email;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import singleton.Database;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class RestituzioneBicicletteController {

    public VBox listaBiciclette;
    public ListView listViewKmEffettuati;
    public ListView listViewPrezzoScontato;
    public Label labelPrezzoTotale;
    public Button btnPagamento;
    private ArrayList<String> categorieSelezionate = new ArrayList<>();
    private int prezzoTotale;

    Database db = new Database();
    Random random = new Random(100);
    String fileNamePrezzo = "prezzo.txt";
    String fileNameIdBici = "bici.txt";
    FileWriter fileWriter = new FileWriter(fileNamePrezzo);
    PrintWriter printWriter = new PrintWriter(fileWriter);
    FileWriter fileWriter2 = new FileWriter(fileNameIdBici);
    PrintWriter printWriter2 = new PrintWriter(fileWriter2);

    ArrayList<String> id_bici = new ArrayList<>();

    public RestituzioneBicicletteController() throws IOException {
    }

    public void initialize() throws Exception {
        Email.getIstanza().setEmail("c1@c1.it");

//        String queryUpdate = "UPDATE Bicicletta SET km_effettuati = "+randomNumber*100+" WHERE id_bici IN (SELECT id_bici FROM Prenota WHERE email_cliente = 'c1@c1.it')";
//        db.update(queryUpdate);
        aggiornaKmBici();
        String query = "SELECT *\n" +
                "FROM Bicicletta AS B\n" +
                "JOIN Prenota AS P ON B.id_bici = P.id_bici\n" +
                "WHERE P.email_cliente = '"+Email.getIstanza().getEmail()+"'";
        try {
            ResultSet rs = db.query(query);
            while (rs.next()) {
                int id_bici = rs.getInt("id_bici");
                System.out.println(calcolaPrezzo(rs));
                int prezzoBici = calcolaPrezzo(rs);
                CheckBox checkBox = getCheckBox(id_bici,prezzoBici);
                listaBiciclette.getChildren().add(checkBox); // Aggiunge il checkbox al VBox
                listViewKmEffettuati.getItems().add(rs.getInt("km_effettuati"));
                listViewPrezzoScontato.getItems().add(prezzoBici);
//                listaBiciclette.getChildren().add(listViewKmEffettuati);
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
    private CheckBox getCheckBox(int categoria,int prezzo) {
        CheckBox checkBox = new CheckBox(String.valueOf(categoria));
        int valore = prezzo;
        checkBox.setOnAction(event -> {
            if (checkBox.isSelected()) {
                categorieSelezionate.add(checkBox.getText());
                prezzoTotale+=valore;
                id_bici.add(checkBox.getText());
                for (String s : id_bici) {
                    System.out.println(s);
                }
               labelPrezzoTotale.setText(prezzoTotale+"€");
//                        System.out.println("prezzo totale:"+prezzoTotale);
            } else {
                categorieSelezionate.remove(checkBox.getText());
                prezzoTotale-=valore;
                id_bici.remove(checkBox.getText());
               labelPrezzoTotale.setText(prezzoTotale+"€");
//                System.out.println("prezzo totale:"+prezzoTotale);
            }
            System.out.println(categorieSelezionate);
        });
        return checkBox;
    }

    private void aggiornaKmBici() throws SQLException {
        String query = "select * from Bicicletta";
        ResultSet rs = db.query(query);
        while (rs.next()) {
            String queryUpdate = "UPDATE Bicicletta AS B\n" +
                    "JOIN Prenota AS P ON B.id_bici = P.id_bici\n" +
                    "SET B.km_effettuati = B.km_effettuati + FLOOR(RAND() * 10)\n" +
                    "WHERE P.email_cliente = '"+Email.getIstanza().getEmail()+"'";
            PreparedStatement ps = db.insert(queryUpdate);
            ps.execute();
        }
    }
    public int calcolaPrezzo(ResultSet rs)throws SQLException{
        String queryVerificaTariffa = "Select MIN(prezzo_tariffa) from Tariffa T JOIN Bicicletta B ON B.categoria_bici = T.categoria_bici WHERE B.categoria_bici ='"+rs.getString("categoria_bici")+"'";
        ResultSet rs2 = db.query(queryVerificaTariffa);
        rs2.next();
        if(rs2.next()){
            return rs.getInt("km_effettuati")*rs2.getInt("prezzo_tariffa");
        }
        else {
            return rs.getInt("km_effettuati")*5;
        }
    }

    public void pagamento(ActionEvent event) throws Exception {
        for (String s : id_bici) {
            printWriter2.println(s);
        }
        printWriter.println(prezzoTotale);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cliente/pagamentoBiciclette.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
