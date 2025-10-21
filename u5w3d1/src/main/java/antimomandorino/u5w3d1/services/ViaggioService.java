package antimomandorino.u5w3d1.services;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import antimomandorino.u5w3d1.entities.Viaggio;
import antimomandorino.u5w3d1.entities.ViaggioStato;
import antimomandorino.u5w3d1.exceptions.NotFoundException;
import antimomandorino.u5w3d1.payloads.ViaggioDTO;
import antimomandorino.u5w3d1.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;

    public Viaggio saveViaggio(ViaggioDTO payload) {
        Viaggio nuovoViaggio = new Viaggio(payload.destinazione(), payload.data(), payload.stato());
        return (Viaggio) this.viaggioRepository.save(nuovoViaggio);
    }

    public Page<Viaggio> findAll(Integer pageNumber, Integer pageSize, String sortBy) {
        if (pageSize > 20) {
            pageSize = 20;
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(new String[]{sortBy}).ascending());
        return this.viaggioRepository.findAll(pageable);
    }

    public Viaggio findById(UUID viaggioId) {
        return (Viaggio) this.viaggioRepository.findById(viaggioId).orElseThrow(() -> new NotFoundException(viaggioId));
    }

    public Viaggio findByIdAndUpdate(UUID viaggioId, ViaggioDTO payload) {
        Viaggio found = this.findById(viaggioId);
        found.setDestinazione(payload.destinazione());
        found.setData(payload.data());
        found.setStato(payload.stato());
        return (Viaggio) this.viaggioRepository.save(found);
    }

    public void findByIdAndDelete(UUID viaggioId) {
        Viaggio found = this.findById(viaggioId);
        this.viaggioRepository.delete(found);
    }

    public Viaggio updateStato(UUID viaggioId, ViaggioStato nuovoStato) {
        Viaggio found = this.findById(viaggioId);
        found.setStato(nuovoStato);
        return (Viaggio) this.viaggioRepository.save(found);
    }
}
