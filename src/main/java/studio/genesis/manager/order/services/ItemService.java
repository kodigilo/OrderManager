package studio.genesis.manager.order.services;

import org.springframework.stereotype.Service;
import studio.genesis.manager.order.models.Item;
import studio.genesis.manager.order.repositories.ItemRepository;
import studio.genesis.manager.order.support.AbstractCrudService;

@Service
public class ItemService extends AbstractCrudService<ItemRepository, Item, Long> {
}
