package studio.genesis.manager.order.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.factory.Mappers;
import studio.genesis.manager.order.models.StockMovement;
import studio.genesis.manager.order.responses.StockMovementPayloadRepresentation;
import studio.genesis.manager.order.responses.StockMovementRepresentation;

import java.util.List;

public interface StockMovementRepresentationMapper {

    StockMovementRepresentationMapper INSTANCE = Mappers.getMapper(StockMovementRepresentationMapper.class);

    @BeanMapping(resultType = StockMovementRepresentation.class)
    StockMovementRepresentation map(StockMovement item);

    @BeanMapping(resultType = StockMovement.class)
    StockMovement toModel(StockMovementRepresentation item);

    @BeanMapping(resultType = StockMovement.class)
    StockMovement payloadToModel(StockMovementPayloadRepresentation item);

    List<StockMovementRepresentation> mapList(List<StockMovement> items);
}
