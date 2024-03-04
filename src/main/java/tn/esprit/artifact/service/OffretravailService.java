package tn.esprit.artifact.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.Offretravail;
import tn.esprit.artifact.repository.OffretravailRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OffretravailService implements IOffretravailService {

    @Autowired
    private OffretravailRepository offretravailRepository;

    @Override
    public Offretravail createOffretravail(Offretravail offretravail) {
        return offretravailRepository.save(offretravail);
    }
    @Override
    public Offretravail updateOffretravail(Long id, Offretravail offretravail) {
        if (offretravailRepository.existsById(id)) {
            offretravail.setId_offretrv(id);
            return offretravailRepository.save(offretravail);
        } else {
            throw new IllegalArgumentException("Offretravail with id " + id + " not found.");
        }
    }
    @Override
    public List<Offretravail> getAllOffretravaux() {
        return offretravailRepository.findAll();
    }

    @Override
    public Optional<Offretravail> getOffretravailById(Long id) {
        return offretravailRepository.findById(id);
    }
    @Override
    public void deleteOffretravail(Long id) {
        offretravailRepository.deleteById(id);
    }
}
