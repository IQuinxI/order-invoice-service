package ma.dev.orderinvoiceservice.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import ma.dev.orderinvoiceservice.repository.OrderLineItemRepository;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderLineItemService {
    private final OrderLineItemRepository orderLineItemRepository;

    public OrderLineItem addOrderItem(Order order, Long productId, int quantity) {
        OrderLineItem orderLineItem = OrderLineItem.builder()
                .orderId(order)
                .productId(productId)
                .quantity(quantity)
                .build();

        return orderLineItemRepository.save(orderLineItem);
    }

    public OrderLineItem getOrderLineItem(Long id) {
        return orderLineItemRepository.findById(id).get();
    }
}
