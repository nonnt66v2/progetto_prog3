package application;

import Mediator.Mediator;
import org.ldp.demo.EccezionePersonalizzata;

import java.sql.SQLException;

public class Cliente {
    private String email;
    private String nome;
    private String cognome;
    private String password;

    protected Mediator mediator;

    public Cliente(Mediator mediator) {
        this.mediator = mediator;
    }
    public Cliente(String email, String password) {
        this.email = email;
        this.password = password;

    }
    public Cliente(String email, String nome, String cognome, String password) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
    }

    // Metodi getter e setter per gli attributi della classe Cliente
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void inviaRichiesta(String tipoRichiesta, String dettagli, String ora) throws EccezionePersonalizzata, SQLException {};

}

