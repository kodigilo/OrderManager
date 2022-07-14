package studio.genesis.manager.order.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import studio.genesis.manager.order.models.Order;
import studio.genesis.manager.order.responses.PaginationOrderResponseRepresentation;

public interface PaginationOrderResponseRepresentationMapper {
    PaginationOrderResponseRepresentationMapper INSTANCE = Mappers.getMapper(PaginationOrderResponseRepresentationMapper.class);

    @BeanMapping(resultType = PaginationOrderResponseRepresentation.class)
    @Mapping(target = "content",  expression = "java(OrderRepresentationMapper.INSTANCE.mapList(item.getContent()))")
    @Mapping(target = "number", source = "item.number")
    @Mapping(target = "size", source = "item.size")
    @Mapping(target = "totalPages", source = "item.totalPages")
    @Mapping(target = "totalElements", source = "item.totalElements")
    PaginationOrderResponseRepresentation map(Long count, Page<Order> item);

}
