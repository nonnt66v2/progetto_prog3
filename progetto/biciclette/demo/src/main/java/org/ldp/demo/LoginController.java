package org.ldp.demo;

import application.Amministratore;
import application.Cliente;
import application.Email;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import singleton.Database;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController {

    public Button btnPrenotaBici;
    @FXML
    Cliente cliente;
    /**
     * Variabile istanza privata che rappresenta la risorsa di bundle associata al file FXML e può essere utilizzata
     * per localizzare stringhe e altre risorse in base alla localizzazione dell'applicazione.
     */
    @FXML
    private ResourceBundle resources;

    /**
     * Variabile istanza privata che rappresenta l'URL del file FXML che è stato dato al caricatore FXML durante
     * il processo di caricamento.
     */
    @FXML
    private URL location;

    /**
     * Variabili istanza private, che rappresentano gli oggetti di tipo Button creati tramite Scene Builder.
     */
    @FXML // fx:id="cliente"
    private Button clienteBtn;

    @FXML // fx:id="amministratore"
    private Button amministratore;

    @FXML // fx:id="loginAdmin"
    private Button loginAdmin;

    @FXML // fx:id="loginUser"
    private Button loginUser;

    @FXML // fx:id="register"
    private Button register;

    @FXML // fx:id="accAdmin"
    private Button accAdmin;

    @FXML // fx:id="accUser"
    private Button accUser;

    @FXML // fx:id="regUser"
    private Button regUser;

    @FXML // fx:id="cliente"
    private Button back;

    @FXML // fx:id="email"
    private TextField email;

    @FXML // fx:id="password"
    private PasswordField password;

    @FXML // fx:id="nome"
    private TextField nome;

    @FXML // fx:id="cognome"
    private TextField cognome;

    @FXML // fx:id="pane"
    private SplitPane pane;

    @FXML // fx:id="label"
    private Label label;

    private Database db;

    /**
     * Metodo chiamato dal FXMLLoader quando l'inizializzazione è completa e lo utilizzo per settare effetti di tipo
     * hover sui vari bottoni, oltre che per settare come cursore la manina invece che la freccetta classica.
     */
    @FXML
    void initialize() {

        /**
         * Clausola try-catch per provare ad effettuare la connessione al database.
         */
        try {
            db = new Database();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo per settare vari effetti quando si effettua un hover su un bottone.
     */
    private void ammMouseEntered(Button button) {
        DropShadow shadow = new DropShadow();

        button.setOnMouseEntered(e -> {
            button.setEffect(shadow);
            button.setStyle("-fx-background-color: #8f6500;");
            button.setCursor(Cursor.HAND);
        });
    }

    /**
     * Metodo per settare vari effetti quando con il cursore ci spostiamo fuori dall'area relativa ad un bottone.
     */
    private void ammMouseExited(Button button) {
        button.setOnMouseExited(e -> {
            button.setEffect(null);
            button.setStyle("-fx-background-color: #f4ad00;");
            button.setCursor(Cursor.DEFAULT);
        });
    }

    private void cliMouseEntered(Button button) {
        DropShadow shadow = new DropShadow();

        button.setOnMouseEntered(e -> {
            button.setEffect(shadow);
            button.setStyle("-fx-background-color: #3b4366;");
            button.setCursor(Cursor.HAND);
        });
    }

    private void cliMouseExited(Button button) {
        button.setOnMouseExited(e -> {
            button.setEffect(null);
            button.setStyle("-fx-background-color: #0b1541;");
            button.setCursor(Cursor.DEFAULT);
        });
    }

    /**
     * Metodo che permette di rendere visibili solo le operazioni relative all'amministratore.
     */
    @FXML
    void handleAmministratore(ActionEvent event) {
        loginAdmin.setVisible(true);
        loginUser.setVisible(false);
        register.setVisible(false);
    }

    /**
     * Metodo che permette di rendere visibili solo le operazioni relative al cliente.
     */
    @FXML
    void handleCliente(ActionEvent event) {
        loginAdmin.setVisible(false);
        loginUser.setVisible(true);
        register.setVisible(true);
    }

    /**
     * Metodo che permette di rendere visibili i campi di testo relativi ai dati da inserire per eseguire l'accesso
     * come amministratore.
     *
     * @param event evento associato all'interfaccia utente, che scaturisce la chiamata a questo metodo
     */
    @FXML
    void handleLoginAdmin(ActionEvent event) {
        pane.setVisible(true);
        back.setVisible(true);
        accAdmin.setVisible(true);
    }

    /**
     * Metodo che permette di rendere visibili i campi di testo relativi ai dati da inserire per eseguire l'accesso
     * come cliente.
     *
     * @param event evento associato all'interfaccia utente, che scaturisce la chiamata a questo metodo
     */
    @FXML
    void handleLoginUser(ActionEvent event) {
        pane.setVisible(true);
        back.setVisible(true);
        accUser.setVisible(true);
        loginUser.setVisible(false);
        register.setVisible(false);
        label.requestFocus();
    }

    /**
     * Metodo che permette di far comparire i campi di testo, TextField, relativi ai dati da immettere per
     * la registrazione di un nuovo cliente.
     *
     * @param event evento associato all'interfaccia utente, che scaturisce la chiamata a questo metodo
     */
    @FXML
    void handleRegister(ActionEvent event) {
        pane.setVisible(true);
        back.setVisible(true);
        regUser.setVisible(true);
        loginUser.setVisible(false);
        register.setVisible(false);
        nome.setVisible(true);
        cognome.setVisible(true);
        label.requestFocus();
    }

    /**
     * Metodo che si occupa della gestione dell'operazione di login degli amministratori.
     *
     * @param event evento associato all'interfaccia utente, che scaturisce la chiamata a questo metodo
     * @throws Exception eccezioni di qualsiasi tipo
     */
    @FXML
    void handleAccAdmin(ActionEvent event) throws Exception {
        Amministratore a = new Amministratore();
        a.setEmail(email.getText());
        a.setPassword(password.getText());
        String em, pass;
        em = email.getText();
        pass = password.getText();

        /**
         * Se i campi di testo relativi all'email o alla password sono vuoti, allora viene generata un'eccezione,
         * con un messaggio in sovraschermo.
         */
        if (em.isEmpty() || pass.isEmpty()) {
            password.clear();
            throw new EccezionePersonalizzata("\nErrore durante l'accesso, compilare tutti i campi!");
        }

        /**
         * Se nel campo email non è contenuto il carattere "@" significa che non si tratta di una vera email.
         */
        if (!em.contains("@")) {
            password.clear();
            throw new EccezionePersonalizzata("\nIl campo email non contiene la @!");
        }

        /**
         * Controllo che il campo email non abbia lettere maiuscole al suo interno, poichè sarebbe errato.
         */
        for (char carattere : em.toCharArray()) {
            if (Character.isUpperCase(carattere)) {
                password.clear();
                throw new EccezionePersonalizzata("\nIl campo email non può avere lettere maiuscole!");
            }
        }

        /**
         * Effettuo una query per controllare se l'email e la password inseriti corrispondono a delle credenziali
         * presenti nella tabella amministratore. Per quanto riguarda la password la avvolgo nella funzione di hashing
         * SHA2 in quanto il campo passwordAdmin nel database è stato crittografato tramite SHA2.
         */
        ResultSet rs = db.query("select * from dbBike.Amministratore where email_admin='" + em + "' and" + " password_admin=SHA2('" + pass + "',256)");

        /**
         * Se esiste una corrispondenza nella tabella amministratore del database si procede al caricamento della scena
         * relativa alla dashboard dell'amministratore.
         */
        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "\nCredenziali valide, accesso consentito!");
            Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin/aggiuntaBiciclette.fxml")));
            /**
             * Creo una nuova scena, a partire dal layout dell'interfaccia utente appena memorizzato nell'oggetto
             * di tipo Parent.
             */
            Scene tableViewScene = new Scene(tableViewParent);
            /**
             * Si crea un nuovo oggetto di tipo Stage, il quale è inizializzato alla finestra corrente associata all'evento.
             * In particolare, a partire dall'oggetto event di tipo ActionEvent si ottiene il nodo sorgente, tramite metodo
             * getSource, dopodichè si risale alla scena, tramite metodo getScene, e alla finestra associata a tale nodo,
             * tramite metodo getWindow.
             */
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            /**
             * Sostituisco la scena attuale con la scena appena creata, che avrà il layout dell'interfaccia utente caricato
             * da "adminHome.fxml".
             */
            window.setScene(tableViewScene);
            window.show();
        } else {
            password.clear();
            throw new EccezionePersonalizzata("\nCredenziali non valide, accesso negato!");
        }
    }

    /**
     * Metodo che si occupa della gestione dell'operazione di login dei clienti.
     *
     * @param event evento associato all'interfaccia utente, che scaturisce la chiamata a questo metodo
     * @throws Exception eccezioni di qualsiasi tipo
     */
    @FXML
    void handleAccUser(ActionEvent event) throws Exception {

        String em, pass;

        em = email.getText();
        pass = password.getText();
        cliente = new Cliente(em, pass);
        /**
         * Se i campi di testo relativi all'email o alla password sono vuoti, allora viene generata un'eccezione,
         * con un messaggio in sovraschermo.
         */
        if (em.isEmpty() || pass.isEmpty()) {
            password.clear();
            throw new EccezionePersonalizzata("\nErrore durante l'accesso, compilare tutti i campi!");
        }

        /**
         * Se nel campo email non è contenuto il carattere "@" significa che non si tratta di una vera email.
         */
        if (!em.contains("@")) {
            password.clear();
            throw new EccezionePersonalizzata("\nIl campo email non contiene la @!");
        }

        /**
         * Controllo che il campo email non abbia lettere maiuscole al suo interno, poichè sarebbe errato.
         */
        for (char carattere : em.toCharArray()) {
            if (Character.isUpperCase(carattere)) {
                password.clear();
                throw new EccezionePersonalizzata("\nIl campo email non può avere lettere maiuscole!");
            }
        }

        /**
         * Effettuo una query per controllare se l'email e la password inseriti corrispondono a delle credenziali
         * presenti nella tabella cliente. Per quanto riguarda la password la avvolgo nella funzione di hashing
         * SHA2 in quanto il campo passwordCliente nel database è stato crittografato tramite SHA2.
         */
        ResultSet rs = db.query("select * from dbBike.Cliente where email_cliente='" + em + "' and" + " password_cliente=SHA2('" + pass + "',256)");

        /**
         * Se esiste una corrispondenza allora il cliente viene reindirizzato alla sua dashboard.
         */
        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "\nCredenziali valide, accesso consentito!");
            /**
             * Memorizzo l'email dell'utente acceduto per future operazioni relative a inserimenti nelle tabelle nelle
             * quali compaiono carrello e vendite.
             */
            Email e = Email.getIstanza();
            e.setEmail(em);

            Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cliente/clienteHome.fxml")));
            /**
             * Creo una nuova scena, a partire dal layout dell'interfaccia utente appena memorizzato nell'oggetto
             * di tipo Parent.
             */
            Scene tableViewScene = new Scene(tableViewParent);
            /**
             * Si crea un nuovo oggetto di tipo Stage, il quale è inizializzato alla finestra corrente associata all'evento.
             * In particolare, a partire dall'oggetto event di tipo ActionEvent si ottiene il nodo sorgente, tramite metodo
             * getSource, dopodichè si risale alla scena, tramite metodo getScene, e alla finestra associata a tale nodo,
             * tramite metodo getWindow.
             */
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            /**
             * Sostituisco la scena attuale con la scena appena creata, che avrà il layout dell'interfaccia utente caricato
             * da "adminHome.fxml".
             */
            window.setScene(tableViewScene);
            window.show();
        }
        /**
         * Se non esiste una corrispondenza allora viene generata un'eccezione ed il cliente non può accedere.
         */
        else {
            password.clear();
            throw new EccezionePersonalizzata("\nCredenziali non valide, accesso negato!");
        }
    }

    /**
     * Metodo che si occupa della gestione dell'operazione di registrazione dei clienti.
     *
     * @param event evento associato all'interfaccia utente, che scaturisce la chiamata a questo metodo
     * @throws Exception eccezioni di qualsiasi tipo
     */
    @FXML
    void handleRegUser(ActionEvent event) throws Exception {
        String em, pass, n, c;
        em = email.getText();
        pass = password.getText();
        n = nome.getText();
        c = cognome.getText();
        cliente = new Cliente(em, n, c, pass);


        /**
         * Se almeno uno dei campi di testo è vuoto, allora viene generata un'eccezione,
         * con un messaggio in sovraschermo.
         */
        if (em.isEmpty() || pass.isEmpty() || n.isEmpty() || c.isEmpty()) {
            password.clear();
            nome.clear();
            cognome.clear();
            throw new EccezionePersonalizzata("\nErrore durante l'accesso, compilare tutti i campi!");
        }

        /**
         * Se nel campo email non è contenuto il carattere "@" significa che non si tratta di una vera email.
         */
        if (!em.contains("@")) {
            password.clear();
            nome.clear();
            cognome.clear();
            throw new EccezionePersonalizzata("\nIl campo email non contiene la @!");
        }

        /**
         * Controllo che il campo email non abbia lettere maiuscole al suo interno, poichè sarebbe errato.
         */
        for (char carattere : em.toCharArray()) {
            if (Character.isUpperCase(carattere)) {
                password.clear();
                nome.clear();
                cognome.clear();
                throw new EccezionePersonalizzata("\nIl campo email non può avere lettere maiuscole!");
            }
        }

        /**
         * Effettuo l'inserimento nel database del nuovo cliente che si sta registrando, avvolgendo la password nella
         * funzione di hashing SHA2.
         */
        String q = "insert into dbBike.Cliente (email_cliente, nome_cliente, cognome_cliente,password_cliente)" + " values (?,?,?,SHA2(?,256))";

        PreparedStatement preparedStmt = db.insert(q);

        preparedStmt.setString(1, em);
        preparedStmt.setString(2, n);
        preparedStmt.setString(3, c);
        preparedStmt.setString(4, pass);

        /**
         * Controllo che non si verifichino violazioni dei vincoli di primary key e di foreign key.
         */
        try {
            preparedStmt.execute();
            JOptionPane.showMessageDialog(null, "\nCliente registrato correttamente!");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EccezionePersonalizzata("\nErrore durante l'inserimento, esiste già un cliente con" + " questa email!");
        } finally {
            email.clear();
            password.clear();
            nome.clear();
            cognome.clear();
        }

        /**
         * Memorizzo l'email dell'utente acceduto per future operazioni relative a inserimenti nelle tabelle nelle
         * quali compaiono carrello e vendite.
         */
        Email e = Email.getIstanza();
        e.setEmail(em);

        Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cliente/clienteHome.fxml")));
        /**
         * Creo una nuova scena, a partire dal layout dell'interfaccia utente appena memorizzato nell'oggetto
         * di tipo Parent.
         */
        Scene tableViewScene = new Scene(tableViewParent);
        /**
         * Si crea un nuovo oggetto di tipo Stage, il quale è inizializzato alla finestra corrente associata all'evento.
         * In particolare, a partire dall'oggetto event di tipo ActionEvent si ottiene il nodo sorgente, tramite metodo
         * getSource, dopodichè si risale alla scena, tramite metodo getScene, e alla finestra associata a tale nodo,
         * tramite metodo getWindow.
         */
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(false);
        /**
         * Sostituisco la scena attuale con la scena appena creata, che avrà il layout dell'interfaccia utente caricato
         * da "adminHome.fxml".
         */
        window.setScene(tableViewScene);
        window.show();

    }

    /**
     * Metodo che permette di tornare alla scena precedente rispetto a quella in cui ci troviamo,
     * ossia il layout dell'interfaccia utente relativo a "home.fxml".
     *
     * @param event evento associato all'interfaccia utente, che scaturisce la chiamata a questo metodo
     * @throws IOException eccezione relativa alle operazioni di I/O
     */
    @FXML
    void handleBack(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminHome.fxml")));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.setResizable(false);
        window.show();
    }

}
