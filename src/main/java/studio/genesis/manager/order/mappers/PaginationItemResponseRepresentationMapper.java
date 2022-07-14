package studio.genesis.manager.order.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import studio.genesis.manager.order.models.Item;
import studio.genesis.manager.order.responses.PaginationItemResponseRepresentation;

@Mapper
public interface PaginationItemResponseRepresentationMapper {

    PaginationItemResponseRepresentationMapper INSTANCE = Mappers.getMapper(PaginationItemResponseRepresentationMapper.class);

    @BeanMapping(resultType = PaginationItemResponseRepresentation.class)
    @Mapping(target = "content",  expression = "java(ItemRepresentationMapper.INSTANCE.mapList(item.getContent()))")
    @Mapping(target = "number", source = "item.number")
    @Mapping(target = "size", source = "item.size")
    @Mapping(target = "totalPages", source = "item.totalPages")
    @Mapping(target = "totalElements", source = "item.totalElements")
    PaginationItemResponseRepresentation map(Long count,Page<Item> item);

}
