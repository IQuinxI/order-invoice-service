package ma.dev.orderinvoiceservice.model;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;



    private Date invoiceDate;

    private int totalAmount;
    private Long clientId;
    @OneToMany(mappedBy = "invoice")
    private Collection<InvoiceItems> invoiceItems;

    @Transient
    private Client client;
}
