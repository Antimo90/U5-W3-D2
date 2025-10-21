package antimomandorino.u5w3d1.payloads;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PrenotazioneDTO(@NotNull(
        message = "ID del Viaggio è obbligatorio"
) UUID viaggioId, @NotNull(
        message = "ID del dipendente è obbligatorio"
) UUID dipendenteId, String notePreferenze) {
}
