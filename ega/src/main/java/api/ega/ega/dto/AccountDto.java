package api.ega.ega.dto;

import api.ega.ega.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AccountDto {
    private int accountNumber;
    private int id;
    private AccountType accountType;
    //private String accountType;
    private LocalDateTime creationDate;
    private BigDecimal balance;

    private String ownerName;
}
