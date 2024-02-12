package factory_method;

/**
 * Interfaccia che funge, come suggerisce il nome, da interfaccia Factory del pattern Factory Method;
 * ho omesso lo specificatore di accesso in quanto i metodi delle interfacce devono per forza essere implementati
 * da altre classi, quindi sono automaticamente public.
 * @author Luca Amoroso
 */
public interface Factory {
    /**
     * Metodo "factoryMethod" del pattern omonimo
     */
    MetodoPagamento getMetodo();
}
