package tn.esprit.artifact.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.artifact.entity.QA;
import tn.esprit.artifact.repository.QaRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("qa")
public class QaController {

    @Autowired
    private QaRepository qaRepository;

    @PostMapping("add")
    public QA createQa(@RequestBody QA qa) {
        return qaRepository.save(qa);
    }

    @PutMapping("/update/{id}")
    public QA updateQa(@PathVariable Long id, @RequestBody QA qa) {
        if (qaRepository.existsById(id)) {
            qa.setId_qa(id);
            return qaRepository.save(qa);
        } else {
            throw new IllegalArgumentException("QA with id " + id + " not found.");
        }
    }
    @GetMapping("/")
    public List<QA> getAllQas() {
        return qaRepository.findAll();
    }
    @DeleteMapping("delete/{id}")
    public void deleteQa(@PathVariable Long id) {
        qaRepository.deleteById(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<QA> getQaById(@PathVariable Long id) {
        QA qa = qaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("QA with id " + id + " not found."));
        return ResponseEntity.ok().body(qa);
    }

}
