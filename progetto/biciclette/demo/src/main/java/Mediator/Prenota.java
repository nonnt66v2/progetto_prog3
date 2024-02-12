package Mediator;

import application.Cliente;
import application.Amministratore;
import application.Email;
import org.ldp.demo.EccezionePersonalizzata;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// Concrete Mediator
public class Prenota implements Mediator {
    private List<Cliente> clienti = new ArrayList<>();
    private Amministratore amministratore = new Amministratore();
    private boolean esitoPrenotazione;

    private void setEsitoPrenotazione(boolean esitoPrenotazione) {
        this.esitoPrenotazione = esitoPrenotazione;
    }
     public boolean getEsitoPrenotazione() {
        return esitoPrenotazione;
    }
    public void aggiungiCliente(Cliente cliente) {
        clienti.add(cliente);
    }

    @Override
    public void prenotaBicicletta(String tipoRichiesta, String categoria, String ora) throws EccezionePersonalizzata, SQLException {
        if (tipoRichiesta.equalsIgnoreCase("SMS")) {
            inviaSMS(categoria,ora);
        } else if (tipoRichiesta.equalsIgnoreCase("Email")) {
            inviaEmail(categoria,ora);
        }
    }

    private void inviaSMS(String categoria,String ora) throws EccezionePersonalizzata, SQLException {
        if(amministratore.verificaDisponibilitaBiciclette(categoria)) {
            JOptionPane.showMessageDialog(null,"Bicicletta prenotata tramite SMS alle ore "+ora);
            setEsitoPrenotazione(true);
        }else {
            JOptionPane.showMessageDialog(null,"Bicicletta non disponibile");
            setEsitoPrenotazione(false);
        }
        // Logica per gestire la prenotazione tramite SMS

    }

    private void inviaEmail(String messaggio,String ora) throws EccezionePersonalizzata, SQLException {
        if (amministratore.verificaDisponibilitaBiciclette(messaggio)) {
            JOptionPane.showMessageDialog(null,"Bicicletta prenotata tramite Email"+ Email.getIstanza().getEmail()+
                    " alle ore "+ora);
            setEsitoPrenotazione(true);
        } else {
            JOptionPane.showMessageDialog(null,"Bicicletta non disponibile");
            setEsitoPrenotazione(false);
        }
    }
}
