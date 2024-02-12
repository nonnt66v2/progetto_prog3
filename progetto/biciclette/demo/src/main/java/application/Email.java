package application;

/**
 * Classe che viene gestita come il pattern Singleton, che permette di avere un'unica istanza dell'email del cliente
 * che si logga, in modo da utilizzarla per quanto riguarda le operazioni relative al carrello e alle vendite,
 * nel quale è coinvolto quel cliente.
 */
public class Email {
    private static Email istanza;
    private String email;

    private Email() {}

    /**
     * Metodo getInstance del Singleton.
     * @return l'unica istanza di questa classe
     */
    public static synchronized Email getIstanza() {
        if (istanza == null) {
            istanza = new Email();
        }
        return istanza;
    }

    /**
     * Metodi set e get per l'email.
     * @param e email dell'utente acceduto
     */
    public void setEmail(String e) {
        email = e;
    }

    public String getEmail() {
        return email;
    }

}