package Mediator;

import application.Cliente;
import org.ldp.demo.EccezionePersonalizzata;

import java.sql.SQLException;

// Concrete Colleague
public class ClienteReal extends Cliente {
    public ClienteReal(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void inviaRichiesta(String tipoRichiesta, String dettagli,String ora) throws EccezionePersonalizzata, SQLException {
        //JOptionPane.showMessageDialog(null, "Cliente invia richiesta tramite SMS");
        mediator.prenotaBicicletta(tipoRichiesta, dettagli,ora);
    }
}
