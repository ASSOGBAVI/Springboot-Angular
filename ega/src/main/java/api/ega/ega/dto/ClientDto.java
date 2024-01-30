package api.ega.ega.dto;

import api.ega.ega.enums.Pays;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClientDto {
    private int id;
    private String clientLastName;
    private String clientFirstName;
    private LocalDate birthDate;
    private String sexe;
    private String address;
    private String phone;
    private Pays nationality;
    private LocalDateTime creationDate;

    private List<AccountDto> accountDtos;
}
