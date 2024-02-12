package org.ldp.demo;

import javax.swing.*;

public class EccezionePersonalizzata extends Exception {
    /**
     * Costruttore di classe, che a sua volta invoca il costruttore della superclasse Exception e visualizza,
     * in una finestra apposita, il messaggio passato in input.
     * @param message stringa da visualizzare nel caso in cui si verifichi questa eccezione
     */
    public EccezionePersonalizzata(String message)
    {
        super(message);
        JOptionPane.showMessageDialog(null, message);
    }
}
