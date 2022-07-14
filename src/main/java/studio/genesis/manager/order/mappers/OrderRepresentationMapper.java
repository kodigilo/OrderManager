package studio.genesis.manager.order.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import studio.genesis.manager.order.models.Order;
import studio.genesis.manager.order.responses.OrderPayloadRepresentation;
import studio.genesis.manager.order.responses.OrderRepresentation;

import java.util.List;
@Mapper
public interface OrderRepresentationMapper {

    OrderRepresentationMapper INSTANCE = Mappers.getMapper(OrderRepresentationMapper.class);

    @BeanMapping(resultType = OrderRepresentation.class)
    OrderRepresentation map(Order item);

    @BeanMapping(resultType = Order.class)
    Order toModel(OrderRepresentation item);

    @BeanMapping(resultType = Order.class)
    Order payloadToModel(OrderPayloadRepresentation item);

    List<OrderRepresentation> mapList(List<Order> items);
}
