package ma.dev.orderinvoiceservice.service;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.dto.OrderLineItemDto;
import ma.dev.orderinvoiceservice.dto.OrderRequest;
import ma.dev.orderinvoiceservice.model.Invoice;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import ma.dev.orderinvoiceservice.repository.OrderLineItemRepository;
import ma.dev.orderinvoiceservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemService orderLineItemService;

    public void placeOrder(OrderRequest orderRequest) {
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

        // OrderLineItem orderLineItems = new OrderLineItem();
        // orderLineItems.setPrice(orderLineItemsDto.getPrice());
        // orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        // orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return null;
        // return orderLineItems;
    }

    public List<Order> getOrders() {
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

        return orderRepository.findAll();
    }

    public Order addOrder(ArrayList<OrderLineItem> orderLineItems, Long clientId, Invoice invoice) {
        // Order order = Order.builder()
        //         .id(null)
        //         .orderLineItemsList(orderLineItems)
        //         .clientId(clientId)
        //         .orderNumber(UUID.randomUUID().toString())
        //         .invoiceId(invoice)
        //         .build();

        return orderRepository.save(new Order(null, UUID.randomUUID().toString(), orderLineItems, null, 1l));
    }

    public Order findOrder(Long id) {
        return orderRepository.findById(id).get();
    }

    public Order addOrderItem(Long id, OrderLineItem orderItem) {
        Order order = findOrder(id);
        orderLineItemService.addOrderItem(order, orderItem.getProductId(), orderItem.getQuantity());
        order.getOrderLineItemsList().add(orderItem);

        return order;
    }
}
