package studio.genesis.manager.order.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import studio.genesis.manager.order.models.User;
import studio.genesis.manager.order.responses.PaginationUserResponseRepresentation;


@Mapper
public interface PaginationUserResponseRepresentationMapper {
    PaginationUserResponseRepresentationMapper INSTANCE = Mappers.getMapper(PaginationUserResponseRepresentationMapper.class);

    @BeanMapping(resultType = PaginationUserResponseRepresentation.class)
    @Mapping(target = "content",  expression = "java(UserRepresentationMapper.INSTANCE.mapList(item.getContent()))")
    @Mapping(target = "number", source = "item.number")
    @Mapping(target = "size", source = "item.size")
    @Mapping(target = "totalPages", source = "item.totalPages")
    @Mapping(target = "totalElements", source = "item.totalElements")
    PaginationUserResponseRepresentation map(Long count, Page<User> item);

}
