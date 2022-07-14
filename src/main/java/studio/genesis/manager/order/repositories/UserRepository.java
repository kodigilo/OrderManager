package studio.genesis.manager.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.genesis.manager.order.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
