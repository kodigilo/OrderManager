package studio.genesis.manager.order.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import studio.genesis.manager.order.models.Item;
import studio.genesis.manager.order.responses.ItemPayloadRepresentation;
import studio.genesis.manager.order.responses.ItemRepresentation;

import java.util.List;

@Mapper
public interface ItemRepresentationMapper {

    ItemRepresentationMapper INSTANCE = Mappers.getMapper(ItemRepresentationMapper.class);

    @BeanMapping(resultType = ItemRepresentation.class)
    ItemRepresentation map(Item item);

    @BeanMapping(resultType = Item.class)
    Item toModel(ItemRepresentation item);

    @BeanMapping(resultType = Item.class)
    Item payloadToModel(ItemPayloadRepresentation item);

    List<ItemRepresentation> mapList(List<Item> items);
}
