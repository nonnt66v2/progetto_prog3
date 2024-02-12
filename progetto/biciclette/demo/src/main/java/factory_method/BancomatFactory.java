package factory_method;

/**
 * Questa classe implementa l'interfaccia Factory e funge da ConcreteFactory del pattern Factory Method;
 * si occupa della creazione di nuovi oggetti di classe Bancomat
 * @author Luca Amoroso
 * @see Factory
 */
public class BancomatFactory implements Factory {
    private MetodoPagamento bancomat;

    /**
     * In questo costruttore i dati relativi al cliente vengono inizializzati mediante costruttore
     * della superclasse DatiCliente
     * @param n nome cliente
     * @param c cognome cliente
     * @param cf codice fiscale cliente
     * @param nc numero bancomat
     * @param ds data di scadenza bancomat
     * @param cv cvv bancomat
     */
    public BancomatFactory(String n, String c, String cf, String nc, String ds, String cv) {
        bancomat = new Bancomat(n,c,cf,nc,ds,cv);
    }

    /**
     * Metodo "factoryMethod" del pattern omonimo
     */
    @Override
    public MetodoPagamento getMetodo() {
        return bancomat;
    }
}
