package tn.esprit.artifact.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.Reclamation;
import tn.esprit.artifact.repository.ReclamationRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReclamationService implements IReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;



    @Override
    public Reclamation createReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation updateReclamation(Long id, Reclamation reclamation) {
        if (reclamationRepository.existsById(id)) {
            reclamation.setId_reclamation(id);
            return reclamationRepository.save(reclamation);
        } else {
            throw new IllegalArgumentException("Reclamation with id " + id + " not found.");
        }
    }
    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public Optional<Reclamation> getReclamationById(Long id) {
        return reclamationRepository.findById(id);
    }
    @Override
    public void deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);
    }

}
