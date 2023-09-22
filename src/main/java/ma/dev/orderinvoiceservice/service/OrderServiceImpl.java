package ma.dev.orderinvoiceservice.service;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.assemblers.OrderAssembler;
import ma.dev.orderinvoiceservice.assemblers.OrderItemAssembler;
import ma.dev.orderinvoiceservice.controller.OrderController;
import ma.dev.orderinvoiceservice.controller.OrderControllerImpl;
import ma.dev.orderinvoiceservice.dto.OrderLineItemDto;
import ma.dev.orderinvoiceservice.dto.OrderRequest;
import ma.dev.orderinvoiceservice.exceptions.OrderNotFoundException;
import ma.dev.orderinvoiceservice.model.Invoice;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import ma.dev.orderinvoiceservice.repository.OrderLineItemRepository;
import ma.dev.orderinvoiceservice.repository.OrderRepository;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemServiceImpl orderLineItemService;
    private final OrderAssembler orderAssembler;
    private final OrderItemAssembler orderItemAssembler;

    // public List<Order> getOrders() {

    // return orderRepository.findAll();
    // }

    // public Order addOrder(ArrayList<OrderLineItem> orderLineItems, Long clientId,
    // Invoice invoice) {
    // Order order = Order.builder()
    // .id(null)
    // .orderLineItemsList(orderLineItems)
    // .clientId(clientId)
    // .orderNumber(UUID.randomUUID().toString())
    // .invoiceId(invoice)
    // .build();

    // return order;
    // }

    // public Order findOrder(Long id) {
    // return orderRepository.findById(id).get();
    // }

    // public Order addOrderItem(Long id, OrderLineItem orderItem) {
    // Order order = findOrder(id);
    // orderLineItemService.addOrderItem(order, orderItem.getProductId(),
    // orderItem.getQuantity());
    // order.getOrderLineItemsList().add(orderItem);

    // return order;
    // }

    @Override
    public EntityModel<Order> getOrder(Long id) {
        // Invoice invoice = invoiceRespository.findById(id).get();

        // // get Client from clients-service
        // // and load it into the response
        // Long clientId = invoice.getClientId();
        // invoice.setClient(customerServiceClient.findClientById(clientId));

        // // Load all invoiceItems into invoice
        // invoice.setInvoiceItems(invoiceItemsRepository.findByInvoiceId(id));

        // // get invoiceItems from products-service
        // // and loads them into the response
        // invoice.getInvoiceItems().forEach(invoiceItem -> {
        // Long productId = invoiceItem.getProductId();
        // invoiceItem.setProduct(productServiceClient.findProductById(productId));
        // });

        // retrieves an Order
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        // order.setOrderLineItemsList(orderLineItemService.getOrderLineItemByOrderId(id));
        return orderAssembler.toModel(order);
    }

    @Override
    public ResponseEntity<?> addOrder(List<OrderLineItem> orderLineItems, Invoice invoice, Long clientId) {
        Order order = Order.builder()
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }

    @Override
    public ResponseEntity<?> replaceOrder(Long id, Order newOrder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'replaceOrder'");
    }

    @Override
    public CollectionModel<EntityModel<Order>> getOrders() {
        List<EntityModel<Order>> orders = orderRepository.findAll().stream()
                .map(orderAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(orders,
                linkTo(methodOn(OrderControllerImpl.class).getOrders()).withSelfRel());
    }

    @Override
    public ResponseEntity<?> addOrderItem(Long id, OrderLineItem orderItem) {
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
    public CollectionModel<EntityModel<Order>> getOrderEager(Long id) {
        List<EntityModel<Order>> orders = orderRepository.findByClientId(id).stream()
                    .map(orderAssembler::toModel)
                    .collect(Collectors.toList());
        
        return CollectionModel.of(orders,
            linkTo(methodOn(OrderControllerImpl.class).getOrdersEager(id)).withSelfRel()
        );
    }

    @Override
    public CollectionModel<EntityModel<OrderLineItem>> getOrderItemsByOrderId(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        List<EntityModel<OrderLineItem>> orderLineItems = orderLineItemService.getOrderItemByOrder(order).stream()
            .map(orderItemAssembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(orderLineItems,
            linkTo(methodOn(OrderControllerImpl.class).getOrderItemsByOrderId(id)).withSelfRel()
            );
    }
}
