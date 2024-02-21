package tn.esprit.artifact.entity;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.core.SpringVersion;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_quiz;
    private String question ;
    private String reponse;
    private int note ;

    @OneToOne(mappedBy = "quiz")
    private Conditature conditature;
}
