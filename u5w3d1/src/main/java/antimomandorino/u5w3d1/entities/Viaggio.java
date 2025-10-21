package antimomandorino.u5w3d1.entities;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import jakarta.persistence.*;
import lombok.Generated;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "viaggi"
)
public class Viaggio {
    @Id
    @GeneratedValue
    @Column(
            name = "viaggio_id"
    )
    private UUID viaggioId;
    private String destinazione;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private ViaggioStato stato;

    public Viaggio(String destinazione, LocalDate data, ViaggioStato stato) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = stato;
    }

    @Generated
    public Viaggio() {
    }

    @Generated
    public UUID getViaggioId() {
        return this.viaggioId;
    }

    @Generated
    public String getDestinazione() {
        return this.destinazione;
    }

    @Generated
    public void setDestinazione(final String destinazione) {
        this.destinazione = destinazione;
    }

    @Generated
    public LocalDate getData() {
        return this.data;
    }

    @Generated
    public void setData(final LocalDate data) {
        this.data = data;
    }

    @Generated
    public ViaggioStato getStato() {
        return this.stato;
    }

    @Generated
    public void setStato(final ViaggioStato stato) {
        this.stato = stato;
    }

    @Generated
    public String toString() {
        String var10000 = String.valueOf(this.getViaggioId());
        return "Viaggio(viaggioId=" + var10000 + ", destinazione=" + this.getDestinazione() + ", data=" + String.valueOf(this.getData()) + ", stato=" + String.valueOf(this.getStato()) + ")";
    }
}
