package studio.genesis.manager.order.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import studio.genesis.manager.order.mappers.StockMovementRepresentationMapper;
import studio.genesis.manager.order.mappers.PaginationStockMovementResponseRepresentationMapper;
import studio.genesis.manager.order.responses.AllStockMovementResponseRepresentation;
import studio.genesis.manager.order.responses.StockMovementPayloadRepresentation;
import studio.genesis.manager.order.responses.StockMovementRepresentation;
import studio.genesis.manager.order.responses.PaginationStockMovementResponseRepresentation;
import studio.genesis.manager.order.services.StockMovementService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@Slf4j
public class StockMovementController implements StockMovementControllerApi {

    private final StockMovementService itemService;

    @Override
    public ResponseEntity<PaginationStockMovementResponseRepresentation> pagination(String order, String orderby, Integer page, Integer limit) {
        log.info("{}: pagination", getClass().getSimpleName());
        try {
            PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.valueOf(order), orderby));
            var pageable = itemService.pageable(pageRequest);
            return ResponseEntity.ok(PaginationStockMovementResponseRepresentationMapper.INSTANCE.map(pageable.getTotalElements(), pageable));
        } catch (Exception e) {
            log.error("{}: pagination error", getClass().getSimpleName(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao listar dados");
        }
    }

    @Override
    public ResponseEntity<AllStockMovementResponseRepresentation> all() {
        log.info("{}: all", getClass().getSimpleName());
        try {
            return ResponseEntity.ok(new AllStockMovementResponseRepresentation().content(StockMovementRepresentationMapper.INSTANCE.mapList(itemService.all())));
        } catch (Exception e) {
            log.error("{}: all error", getClass().getSimpleName(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao listar dados");
        }
    }

    @Override
    public ResponseEntity<StockMovementRepresentation> add(StockMovementPayloadRepresentation body) {
        log.info("{}: add", getClass().getSimpleName());
        try {
            return ResponseEntity.ok(StockMovementRepresentationMapper.INSTANCE.map(itemService.save(StockMovementRepresentationMapper.INSTANCE.payloadToModel(body))));
        } catch (Exception e) {
            log.error("{}: add error", getClass().getSimpleName(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao add ordem");
        }
    }

    @Override
    public ResponseEntity<StockMovementRepresentation> get(Integer id) {
        log.info("{}: get", getClass().getSimpleName());
        try {
            return ResponseEntity.ok(StockMovementRepresentationMapper.INSTANCE.map(itemService.find(id.longValue())));
        } catch (Exception e) {
            log.error("{}: get error", getClass().getSimpleName(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar ordem");
        }
    }

    @Override
    public ResponseEntity<StockMovementRepresentation> update(Integer id, StockMovementRepresentation body) {
        log.info("{}: update", getClass().getSimpleName());
        try {
            if (itemService.find(id.longValue()) == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StockMovement não encontrada");
            return ResponseEntity.ok(StockMovementRepresentationMapper.INSTANCE.map(itemService.save(StockMovementRepresentationMapper.INSTANCE.toModel(body))));
        } catch (Exception e) {
            log.error("{}: update error", getClass().getSimpleName(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar ordem");
        }
    }

    @Override
    public ResponseEntity<StockMovementRepresentation> delete(Integer id) {
        log.info("{}: delete", getClass().getSimpleName());
        try {
            var item = StockMovementRepresentationMapper.INSTANCE.map(itemService.find(id.longValue()));
            itemService.delete(id.longValue());
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            log.error("{}: delete error", getClass().getSimpleName(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao deletar ordem");
        }
    }


}
