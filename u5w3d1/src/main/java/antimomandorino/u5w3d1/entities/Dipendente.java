package antimomandorino.u5w3d1.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "dipendenti"
)
@JsonIgnoreProperties({"password", "authorities", "enabled", "accountNonLocked", "accountNonExpired", "credentialsNonExpired"})
public class Dipendente implements UserDetails {
    @Id
    @GeneratedValue
    @Column(
            name = "dipendente_id"
    )
    private UUID dipendenteId;
    @Column(
            unique = true
    )
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    @Column(
            name = "immagine_profilo"
    )
    private String immagineProfilo;
    @Enumerated(EnumType.STRING)
    private DipendenteRole role;

    public Dipendente(String username, String nome, String cognome, String email, String password) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        String nomecompleto = this.nome + "+" + this.cognome;
        this.immagineProfilo = "https://ui-avatars.com/api/?name=" + nomecompleto;
        this.role = DipendenteRole.USER;
    }

    @Generated
    public Dipendente() {
    }

    @Generated
    public UUID getDipendenteId() {
        return this.dipendenteId;
    }

    @Generated
    public String getUsername() {
        return this.username;
    }

    @Generated
    public void setUsername(final String username) {
        this.username = username;
    }

    @Generated
    public String getNome() {
        return this.nome;
    }

    @Generated
    public void setNome(final String nome) {
        this.nome = nome;
    }

    @Generated
    public String getCognome() {
        return this.cognome;
    }

    @Generated
    public void setCognome(final String cognome) {
        this.cognome = cognome;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public void setEmail(final String email) {
        this.email = email;
    }

    @Generated
    public String getImmagineProfilo() {
        return this.immagineProfilo;
    }

    @Generated
    public void setImmagineProfilo(final String immagineProfilo) {
        this.immagineProfilo = immagineProfilo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((this.role.name())));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Generated
    public String toString() {
        String var10000 = String.valueOf(this.getDipendenteId());
        return "Dipendente(dipendenteId=" + var10000 + ", username=" + this.getUsername() + ", nome=" + this.getNome() + ", cognome=" + this.getCognome() + ", email=" + this.getEmail() + ", immagineProfilo=" + this.getImmagineProfilo() + ")";
    }
}
