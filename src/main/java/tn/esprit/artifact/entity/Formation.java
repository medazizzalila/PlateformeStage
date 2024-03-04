package tn.esprit.artifact.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_formation;
    private String name;
    private String descrp;
    private Date startdate;
    private Date enddate;
    private String localisation;

    @ManyToMany(mappedBy = "formations")
    private Set<User> users;

}
