package tn.esprit.artifact.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.artifact.entity.Reclamation;
import tn.esprit.artifact.entity.StateRec;
import tn.esprit.artifact.repository.ReclamationRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("reclamations")
public class ReclamationController {

    @Autowired
    private ReclamationRepository reclamationRepository;



// ...

    @PostMapping("/add")
    public Reclamation createReclamation(@RequestParam("piece") MultipartFile file,
                                         @RequestParam("contenu") String contenu,
                                         @RequestParam("date") String date,
                                         @RequestParam("staterec") StateRec staterec) {
        try {
            // Créer le dossier s'il n'existe pas
            Path path = Paths.get(System.getProperty("user.dir") + "/uploads");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // Enregistrer le fichier dans le dossier
            Path filePath = path.resolve(file.getOriginalFilename());
            file.transferTo(filePath.toFile());

            // Créer une nouvelle réclamation avec les valeurs fournies
            Reclamation reclamation = new Reclamation();
            reclamation.setPiece(file.getOriginalFilename());
            reclamation.setContenu(contenu);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = formatter.parse(date);
            reclamation.setDate(parsedDate);
            reclamation.setStaterec(staterec);

            return reclamationRepository.save(reclamation);
        } catch (Exception e) {
            // Gérer l'exception
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
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
