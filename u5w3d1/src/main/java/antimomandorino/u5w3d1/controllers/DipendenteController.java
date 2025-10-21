package antimomandorino.u5w3d1.controllers;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import antimomandorino.u5w3d1.entities.Dipendente;
import antimomandorino.u5w3d1.exceptions.ValidationException;
import antimomandorino.u5w3d1.payloads.DipendenteDTO;
import antimomandorino.u5w3d1.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping({"/dipendenti"})
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    @PreAuthorize("hasAuthority('AMDIN')")
    public Page<Dipendente> findAllDipendenti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "dipendenteId") String sortBy) {
        return this.dipendenteService.findAll(page, size, sortBy);
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente findDipendenteById(@PathVariable UUID dipendenteId) {
        return this.dipendenteService.findById(dipendenteId);
    }


    @PutMapping({"/{dipendenteId}"})
    @PreAuthorize("hasAuthority('AMDIN')")
    public Dipendente findByIdAndUpdate(@PathVariable UUID dipendenteId, @RequestBody @Validated DipendenteDTO dipendentePayload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream().map((fieldError) -> fieldError.getDefaultMessage()).toList());
        } else {
            return this.dipendenteService.findByIdAndUpdate(dipendenteId, dipendentePayload);
        }
    }

    @DeleteMapping({"/{dipendenteId}"})
    @PreAuthorize("hasAuthority('AMDIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID dipendenteId) {
        this.dipendenteService.findByIdAndDelete(dipendenteId);
    }

    @PostMapping({"/{dipendenteId}/upload"})
    @PreAuthorize("hasAuthority('AMDIN')")
    public Dipendente uploadImmagine(@PathVariable UUID dipendenteId, @RequestParam("immagine") MultipartFile file) throws IOException {
        return this.dipendenteService.uploadImmagine(dipendenteId, file);
    }

    @GetMapping("/me")
    public Dipendente getProfile(@AuthenticationPrincipal Dipendente currentAuthenticateDipendente) {
        return currentAuthenticateDipendente;
    }

    @PutMapping("/me")
    public Dipendente updateProfile(@AuthenticationPrincipal Dipendente currentAuthenticateDipendente, @RequestBody DipendenteDTO payload) {
        return this.dipendenteService.findByIdAndUpdate(currentAuthenticateDipendente.getDipendenteId(), payload);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Dipendente currentAuthenticateDipendente) {
        this.dipendenteService.findByIdAndDelete(currentAuthenticateDipendente.getDipendenteId());
    }

}
