package api.ega.ega.entity;

import api.ega.ega.enums.Pays;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Setter
@Getter
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String clientLastName;
    private String clientFirstName;
    private LocalDate birthDate;
    private String sexe;
    private String address;
    private String phone;
    private Pays nationality;
    private LocalDateTime creationDate;
   // private email email;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;


    @PrePersist
    protected void onCreate(){
        creationDate = LocalDateTime.now();
    }


}