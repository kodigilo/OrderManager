package studio.genesis.manager.order.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity", nullable = false, columnDefinition = "int default 0")
    private Integer quantity;

    @Column(name = "creation_date", nullable = false)
    private OffsetDateTime creationDate;

    @JoinColumn(name = "id_item", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;

    @JoinColumn(name = "id_user", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
