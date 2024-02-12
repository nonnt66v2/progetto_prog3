package factory_method;

/**
 * Questa classe estende la classe astratta DatiCliente e funge da ConcreteProduct del pattern Factory method;
 * si occupa della gestione del metodo di pagamento relativo alle carte di credito.
 * @author Luca Amoroso
 * @see DatiCliente
 */
public class CartaCredito extends DatiCliente {
    private String numCarta;
    private String dataScadenza;
    private String cvv;

    /**
     * In questo costruttore i dati relativi al cliente vengono inizializzati mediante costruttore
     * della superclasse DatiCliente.
     * @param n nome cliente
     * @param c cognome cliente
     * @param cf codice fiscale cliente
     * @param nc numero carta di credito
     * @param ds data di scadenza carta di credito
     * @param cv cvv carta di credito
     */
    public CartaCredito(String n, String c, String cf, String nc, String ds, String cv) {
        super(n,c,cf);
        numCarta = nc;
        dataScadenza = ds;
        cvv = cv;
    }

    public String getNumCarta() {
        return numCarta;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public String getCvv() {
        return cvv;
    }

    /**
     * Metodo della superclasse astratta, implementato dalle sottoclassi in base a come viene effettuato il pagamento
     */
    public String paga() {
        return "Il cliente " + getNome() + " " + getCognome() + " ha effettuato con successo il pagamento con carta" +
                " di credito.";
    }
}
