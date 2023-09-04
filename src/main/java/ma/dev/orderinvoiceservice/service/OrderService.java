package ma.dev.orderinvoiceservice.service;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.dto.OrderLineItemDto;
import ma.dev.orderinvoiceservice.dto.OrderRequest;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import ma.dev.orderinvoiceservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
    }


    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemsDto) {

        OrderLineItem orderLineItems = new OrderLineItem();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
