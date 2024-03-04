package tn.esprit.artifact.service;

import tn.esprit.artifact.entity.QA;

import java.util.List;
import java.util.Optional;

public interface IQaService {
    QA createQa(QA qa);

    QA updateQa(Long id, QA qa);

    List<QA> getAllQas();

    Optional<QA> getQaById(Long id);

    void deleteQa(Long id);
}
