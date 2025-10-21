package antimomandorino.u5w3d1.controllers;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import antimomandorino.u5w3d1.entities.Viaggio;
import antimomandorino.u5w3d1.entities.ViaggioStato;
import antimomandorino.u5w3d1.exceptions.ValidationException;
import antimomandorino.u5w3d1.payloads.ViaggioDTO;
import antimomandorino.u5w3d1.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping({"/viaggi"})
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public Page<Viaggio> findAllViaggi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "viaggioId") String sortBy) {
        return this.viaggioService.findAll(page, size, sortBy);
    }

    @GetMapping({"/{viaggioId}"})
    public Viaggio findViaggioById(@PathVariable UUID viaggioId) {
        return this.viaggioService.findById(viaggioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio createViaggio(@RequestBody @Validated ViaggioDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream().map((fieldError) -> fieldError.getDefaultMessage()).toList());
        } else {
            return this.viaggioService.saveViaggio(payload);
        }
    }

    @PutMapping({"/{viaggioId}"})
    public Viaggio findByIdAndUpdate(@PathVariable UUID viaggioId, @RequestBody @Validated ViaggioDTO payload) {
        return this.viaggioService.findByIdAndUpdate(viaggioId, payload);
    }

    @DeleteMapping({"/{viaggioId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID viaggioId) {
        this.viaggioService.findByIdAndDelete(viaggioId);
    }

    @PatchMapping({"/{viaggioId}/stato"})
    public Viaggio updateStatoViaggio(@PathVariable UUID viaggioId, @RequestParam ViaggioStato nuovoStato) {
        return this.viaggioService.updateStato(viaggioId, nuovoStato);
    }
}
