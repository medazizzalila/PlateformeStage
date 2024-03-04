package tn.esprit.artifact.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reclamation;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    private String contenu;
    private String piece;

    @Enumerated(EnumType.STRING)
    private StateRec staterec;

    @JsonIgnore
   @ManyToMany(mappedBy = "reclamations")
    private Set<User> users;
}
