package tn.esprit.artifact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.artifact.entity.QA;

public interface QaRepository extends JpaRepository<QA,Long> {
}
