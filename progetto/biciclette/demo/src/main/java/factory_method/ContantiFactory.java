package factory_method;

/**
 * Questa classe implementa l'interfaccia Factory e funge da ConcreteFactory del pattern Factory Method;
 * si occupa della creazione di nuovi oggetti di classe Contanti.
 * @author Luca Amoroso
 * @see Factory
 */
public class ContantiFactory implements Factory {
    private MetodoPagamento cont;

    /**
     * In questo costruttore i dati relativi al cliente vengono inizializzati mediante costruttore
     * della superclasse DatiCliente.
     * @param n nome cliente
     * @param c cognome cliente
     * @param cf codice fiscale cliente
     * @param s soldi con cui il cliente vuole pagare
     */
    public ContantiFactory(String n, String c, String cf, double s) {
        cont = new Contanti(n,c,cf,s);
    }

    /**
     * Metodo "factoryMethod" del pattern omonimo
     */
    @Override
    public MetodoPagamento getMetodo() {
        return cont;
    }
}
