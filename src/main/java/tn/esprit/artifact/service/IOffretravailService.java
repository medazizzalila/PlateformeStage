package tn.esprit.artifact.service;

import tn.esprit.artifact.entity.Offretravail;

import java.util.List;
import java.util.Optional;

public interface IOffretravailService {
    Offretravail createOffretravail(Offretravail offretravail);

    Offretravail updateOffretravail(Long id, Offretravail offretravail);

    List<Offretravail> getAllOffretravaux();

    Optional<Offretravail> getOffretravailById(Long id);

    void deleteOffretravail(Long id);
}
