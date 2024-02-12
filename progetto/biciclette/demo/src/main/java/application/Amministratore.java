package application;

import org.ldp.demo.EccezionePersonalizzata;

import java.sql.ResultSet;
import java.sql.SQLException;

import singleton.Database;

public class Amministratore {

    private String email;
    private String password;
    private String nome;

    public Amministratore() {
    }

    ;

    public Amministratore(String email, String password, String nome) {
        this.email = email;
        this.password = password;
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void aggiungiBiciclettaAParcheggio() throws SQLException, EccezionePersonalizzata {
    }

    ;

    public void aggiornaTariffa() throws SQLException {
    }

    ;

    public boolean verificaDisponibilitaBiciclette(String categoria) throws SQLException, EccezionePersonalizzata {
        // Logica per verificare la disponibilità delle biciclette
        Database db = new Database();
        String query = "SELECT COUNT(*) AS biciclette_presenti FROM Bicicletta B JOIN Parcheggio P ON B.id_parcheggio =" +
                " P.id_parcheggio WHERE B.categoria_bici = '" + categoria + "' AND B.disponibile = true;";
        ResultSet rs = db.query(query);
        if (rs.next()) {
            return rs.getInt("biciclette_presenti") > 0;
        }
        return false;
    }
}
