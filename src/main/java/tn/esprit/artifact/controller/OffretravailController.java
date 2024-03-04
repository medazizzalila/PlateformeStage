package tn.esprit.artifact.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.artifact.entity.Offretravail;
import tn.esprit.artifact.repository.OffretravailRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("offretravail")
public class OffretravailController {

    @Autowired
    private OffretravailRepository offretravailRepository;

    @PostMapping("add")
    public Offretravail createQa(@RequestBody Offretravail offretravail) {
        return offretravailRepository.save(offretravail);
    }

    @PutMapping("/update/{id}")
    public Offretravail updateOffretravail(@PathVariable Long id, @RequestBody Offretravail offretravail) {
        if (offretravailRepository.existsById(id)) {
            offretravail.setId_offretrv(id);
            return offretravailRepository.save(offretravail);
        } else {
            throw new IllegalArgumentException("Offretravail with id " + id + " not found.");
        }
    }
    @GetMapping("/")
    public List<Offretravail> getAllOffretravaux() {
        return offretravailRepository.findAll();
    }
    @DeleteMapping("delete/{id}")
    public void deleteOffretravail(@PathVariable Long id) {
        offretravailRepository.deleteById(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Offretravail> getQaByIdgetOffretravailById(@PathVariable Long id) {
        Offretravail ot = offretravailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("QA with id " + id + " not found."));
        return ResponseEntity.ok().body(ot);
    }
}
