package tn.esprit.artifact.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.artifact.entity.Reclamation;
import tn.esprit.artifact.repository.ReclamationRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("reclamations")
public class ReclamationController {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @PostMapping("add")
    public Reclamation createReclamation(@RequestBody Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @PutMapping("/update/{id}")
    public Reclamation updateReclamation(@PathVariable Long id, @RequestBody Reclamation reclamation) {
        if (reclamationRepository.existsById(id)) {
            reclamation.setId_reclamation(id);
            return reclamationRepository.save(reclamation);
        } else {
            throw new IllegalArgumentException("Reclamation with id " + id + " not found.");
        }
    }
    @GetMapping("/")
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }
    @DeleteMapping("delete/{id}")
    public void deleteReclamation(@PathVariable Long id) {
        reclamationRepository.deleteById(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable Long id) {
        Reclamation reclamation = reclamationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("QA with id " + id + " not found."));
        return ResponseEntity.ok().body(reclamation);
    }
}
