package studio.genesis.manager.order.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import studio.genesis.manager.order.models.User;
import studio.genesis.manager.order.responses.UserPayloadRepresentation;
import studio.genesis.manager.order.responses.UserRepresentation;

import java.util.List;
@Mapper
public interface UserRepresentationMapper {

    UserRepresentationMapper INSTANCE = Mappers.getMapper(UserRepresentationMapper.class);

    @BeanMapping(resultType = UserRepresentation.class)
    UserRepresentation map(User item);

    @BeanMapping(resultType = User.class)
    User toModel(UserRepresentation item);

    @BeanMapping(resultType = User.class)
    User payloadToModel(UserPayloadRepresentation item);

    List<UserRepresentation> mapList(List<User> items);
}
