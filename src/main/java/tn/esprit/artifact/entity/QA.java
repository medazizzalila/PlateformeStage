package tn.esprit.artifact.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QA implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_qa;
    private String question;
    private String reponse;
    @ManyToMany(mappedBy = "qas")
    private Set<User> users;

}
