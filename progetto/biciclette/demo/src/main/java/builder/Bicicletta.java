package builder;
import java.util.Arrays;

// Classe Bicicletta
public class Bicicletta {
    private int id;
    private String categoria;
    private int prezzo;
    private int idParcheggio;
    private String[] equipaggiamento;

    // Costruttore privato per evitare l'istanziazione diretta
    private Bicicletta(BiciclettaBuilder builder) {
        this.id = builder.id;
        this.categoria = builder.categoria;
        this.prezzo = builder.prezzo;
        this.idParcheggio = builder.idParcheggio;
        this.equipaggiamento = builder.equipaggiamento;
    }

    // Metodi getter
    public int getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public int getIdParcheggio() {
        return idParcheggio;
    }

    public String[] getEquipaggiamento() {
        return equipaggiamento;
    }

    // Classe Builder
    public static class BiciclettaBuilder {
        private int id;
        private String categoria;
        private int prezzo;
        private int idParcheggio;
        private String[] equipaggiamento;

        // Costruttore del Builder
        public BiciclettaBuilder(int id, String categoria) {
            this.id = id;
            this.categoria = categoria;
        }

        // Metodi setter per impostare gli altri attributi
        public BiciclettaBuilder prezzo(int prezzo) {
            this.prezzo = prezzo;
            return this;
        }

        public BiciclettaBuilder idParcheggio(int idParcheggio) {
            this.idParcheggio = idParcheggio;
            return this;
        }

        public BiciclettaBuilder equipaggiamento(String[] equipaggiamento) {
            this.equipaggiamento = equipaggiamento;
            return this;
        }

        // Metodo per costruire l'oggetto Bicicletta
        public Bicicletta build() {
            return new Bicicletta(this);
        }
    }
}
