package api.ega.ega.entity;

import api.ega.ega.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Setter
@Getter
@Table(name = "accounts")
public class Account {
    @Id
    private String accountNumber;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //private String accountType;

    //Hafta do a large refractor.
    private AccountType accountType;
    private LocalDateTime creationDate;
    private BigDecimal balance;
    private String countryCode = "228";
    private String bankCode = "Ega";


    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @OneToMany(mappedBy = "sourceAccount")
    private List<Transaction> transactionsAsSource;

    @OneToMany(mappedBy = "recipientAccount")
    private List<Transaction> transactionsAsRecipient;
    @PrePersist
    protected void onCreate(){
        creationDate = LocalDateTime.now();
        balance = BigDecimal.ZERO;
    }
    private void generateAndSetFormattedId() {
        Iban.Builder ibanBuilder = new Iban.Builder()
                .countryCode(CountryCode.valueOf(countryCode))
                .bankCode(bankCode)
                .accountNumber(String.valueOf(id)); // Assuming id is the account number

        Iban iban = ibanBuilder.build();
        accountNumber = iban.toString();
    }



}
