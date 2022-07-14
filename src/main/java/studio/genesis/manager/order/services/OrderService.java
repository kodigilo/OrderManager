package studio.genesis.manager.order.services;

import org.springframework.stereotype.Service;
import studio.genesis.manager.order.models.Order;
import studio.genesis.manager.order.repositories.OrderRepository;
import studio.genesis.manager.order.support.AbstractCrudService;

@Service
public class OrderService extends AbstractCrudService<OrderRepository, Order, Long> {

}
