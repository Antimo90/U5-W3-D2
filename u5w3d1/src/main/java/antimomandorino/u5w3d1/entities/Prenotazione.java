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
        name = "prenotazioni"
)
public class Prenotazione {
    @Id
    @GeneratedValue
    @Column(
            name = "prenotazione_id"
    )
    private UUID prenotazioneId;
    @Column(
            name = "data_richiesta"
    )
    private LocalDate dataRichiesta;
    @Column(
            name = "note_preferenze"
    )
    private String notePreferenze;
    @ManyToOne
    @JoinColumn(
            name = "viaggio_id",
            nullable = false
    )
    private Viaggio viaggio;
    @ManyToOne
    @JoinColumn(
            name = "dipendente_id",
            nullable = false
    )
    private Dipendente dipendente;

    public Prenotazione(LocalDate dataRichiesta, String notePreferenze, Viaggio viaggio, Dipendente dipendente) {
        this.dataRichiesta = dataRichiesta;
        this.notePreferenze = notePreferenze;
        this.viaggio = viaggio;
        this.dipendente = dipendente;
    }

    @Generated
    public Prenotazione() {
    }

    @Generated
    public UUID getPrenotazioneId() {
        return this.prenotazioneId;
    }

    @Generated
    public LocalDate getDataRichiesta() {
        return this.dataRichiesta;
    }

    @Generated
    public void setDataRichiesta(final LocalDate dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    @Generated
    public String getNotePreferenze() {
        return this.notePreferenze;
    }

    @Generated
    public void setNotePreferenze(final String notePreferenze) {
        this.notePreferenze = notePreferenze;
    }

    @Generated
    public Viaggio getViaggio() {
        return this.viaggio;
    }

    @Generated
    public void setViaggio(final Viaggio viaggio) {
        this.viaggio = viaggio;
    }

    @Generated
    public Dipendente getDipendente() {
        return this.dipendente;
    }

    @Generated
    public void setDipendente(final Dipendente dipendente) {
        this.dipendente = dipendente;
    }

    @Generated
    public String toString() {
        String var10000 = String.valueOf(this.getPrenotazioneId());
        return "Prenotazione(prenotazioneId=" + var10000 + ", dataRichiesta=" + String.valueOf(this.getDataRichiesta()) + ", notePreferenze=" + this.getNotePreferenze() + ", viaggio=" + String.valueOf(this.getViaggio()) + ", dipendente=" + String.valueOf(this.getDipendente()) + ")";
    }
}
