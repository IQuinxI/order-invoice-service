package ma.dev.orderinvoiceservice.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Long IdProduct;
    @NonNull
    private String name;
    private String designation;
    private double price;
    private String Category;
    private String pictureURL;
    private int quantity;
}