package org.ldp.demo;

import application.Email;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import factory_method.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import singleton.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class PagamentoBicicletteController {

    public ToggleGroup paymentToggleGroup;
    public Button pagaBtn;
    public TextField nomeTextField;
    public Label nomeLabel;

    public Label cognomeLabel;

    public TextField cognomeTextField;

    public Label CfLabel;

    public TextField CfTextField;

    public Label numeroCartaLabel;
    public Label soldiLabel;
    public TextField soldiTextField;
    public TextField numeroCartaTextField;
    public Label dataScadenzaLabel;
    public TextField dataScadenzaTextField;
    public Label CVVLabel;
    public TextField cvvTextField;
    public Button pagaBtn2;
    public Button infoBtn;


    @FXML
    private RadioButton radioContanti;

    @FXML
    private RadioButton creditCardRadioButton;

    @FXML
    private RadioButton debitCardRadioButton;


    private Factory factory;

    private MetodoPagamento met;

    private Database db;

    @FXML
    private VBox parameterContainer; // Contenitore per i campi di testo dei parametri

    ArrayList<String> id_bici = new ArrayList<>();

    //private RestituzioneBicicletteController restituzioneBicicletteController = new RestituzioneBicicletteController();

    int importo;
    String filePrezzo = "prezzo.txt";
    String fileIdBici = "bici.txt";
    /***
     * Costruttore per inizializzare la finestra
     * @throws IOException
     */
    public PagamentoBicicletteController() throws IOException {};
    /***
     * Metodo per inizializzare la finestra
     * @throws IOException
     */
    public void initialize() throws IOException {
        paymentToggleGroup = new ToggleGroup();
        radioContanti.setToggleGroup(paymentToggleGroup);
        creditCardRadioButton.setToggleGroup(paymentToggleGroup);
        debitCardRadioButton.setToggleGroup(paymentToggleGroup);
        soldiTextField.setEditable(false);
        mostraImporto();
        soldiTextField.setText(String.valueOf(importo));
        startVisibility();
    }
    /***
     * Metodo per gestire il pagamento
     * @throws EccezionePersonalizzata
     * @throws IOException
     * @throws SQLException
     */
    public void handlePaymentMethod() throws EccezionePersonalizzata, IOException, SQLException {
        RadioButton selectedRadioButton = (RadioButton) paymentToggleGroup.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        if (toogleGroupValue.equals("Contanti")) {
            factory = new ContantiFactory(nomeTextField.getText(), cognomeTextField.getText(), CfTextField.getText(), importo);
            met = factory.getMetodo();
            resetInfoBici("Contanti");
            JOptionPane.showMessageDialog(null, met.paga());
        } else if (toogleGroupValue.equals("Carta di credito")) {
            factory = new CartaCreditoFactory(nomeTextField.getText(), cognomeTextField.getText(), CfTextField.getText(), numeroCartaTextField.getText(), dataScadenzaTextField.getText(), cvvTextField.getText());
            met = factory.getMetodo();
            resetInfoBici("Carta di credito");
            JOptionPane.showMessageDialog(null, met.paga());
        } else if (toogleGroupValue.equals("Bancomat")) {
            factory = new CartaCreditoFactory(nomeTextField.getText(), cognomeTextField.getText(), CfTextField.getText(), numeroCartaTextField.getText(), dataScadenzaTextField.getText(), cvvTextField.getText());
            met = factory.getMetodo();
            resetInfoBici("Bancomat");
            JOptionPane.showMessageDialog(null, met.paga());
        }
    }
    /***
     * Metodo per gestire la visibilità dei campi di testo
     */
    private void startVisibility() {
        nomeLabel.setVisible(false);
        nomeTextField.setVisible(false);
        cognomeLabel.setVisible(false);
        cognomeTextField.setVisible(false);
        CfLabel.setVisible(false);
        CfTextField.setVisible(false);
        numeroCartaLabel.setVisible(false);
        numeroCartaTextField.setVisible(false);
        soldiLabel.setVisible(false);
        soldiTextField.setVisible(false);
        dataScadenzaLabel.setVisible(false);
        dataScadenzaTextField.setVisible(false);
        CVVLabel.setVisible(false);
        cvvTextField.setVisible(false);
        pagaBtn.setVisible(false);
    }
    /***
     * Metodo per gestire la visibilità dei campi di testo
     */
    private void cashVisibility() {
        nomeLabel.setVisible(true);
        nomeTextField.setVisible(true);
        cognomeLabel.setVisible(true);
        cognomeTextField.setVisible(true);
        CfLabel.setVisible(true);
        CfTextField.setVisible(true);
        soldiLabel.setVisible(true);
        soldiTextField.setVisible(true);
        pagaBtn.setVisible(true);
    }
    /***
     * Metodo per gestire la visibilità dei campi di testo
     */
    private void cartaVisibility() {
        cashVisibility();
        numeroCartaLabel.setVisible(true);
        numeroCartaTextField.setVisible(true);
        dataScadenzaLabel.setVisible(true);
        dataScadenzaTextField.setVisible(true);
        CVVLabel.setVisible(true);
        cvvTextField.setVisible(true);

    }

    /***
     * Metodo per gestire la visibilità dei campi di testo
     * @param actionEvent
     * @throws EccezionePersonalizzata
     */
    public void handleInfoMethod(ActionEvent actionEvent) throws EccezionePersonalizzata {
        try {
            initialize();
            RadioButton selectedRadioButton = (RadioButton) paymentToggleGroup.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText();
            String paymentMethod = selectedRadioButton.getText();
            if (toogleGroupValue.equals("Contanti")) {
                cashVisibility();
            } else if (toogleGroupValue.equals("Carta di credito")) {
                cartaVisibility();
            } else if (toogleGroupValue.equals("Bancomat")) {
                cartaVisibility();
            }
        } catch (Exception e) {
            throw new EccezionePersonalizzata("Seleziona qualcosa");
        }
    }
    /***
     * Metodo per tornare alla home del cliente
     * @throws IOException
     */
    private void mostraImporto() throws IOException {

        try {
            File myObj = new File(filePrezzo);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                importo = Integer.parseInt(data);
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            File myObj = new File(fileIdBici);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                id_bici.add(data);
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /***
     * Metodo per resettare le informazioni delle bici
     * @param MetodoPagamento
     * @throws SQLException
     */
    private void resetInfoBici(String MetodoPagamento)throws SQLException{
        Database db = new Database();
        for (String id_bici : id_bici) {
            String queryGetKmEffettuati = "SELECT km_effettuati FROM Bicicletta WHERE id_bici = " + Integer.parseInt(id_bici);
            ResultSet rs = db.query(queryGetKmEffettuati);
            rs.next();
            int km_effettuati = rs.getInt("km_effettuati");
            String queryInsertPagamento = "INSERT INTO Paga (id_bici, email_cliente, km_effettuati, metodo_pagamento, importo) " +
                    "VALUES (" + Integer.parseInt(id_bici) + ", '"+Email.getIstanza().getEmail()+"',"+km_effettuati+" , '"+MetodoPagamento+"'," + importo + ")";
            db.update(queryInsertPagamento);
            String queryResetInfoBici = "UPDATE Bicicletta SET km_effettuati = 0,disponibile = true WHERE id_bici = " + Integer.parseInt(id_bici);
            db.update(queryResetInfoBici);
            String removeFromPrenota = "DELETE FROM Prenota WHERE id_bici = " + Integer.parseInt(id_bici);
            db.update(removeFromPrenota);
            String selezionaParcheggi = "SELECT id_parcheggio FROM Parcheggio";
            rs = db.query(selezionaParcheggi);
            ArrayList<Integer> parcheggio = new ArrayList<>();
            while(rs.next()){
                parcheggio.add(rs.getInt("id_parcheggio"));
            }
            int parcheggioRandom = (int)(Math.random() * parcheggio.size());
            String assegnaNuovoParcheggio= "UPDATE Bicicletta SET id_parcheggio = "+parcheggioRandom+" WHERE id_bici = " + Integer.parseInt(id_bici);
            db.update(assegnaNuovoParcheggio);
        }
    }

    /***
     * Metodo per tornare alla home del cliente
     * @param event
     * @throws IOException
     */
    public void handleReturnHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cliente/clienteHome.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}


//implementa questi 3 radio button in un unico metodo per evitare duplicazione di codice


