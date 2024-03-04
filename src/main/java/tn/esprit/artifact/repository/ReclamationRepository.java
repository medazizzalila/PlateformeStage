package tn.esprit.artifact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.artifact.entity.Reclamation;

public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {
}
