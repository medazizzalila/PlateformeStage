package tn.esprit.artifact.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.artifact.entity.Offretravail;
import tn.esprit.artifact.entity.QA;

import java.util.List;
import java.util.Optional;

public interface OffretravailRepository extends JpaRepository<Offretravail,Long> {





}
