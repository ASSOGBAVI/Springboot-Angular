package api.ega.ega.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TransactionDto {
    private int id;
    private AccountDto sourceAccountId;
    private AccountDto recipientAccountId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;


    public void setSourceAccountId(String accountNumber) {
    }

    public void setRecipientAccountId(String accountNumber) {
    }
}
