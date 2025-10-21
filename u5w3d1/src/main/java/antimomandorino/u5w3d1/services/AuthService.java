package antimomandorino.u5w3d1.services;

import antimomandorino.u5w3d1.entities.Dipendente;
import antimomandorino.u5w3d1.exceptions.UnauthorizedException;
import antimomandorino.u5w3d1.payloads.LoginDTO;
import antimomandorino.u5w3d1.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWTTools jwtTools;


    public String checkCredentialsAndGeneratedToken(LoginDTO body) {

        Dipendente found = this.dipendenteService.findByEmail(body.email());

        if (found.getPassword().equals(body.password())) {
            //TODO : migliorare gestione password

            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
