package studio.genesis.manager.order.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import studio.genesis.manager.order.models.StockMovement;
import studio.genesis.manager.order.responses.PaginationStockMovementResponseRepresentation;

public interface PaginationStockMovementResponseRepresentationMapper {
    PaginationStockMovementResponseRepresentationMapper INSTANCE = Mappers.getMapper(PaginationStockMovementResponseRepresentationMapper.class);

    @BeanMapping(resultType = PaginationStockMovementResponseRepresentation.class)
    @Mapping(target = "content",  expression = "java(StockMovementRepresentationMapper.INSTANCE.mapList(item.getContent()))")
    @Mapping(target = "number", source = "item.number")
    @Mapping(target = "size", source = "item.size")
    @Mapping(target = "totalPages", source = "item.totalPages")
    @Mapping(target = "totalElements", source = "item.totalElements")
    PaginationStockMovementResponseRepresentation map(Long count, Page<StockMovement> item);

}
