package studio.genesis.manager.order.services;

import org.springframework.stereotype.Service;
import studio.genesis.manager.order.models.User;
import studio.genesis.manager.order.repositories.UserRepository;
import studio.genesis.manager.order.support.AbstractCrudService;

@Service
public class UserService extends AbstractCrudService<UserRepository,User, Long> {

}

