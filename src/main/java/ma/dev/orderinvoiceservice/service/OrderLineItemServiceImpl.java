package ma.dev.orderinvoiceservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import feign.FeignException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.assemblers.OrderItemAssembler;
import ma.dev.orderinvoiceservice.controller.OrderControllerImpl;
import ma.dev.orderinvoiceservice.controller.clients.ProductServiceClient;
import ma.dev.orderinvoiceservice.exceptions.OrderItemNotFoundException;
import ma.dev.orderinvoiceservice.exceptions.RequestNotValidException;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import ma.dev.orderinvoiceservice.repository.OrderLineItemRepository;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderLineItemServiceImpl {
    private final OrderLineItemRepository orderLineItemRepository;
    private final OrderItemAssembler assembler;
    private final ProductServiceClient productServiceClient;
    private final OrderItemAssembler orderItemAssembler;

    public OrderLineItem addOrderItem(Order order, Long productId, Integer quantity) {
        try {
            Integer.valueOf(quantity);
        } catch (Exception e) {
            throw new RequestNotValidException();
        }
        
        if ((quantity == 0) || productId == null || order == null)
            throw new RequestNotValidException();

        try {
            productServiceClient.findProductById(productId);
        } catch (FeignException e) {
            throw e;
        }
        OrderLineItem orderLineItem = OrderLineItem.builder()
                .order(order)
                .productId(productId)
                .quantity(quantity)
                .build();

        return orderLineItemRepository.save(orderLineItem);
    }

    public OrderLineItem getOrderLineItem(Long id) {
        OrderLineItem orderLineItem = orderLineItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException(id));

        // Binds a product to the response from the product service using FeignClient
        // orderLineItem.setProduct(productServiceClient.findProductById(orderLineItem.getProductId()));

        return orderLineItem;
    }

    public CollectionModel<EntityModel<OrderLineItem>> getOrderItemByOrder(Order order) {
        List<EntityModel<OrderLineItem>> orderItem = orderLineItemRepository.findByOrder(order)
                .stream()
                .map(orderItemAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orderItem,
                linkTo(methodOn(OrderControllerImpl.class).getOrderItemsByOrderId(order.getId())).withSelfRel());
    }

    public CollectionModel<EntityModel<OrderLineItem>> getAllItems() {
        List<EntityModel<OrderLineItem>> orders = orderLineItemRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders,
                linkTo(methodOn(OrderControllerImpl.class).getItems()).withSelfRel());
    }
}
