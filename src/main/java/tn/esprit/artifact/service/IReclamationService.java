package tn.esprit.artifact.service;

import tn.esprit.artifact.entity.Reclamation;

import java.util.List;
import java.util.Optional;

public interface IReclamationService {

     List<Reclamation> getAllReclamations();
     Optional<Reclamation> getReclamationById(Long id);
    Reclamation createReclamation(Reclamation reclamation);
     Reclamation updateReclamation(Long id, Reclamation reclamation);
     void deleteReclamation(Long id);
}
