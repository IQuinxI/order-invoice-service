package ma.dev.orderinvoiceservice.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import ma.dev.orderinvoiceservice.controller.OrderControllerImpl;
import ma.dev.orderinvoiceservice.model.Order;

/**
 * OrderAssembler
 */
@Component
public class OrderAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    @Override
    public EntityModel<Order> toModel(Order order) {
        return EntityModel.of(order, 
            linkTo(methodOn(OrderControllerImpl.class).getOrder(order.getId())).withSelfRel(),
            linkTo(methodOn(OrderControllerImpl.class).getOrders()).withRel("Orders")
            );
    }

    
}