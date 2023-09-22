package ma.dev.orderinvoiceservice.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.assemblers.OrderItemAssembler;
import ma.dev.orderinvoiceservice.controller.OrderControllerImpl;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import ma.dev.orderinvoiceservice.repository.OrderLineItemRepository;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderLineItemServiceImpl {
    private final OrderLineItemRepository orderLineItemRepository;
    private final OrderItemAssembler assembler;
    public OrderLineItem addOrderItem(Order order, Long productId, int quantity) {
        OrderLineItem orderLineItem = OrderLineItem.builder()
                .order(order)
                .productId(productId)
                .quantity(quantity)
                .build();

        return orderLineItemRepository.save(orderLineItem);
    }

    public OrderLineItem getOrderLineItem(Long id) {
        return orderLineItemRepository.findById(id).get();
    }

    public List<OrderLineItem> getOrderItemByOrder(Order order) {
        return orderLineItemRepository.findByOrder(order);
    }

    public CollectionModel<EntityModel<OrderLineItem>> getAllItems() {
        List<EntityModel<OrderLineItem>> orders = orderLineItemRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders,
            linkTo(methodOn(OrderControllerImpl.class).getItems()).withSelfRel());
    }
    // public List<OrderLineItem> getOrderLineItemByOrderId(Long id) {
    //     return orderLineItemRepository.findByOrderId(id);
    // }
}
