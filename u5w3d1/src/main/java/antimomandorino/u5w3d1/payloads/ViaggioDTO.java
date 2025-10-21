package antimomandorino.u5w3d1.payloads;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import antimomandorino.u5w3d1.entities.ViaggioStato;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ViaggioDTO(@NotBlank(
        message = "La destinazione è obbligatorio"
) @Size(
        min = 2,
        max = 40,
        message = "La destinazione deve avere una lunghezza compresa tra 2 e 40 caratteri"
) String destinazione, @NotNull(
        message = "La data  è obbligatoria"
) @FutureOrPresent(
        message = "La data del viaggio deve essere oggi o successiva"
) LocalDate data, @NotNull(
        message = "Stato iniziale è obbligatorio"
) ViaggioStato stato) {
    public ViaggioDTO(@NotBlank(
            message = "La destinazione è obbligatorio"
    ) @Size(
            min = 2,
            max = 40,
            message = "La destinazione deve avere una lunghezza compresa tra 2 e 40 caratteri"
    ) String destinazione, @JsonFormat(pattern = "yyyy-MM-dd") @NotNull(
            message = "La data  è obbligatoria"
    ) @FutureOrPresent(
            message = "La data del viaggio deve essere oggi o successiva"
    ) LocalDate data, @NotNull(
            message = "Stato iniziale è obbligatorio"
    ) ViaggioStato stato) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = stato;
    }

    @JsonFormat(
            pattern = "yyyy-MM-dd"
    )
    public @NotNull(
            message = "La data  è obbligatoria"
    ) @FutureOrPresent(
            message = "La data del viaggio deve essere oggi o successiva"
    ) LocalDate data() {
        return this.data;
    }
}
