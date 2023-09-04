package ma.dev.orderinvoiceservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inviceItemId;

    private Long invoiceId;

    private Long productId;

    private Long quantity;

    private double unitPrice;

    @JsonIgnore
    @ManyToOne
    private Invoice invoice;

    @Transient
    private Product product;
}
