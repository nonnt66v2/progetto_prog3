package singleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Questa classe gestisce le operazioni che si possono effettuare sul database.
 *
 * @author Luca Amoroso
 * @see ConnessioneSingleton
 */
public class Database {
    private ConnessioneSingleton cs;

    /**
     * Nel costruttore viene inizializzata la connessione al database mediante il metodo della classe
     * ConnessioneSingleton che si occupa di restituire l'unica istanza di connessione al database.
     */
    public Database() {
        cs = ConnessioneSingleton.getIstanza();
    }

    /**
     * Metodo per l'esecuzione di una query passata in input.
     *
     * @param q stringa che equivale alla query da eseguire
     * @return il risultato della query eseguita
     * @throws SQLException eccezioni riguardanti database SQL
     */
    public ResultSet query(String q) throws SQLException {
        Statement state = cs.getConnessione().createStatement();
        return state.executeQuery(q);
    }

    /**
     * Metodo per ottenere un oggetto PreparedStatement pronto per l'esecuzione di una query di inserimento
     * nel database.
     *
     * @param q stringa che rappresenta una query di inserimento nel database
     * @return un oggetto PreparedStatement su cui effettuare una query di inserimento nel database
     * @throws SQLException eccezioni riguardanti database SQL
     */
    public PreparedStatement insert(String q) throws SQLException {
        return cs.getConnessione().prepareStatement(q);
    }

    /**
     * Metodo per effettuare un'eliminazione dal database a partire dalla stringa passata in input,
     * la quale rappresenta una query di eliminazione.
     *
     * @param q stringa che rappresenta una query di eliminazione dal database
     * @throws SQLException eccezioni riguardanti database SQL
     */
    public void update(String q) throws SQLException {
        Statement state = cs.getConnessione().createStatement();
        state.executeUpdate(q);
    }
}
