package factory_method;

/**
 * Classe astratta che implementa l'interfaccia MetodoPagamento, quindi funge da ConcreteProduct del pattern
 * Factory Method;
 * questa classe ha tre variabili istanza relative al nome, al cognome e al codice fiscale del cliente che procede
 * ad un acquisto, in quanto i metodi di pagamento hanno comunque in comune l'immissione di questi tre dati.
 * @author Luca Amoroso
 * @see MetodoPagamento
 */
public abstract class DatiCliente implements MetodoPagamento {
    private String nome;
    private String cognome;
    private String codFiscale;

    /**
     * @param n nome cliente
     * @param c cognome cliente
     * @param cf codice fiscale cliente
     */
    public DatiCliente(String n, String c, String cf) {
        nome = n;
        cognome = c;
        codFiscale = cf;
    }

    public void setNome(String n) {
        nome = n;
    }

    public String getNome() {
        return nome;
    }

    public void setCognome(String c) {
        cognome = c;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCodFiscale(String cf) {
        codFiscale = cf;
    }

    public String getCodFiscale() {
        return codFiscale;
    }

    /**
     * Il metodo paga implementato dall'interfaccia MetodoPagamento non viene effettivamente implementato,
     * poichè reso astratto, quindi l'implementazione vera e propria la faranno le sottoclassi che estendono
     * questa classe astratta.
     */
    public abstract String paga();
}
