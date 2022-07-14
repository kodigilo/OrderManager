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
import studio.genesis.manager.order.mappers.OrderRepresentationMapper;
import studio.genesis.manager.order.mappers.PaginationOrderResponseRepresentationMapper;
import studio.genesis.manager.order.responses.AllOrderResponseRepresentation;
import studio.genesis.manager.order.responses.OrderPayloadRepresentation;
import studio.genesis.manager.order.responses.OrderRepresentation;
import studio.genesis.manager.order.responses.PaginationOrderResponseRepresentation;
import studio.genesis.manager.order.services.OrderService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@Slf4j
public class OrderController implements OrderControllerApi {

    private final OrderService itemService;

    @Override
    public ResponseEntity<PaginationOrderResponseRepresentation> pagination(String order, String orderby, Integer page, Integer limit) {
        log.info("{}: pagination", getClass().getSimpleName());
        try {
            PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.valueOf(order), orderby));
            var pageable = itemService.pageable(pageRequest);
            return ResponseEntity.ok(PaginationOrderResponseRepresentationMapper.INSTANCE.map(pageable.getTotalElements(), pageable));
        } catch (Exception e) {
            log.error("{}: pagination error", getClass().getSimpleName(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao listar dados");
        }
    }

    @Override
    public ResponseEntity<AllOrderResponseRepresentation> all() {
        log.info("{}: all", getClass().getSimpleName());
        try {
            return ResponseEntity.ok(new AllOrderResponseRepresentation().content(OrderRepresentationMapper.INSTANCE.mapList(itemService.all())));
        } catch (Exception e) {
            log.error("{}: all error", getClass().getSimpleName(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao listar dados");
        }
    }

    @Override
    public ResponseEntity<OrderRepresentation> add(OrderPayloadRepresentation body) {
        log.info("{}: add", getClass().getSimpleName());
        try {
            return ResponseEntity.ok(OrderRepresentationMapper.INSTANCE.map(itemService.save(OrderRepresentationMapper.INSTANCE.payloadToModel(body))));
        } catch (Exception e) {
            log.error("{}: add error", getClass().getSimpleName(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao add ordem");
        }
    }

    @Override
    public ResponseEntity<OrderRepresentation> get(Integer id) {
        log.info("{}: get", getClass().getSimpleName());
        try {
            return ResponseEntity.ok(OrderRepresentationMapper.INSTANCE.map(itemService.find(id.longValue())));
        } catch (Exception e) {
            log.error("{}: get error", getClass().getSimpleName(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar ordem");
        }
    }

    @Override
    public ResponseEntity<OrderRepresentation> update(Integer id, OrderRepresentation body) {
        log.info("{}: update", getClass().getSimpleName());
        try {
            if (itemService.find(id.longValue()) == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order não encontrada");
            return ResponseEntity.ok(OrderRepresentationMapper.INSTANCE.map(itemService.save(OrderRepresentationMapper.INSTANCE.toModel(body))));
        } catch (Exception e) {
            log.error("{}: update error", getClass().getSimpleName(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar ordem");
        }
    }

    @Override
    public ResponseEntity<OrderRepresentation> delete(Integer id) {
        log.info("{}: delete", getClass().getSimpleName());
        try {
            var item = OrderRepresentationMapper.INSTANCE.map(itemService.find(id.longValue()));
            itemService.delete(id.longValue());
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            log.error("{}: delete error", getClass().getSimpleName(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao deletar ordem");
        }
    }


}
