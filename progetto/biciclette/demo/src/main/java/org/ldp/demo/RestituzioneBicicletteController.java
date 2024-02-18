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

import java.io.*;
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
//    FileWriter fileout = new FileWriter(fileNamePrezzo);
//    // ... che incapsulo in un BufferedWriter...
//    BufferedWriter filebuf = new BufferedWriter(fileout);
//    // ... che incapsulo in un PrintWriter
//    PrintWriter printout = new PrintWriter(filebuf);

    ArrayList<String> id_bici = new ArrayList<>();

    public void setPrezzoTotale(int prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }
    public int getPrezzoTotale() {
        return prezzoTotale;
    }

    public ArrayList<String> getId_bici() {
        return id_bici;
    }

   public RestituzioneBicicletteController() throws IOException {
    }
    /***
     * @throws Exception
     */
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
    /***
     * @param categoria
     * @return
     */
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
    /***
     * @param categoria
     * @param prezzo
     * @return
     */
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


    /***
     * Aggiorna i km effettuati delle bici
     * @throws SQLException
     */
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

    /***
     * @param rs
     * @return
     * @throws SQLException
     */
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

    /***
     * @param event
     * @throws Exception
     */
    public void pagamento(ActionEvent event) throws Exception {
        /***
         * 1. Calcola il prezzo totale
         * 2. Salva il prezzo totale in un file
         * 3. Salva gli id delle bici in un file
         * 4. Mostra la schermata di pagamento
         */

        //scrivi il prezzo totale in un file
        try {
            File myObj = new File(fileNamePrezzo);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //scrittura su un file
        try {
            FileWriter myWriter = new FileWriter(fileNamePrezzo);
            myWriter.write(String.valueOf(prezzoTotale));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //scrivi gli id delle bici in un file
        try {
            File myObj = new File(fileNameIdBici);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //scrittura su un file
        try {
            FileWriter myWriter = new FileWriter(fileNameIdBici);
            for (String s : id_bici) {
                myWriter.write(s);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
        //mostra la schermata di pagamento
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cliente/pagamentoBiciclette.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleRetrunAdminHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cliente/clienteHome.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
