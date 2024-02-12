package factory_method;

/**
 * Questa classe estende la classe astratta DatiCliente e funge da ConcreteProduct del pattern Factory method;
 * si occupa della gestione del metodo di pagamento relativo ai contanti
 * @author Luca Amoroso
 * @see DatiCliente
 */
public class Contanti extends DatiCliente {
    private double soldi;

    /**
     * In questo costruttore i dati relativi al cliente vengono inizializzati mediante costruttore
     * della superclasse DatiCliente
     * @param n nome cliente
     * @param c cognome cliente
     * @param cf codice fiscale cliente
     * @param s soldi con cui il cliente vuole pagare
     */
    public Contanti(String n, String c, String cf, double s) {
        super(n,c,cf);
        soldi = s;
    }

    public double getSoldi() {
        return soldi;
    }

    /**
     * Metodo della superclasse astratta, implementato dalle sottoclassi in base a come viene effettuato il pagamento
     */
    public String paga() {
        return "Il cliente " + getNome() + " " + getCognome() + " ha effettuato con successo il pagamento con " + soldi
                + " euro.";
    }
}
