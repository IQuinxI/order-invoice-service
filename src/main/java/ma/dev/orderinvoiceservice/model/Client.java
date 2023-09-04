package ma.dev.orderinvoiceservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
   
    private Long clientId;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String email;
}
