package tn.esprit.artifact.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.QA;
import tn.esprit.artifact.repository.QaRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QaService implements IQaService {

    @Autowired
    private QaRepository qaRepository;

    @Override
    public QA createQa(QA qa) {
        return qaRepository.save(qa);
    }
    @Override
    public QA updateQa(Long id, QA qa) {
        if (qaRepository.existsById(id)) {
            qa.setId_qa(id);
            return qaRepository.save(qa);
        } else {
            throw new IllegalArgumentException("Qa with id " + id + " not found.");
        }
    }
    @Override
    public List<QA> getAllQas() {
        return qaRepository.findAll();
    }

    @Override
    public Optional<QA> getQaById(Long id) {
        return qaRepository.findById(id);
    }
    @Override
    public void deleteQa(Long id) {
        qaRepository.deleteById(id);
    }
}
