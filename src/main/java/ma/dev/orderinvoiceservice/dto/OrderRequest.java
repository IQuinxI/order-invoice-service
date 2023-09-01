package ma.dev.orderinvoiceservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderLineItemDto> orderLineItemsDtoList;



}
