package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe che permette la gestione del pattern Singleton, nell'ambito della connessione al database
 * @author Luca Amoroso
 */

public class ConnessioneSingleton {
    /**
     * Le variabili istanze rappresentano, rispettivamente, una variabile statica che conterrà l'unica istanza
     * della classe e una variabile che conterrà l'oggetto di connessione al database
     */
    private static ConnessioneSingleton istanza;
    private Connection conn;

    /**
     * Costruttore privato, in modo da impedire la creazione di istanze dirette della classe, al di fuori della
     * classe stessa.
     */
    private ConnessioneSingleton() {}

    /**
     * Metodo che restituisce l'unica istanza della classe, se esiste, o ne crea una nuova nel caso in cui
     * non esista. Nel caso in cui non esista, inoltre, invoca il metodo "connessione" per ottenere una connessione
     * al database.
     * @return l'unica istanza della classe
     */
    public static ConnessioneSingleton getIstanza() {
        if (istanza == null) {
            istanza = new ConnessioneSingleton();
            istanza.conn = istanza.connessione();
        }
        return istanza;
    }

    /**
     * Metodo per la connessione al database MySQL, gestito con la clausola try-catch.
     * Se la connessione ha successo, viene stampato un messaggio sulla console, altrimenti viene stampato
     * il messaggio relativo all'errore verificatosi.
     * @return l'oggetto di connessione al database
     */
    private Connection connessione() {
        try {
            /**
             * La seguente istruzione permette di caricare dinamicamente la classe dei driver JDBC MySQL nel caricatore
             * di classi Java. Questa istruzione è comune nei driver JDBC e viene spesso utilizzata
             * per registrare il driver JDBC con il DriverManager di Java.
             */

            //Class.forName("com.mysql.jdbc.Driver");
            /*non lo uso pi&ugrave; perch&egrave; ho linkato la libreria .jar
            */
            /**
             * La seguente istruzione permette di stabilire una connessione al database MySQL utilizzando il JDBC.
             * Ciò avviene specificando l'URL di connessione al database, ossia la macchina sulla quale si trova
             * in esecuzione, la porta e il nome del database, oltre a nome utente e password per la connessione.
             */
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbBike","nonnt66",
                    "password");
            System.out.println("Connessione al database riuscita!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public Connection getConnessione() {
        return conn;
    }
}