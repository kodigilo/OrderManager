package studio.genesis.manager.order.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "stock_movements")
public class StockMovement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity", nullable = false, columnDefinition = "int default 0")
    private Integer quantity;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @JoinColumn(name = "id_item", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;
}
