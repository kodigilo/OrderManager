package studio.genesis.manager.order.services;


import org.springframework.stereotype.Service;
import studio.genesis.manager.order.models.StockMovement;
import studio.genesis.manager.order.repositories.StockMovementRepository;
import studio.genesis.manager.order.support.AbstractCrudService;

@Service
public class StockMovementService extends AbstractCrudService<StockMovementRepository, StockMovement, Long> {

}
