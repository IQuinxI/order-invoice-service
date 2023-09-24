package ma.dev.orderinvoiceservice.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import ma.dev.orderinvoiceservice.controller.OrderControllerImpl;
import ma.dev.orderinvoiceservice.controller.clients.ProductServiceClient;
import ma.dev.orderinvoiceservice.model.OrderLineItem;

/**
 * OrderItemAssembler
 */
@Component
@RequiredArgsConstructor
public class OrderItemAssembler implements RepresentationModelAssembler<OrderLineItem, EntityModel<OrderLineItem>>{
    private final ProductServiceClient productServiceClient;
    @Override
    public EntityModel<OrderLineItem> toModel(OrderLineItem orderLineItem) {
        orderLineItem.setProduct(productServiceClient.
            findProductById(orderLineItem.getProductId()));
            
        return EntityModel.of(orderLineItem,
            linkTo(methodOn(OrderControllerImpl.class).getOrderItemsByOrderId(orderLineItem.getOrder().getId())).withSelfRel(),
            linkTo(methodOn(OrderControllerImpl.class).getItems()).withRel("Items")
        );
    }

    
}