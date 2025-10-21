package antimomandorino.u5w3d1.controllers;

import antimomandorino.u5w3d1.entities.Dipendente;
import antimomandorino.u5w3d1.exceptions.ValidationException;
import antimomandorino.u5w3d1.payloads.DipendenteDTO;
import antimomandorino.u5w3d1.payloads.LoginDTO;
import antimomandorino.u5w3d1.payloads.LoginResponseDTO;
import antimomandorino.u5w3d1.services.AuthService;
import antimomandorino.u5w3d1.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body) {
        return new LoginResponseDTO(authService.checkCredentialsAndGeneratedToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente createDipendente(@RequestBody @Validated DipendenteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream().map((fieldError) -> fieldError.getDefaultMessage()).toList());
        } else {
            return this.dipendenteService.saveDipendente(payload);
        }
    }

}
