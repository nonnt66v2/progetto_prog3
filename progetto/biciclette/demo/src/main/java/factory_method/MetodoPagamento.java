package factory_method;

/**
 * Interfaccia che funge da interfaccia Product del pattern Factory Method e serve per gestire il modo in cui
 * viene effettuato un pagamento, in base alle diverse modalità;
 * ho omesso lo specificatore di accesso in quanto i metodi delle interfacce devono per forza essere implementati
 * da altre classi, quindi sono automaticamente public.
 * @author Luca Amoroso
 */
public interface MetodoPagamento {
    String paga();
}
