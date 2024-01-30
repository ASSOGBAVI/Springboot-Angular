package api.ega.ega.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.IbanFormatException;
import org.iban4j.IbanUtil;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "accounts")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "sourceAccount") // Match with the field name in Account entity
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "recipientAccount") // Match with the field name in Account entity
    private Account recipientAccount;
    private BigDecimal amount;
    private LocalDateTime transactionDate;



    @PrePersist
    protected void onCreate(){
        transactionDate = LocalDateTime.now();

    }


}
