package studio.genesis.manager.order.controllers;

import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import studio.genesis.manager.order.mappers.ItemRepresentationMapper;
import studio.genesis.manager.order.mappers.PaginationItemResponseRepresentationMapper;
import studio.genesis.manager.order.responses.*;
import studio.genesis.manager.order.services.ItemService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class ItemController implements ItemControllerApi {

    private final ItemService itemService;

    @Override
    public ResponseEntity<PaginationItemResponseRepresentation> pagination(String order, String orderby, Integer page, Integer limit) {
        try {
            PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.valueOf(order), orderby));
            var pageable = itemService.pageable(pageRequest);
            return ResponseEntity.ok(PaginationItemResponseRepresentationMapper.INSTANCE.map(pageable.getTotalElements(), pageable));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao listar dados");
        }
    }

    @Override
    public ResponseEntity<AllItemResponseRepresentation> all() {
        try {
            return ResponseEntity.ok(new AllItemResponseRepresentation().content(ItemRepresentationMapper.INSTANCE.mapList(itemService.all())));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao listar dados");
        }
    }

    @Override
    public ResponseEntity<ItemRepresentation> add(ItemPayloadRepresentation body) {
        try {
            return ResponseEntity.ok(ItemRepresentationMapper.INSTANCE.map(itemService.save(ItemRepresentationMapper.INSTANCE.payloadToModel(body))));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao add item");
        }
    }

    @Override
    public ResponseEntity<ItemRepresentation> get(Integer id) {
        try {
            return ResponseEntity.ok(ItemRepresentationMapper.INSTANCE.map(itemService.find(id.longValue())));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar item");
        }
    }

    @Override
    public ResponseEntity<ItemRepresentation> update(Integer id, ItemRepresentation body) {
        try {
            if (itemService.find(id.longValue()) == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item não encontrado");
            return ResponseEntity.ok(ItemRepresentationMapper.INSTANCE.map(itemService.save(ItemRepresentationMapper.INSTANCE.toModel(body))));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar item");
        }
    }

    @Override
    public ResponseEntity<ItemRepresentation> delete(Integer id) {
        try {
            var item = ItemRepresentationMapper.INSTANCE.map(itemService.find(id.longValue()));
            itemService.delete(id.longValue());
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao deletar item");
        }
    }


}
