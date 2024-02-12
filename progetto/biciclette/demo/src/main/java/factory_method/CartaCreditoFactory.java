package factory_method;

/**
 * Questa classe implementa l'interfaccia Factory e funge da ConcreteFactory del pattern Factory Method;
 * si occupa della creazione di nuovi oggetti di classe CartaCredito.
 * @author Luca Amoroso
 * @see Factory
 */
public class CartaCreditoFactory implements Factory {
    private MetodoPagamento carta;

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
    public CartaCreditoFactory(String n, String c, String cf, String nc, String ds, String cv) {
        carta = new CartaCredito(n,c,cf,nc,ds,cv);
    }

    /**
     * Metodo "factoryMethod" del pattern omonimo
     */
    @Override
    public MetodoPagamento getMetodo() {
        return carta;
    }
}
