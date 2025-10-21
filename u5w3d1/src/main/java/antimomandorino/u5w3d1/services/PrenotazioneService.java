package antimomandorino.u5w3d1.services;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import antimomandorino.u5w3d1.entities.Dipendente;
import antimomandorino.u5w3d1.entities.Prenotazione;
import antimomandorino.u5w3d1.entities.Viaggio;
import antimomandorino.u5w3d1.exceptions.BadRequestException;
import antimomandorino.u5w3d1.exceptions.NotFoundException;
import antimomandorino.u5w3d1.payloads.PrenotazioneDTO;
import antimomandorino.u5w3d1.repositories.DipendenteRepository;
import antimomandorino.u5w3d1.repositories.PrenotazioneRepository;
import antimomandorino.u5w3d1.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private ViaggioRepository viaggioRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Prenotazione savePrenotazione(PrenotazioneDTO payload) {
        Dipendente dipendente = (Dipendente) this.dipendenteRepository.findById(payload.dipendenteId()).orElseThrow(() -> new NotFoundException(payload.dipendenteId()));
        Viaggio viaggio = (Viaggio) this.viaggioRepository.findById(payload.viaggioId()).orElseThrow(() -> new NotFoundException(payload.viaggioId()));
        LocalDate dataViaggio = viaggio.getData();
        if (this.prenotazioneRepository.existsByDipendente_DipendenteIdAndViaggio_Data(dipendente.getDipendenteId(), dataViaggio)) {
            String var10002 = String.valueOf(dipendente.getDipendenteId());
            throw new BadRequestException("Il dipendente con ID " + var10002 + " ha già una prenotazione per un viaggio in data " + String.valueOf(dataViaggio) + ". Non può avere più prenotazioni per lo stesso giorno.");
        } else {
            Prenotazione nuovaPrenotazione = new Prenotazione(LocalDate.now(), payload.notePreferenze(), viaggio, dipendente);
            return (Prenotazione) this.prenotazioneRepository.save(nuovaPrenotazione);
        }
    }

    public Page<Prenotazione> findAll(Integer pageNumber, Integer pageSize, String sortBy) {
        if (pageSize > 20) {
            pageSize = 20;
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(new String[]{sortBy}).ascending());
        return this.prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione findById(UUID id) {
        return (Prenotazione) this.prenotazioneRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Prenotazione findByIdAndUpdate(UUID prenotazioneId, PrenotazioneDTO payload) {
        Prenotazione found = this.findById(prenotazioneId);
        Dipendente nuovoDipendente = (Dipendente) this.dipendenteRepository.findById(payload.dipendenteId()).orElseThrow(() -> new NotFoundException(payload.dipendenteId()));
        Viaggio nuovoViaggio = (Viaggio) this.viaggioRepository.findById(payload.viaggioId()).orElseThrow(() -> new NotFoundException(payload.viaggioId()));
        LocalDate nuovaDataViaggio = nuovoViaggio.getData();
        if (!this.prenotazioneRepository.existsByDipendente_DipendenteIdAndViaggio_Data(nuovoDipendente.getDipendenteId(), nuovaDataViaggio) || found.getViaggio().getViaggioId().equals(nuovoViaggio.getViaggioId()) && found.getDipendente().getDipendenteId().equals(nuovoDipendente.getDipendenteId())) {
            found.setViaggio(nuovoViaggio);
            found.setDipendente(nuovoDipendente);
            found.setNotePreferenze(payload.notePreferenze());
            return (Prenotazione) this.prenotazioneRepository.save(found);
        } else {
            String var10002 = String.valueOf(nuovoDipendente.getDipendenteId());
            throw new BadRequestException("Il dipendente con ID " + var10002 + " ha già un'altra prenotazione per un viaggio in data " + String.valueOf(nuovaDataViaggio) + ".");
        }
    }

    public void findByIdAndDelete(UUID id) {
        Prenotazione found = this.findById(id);
        this.prenotazioneRepository.delete(found);
    }
}
