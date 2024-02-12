package Mediator;

import org.ldp.demo.EccezionePersonalizzata;

import java.sql.SQLException;

// Interfaccia Mediator
public interface Mediator {
    void prenotaBicicletta(String tipoRichiesta, String categoria, String ora) throws EccezionePersonalizzata, SQLException;

}
