package tn.esprit.artifact.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Offretravail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_offretrv;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date datedebut;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date datefin;
    private String descrip;
    private String email;
    private long num;
    private String societe;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "offretravail")
    private Set<User> users;

}
