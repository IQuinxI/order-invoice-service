package ma.dev.orderinvoiceservice.service;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.assemblers.OrderAssembler;
import ma.dev.orderinvoiceservice.assemblers.OrderItemAssembler;
import ma.dev.orderinvoiceservice.controller.OrderControllerImpl;
import ma.dev.orderinvoiceservice.controller.clients.CustomerServiceClient;
import ma.dev.orderinvoiceservice.exceptions.OrderNotFoundException;
import ma.dev.orderinvoiceservice.exceptions.RequestNotValidException;
import ma.dev.orderinvoiceservice.model.Invoice;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import ma.dev.orderinvoiceservice.repository.OrderRepository;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import feign.FeignException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemServiceImpl orderLineItemService;
    private final OrderAssembler orderAssembler;
    private final OrderItemAssembler orderItemAssembler;
    private final CustomerServiceClient customerServiceClient;

    @Override
    public EntityModel<Order> getOrder(Long id) {

        // retrieves an Order
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        order.setClient(customerServiceClient.findClientById(order.getClientId()));

        return orderAssembler.toModel(order);
    }

    @Override
    public ResponseEntity<?> addOrder(List<OrderLineItem> orderLineItems, Invoice invoice, Long clientId) {
        if (clientId == null)
            throw new RequestNotValidException();
            
        try {
            customerServiceClient.findClientById(clientId);
        }catch(FeignException e) {
            if(e.status() == 404) 
                throw new ResponseStatusException(HttpStatus.valueOf(404), "Could not find the Client");

        }
        

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderLineItems)
                .invoiceId(invoice)
                .clientId(clientId)
                .build();

        EntityModel<Order> entityModel = orderAssembler.toModel(orderRepository.save(order));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @Override
    public ResponseEntity<?> deleteOrder(Long id) {
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> replaceOrder(Long id, List<OrderLineItem> orderLineItems, Invoice invoice, Long clientId) {
        if (clientId == null)
            throw new RequestNotValidException();

        Order updatedOrder = orderRepository.findById(id)
                .map(ord -> {
                    ord.setClientId(clientId);
                    ord.setInvoiceId(invoice);
                    ord.setOrderLineItemsList(orderLineItems);
                    return orderRepository.save(ord);
                })
                .orElseThrow(() -> new OrderNotFoundException(id));

        EntityModel<Order> entityModel = orderAssembler.toModel(updatedOrder);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @Override
    public CollectionModel<EntityModel<Order>> getOrders() {
        List<EntityModel<Order>> orders = orderRepository.findAll()
                .stream()
                .map(ord -> {
                    ord.setClient(customerServiceClient.findClientById(ord.getClientId()));
                    return orderAssembler.toModel(ord);
                })
                .collect(Collectors.toList());
        return CollectionModel.of(orders,
                linkTo(methodOn(OrderControllerImpl.class).getOrders()).withSelfRel());
    }

    @Override
    public ResponseEntity<?> addOrderItem(Long id, OrderLineItem orderItem) {
        if (orderItem == null)
            throw new RequestNotValidException();

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        orderLineItemService.addOrderItem(order, orderItem.getProductId(), orderItem.getQuantity());

        order.getOrderLineItemsList().add(orderItem);

        EntityModel<Order> entityModel = orderAssembler.toModel(order);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    @Override
    public CollectionModel<EntityModel<OrderLineItem>> getOrderItemsByOrderId(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        List<EntityModel<OrderLineItem>> orderLineItems = orderLineItemService.getOrderItemByOrder(order)
                .stream()
                .map(orderItemAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orderLineItems,
                linkTo(methodOn(OrderControllerImpl.class).getOrderItemsByOrderId(id)).withSelfRel());
    }
}
