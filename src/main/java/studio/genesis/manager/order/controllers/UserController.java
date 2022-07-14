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
import studio.genesis.manager.order.mappers.UserRepresentationMapper;
import studio.genesis.manager.order.mappers.PaginationUserResponseRepresentationMapper;
import studio.genesis.manager.order.responses.AllUserResponseRepresentation;
import studio.genesis.manager.order.responses.UserPayloadRepresentation;
import studio.genesis.manager.order.responses.UserRepresentation;
import studio.genesis.manager.order.responses.PaginationUserResponseRepresentation;
import studio.genesis.manager.order.services.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class UserController implements UserControllerApi {

    private final UserService itemService;

    @Override
    public ResponseEntity<PaginationUserResponseRepresentation> pagination(String order, String orderby, Integer page, Integer limit) {
        try {
            PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.valueOf(order), orderby));
            var pageable = itemService.pageable(pageRequest);
            return ResponseEntity.ok(PaginationUserResponseRepresentationMapper.INSTANCE.map(pageable.getTotalElements(), pageable));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao listar dados");
        }
    }

    @Override
    public ResponseEntity<AllUserResponseRepresentation> all() {
        try {
            return ResponseEntity.ok(new AllUserResponseRepresentation().content(UserRepresentationMapper.INSTANCE.mapList(itemService.all())));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao listar dados");
        }
    }

    @Override
    public ResponseEntity<UserRepresentation> add(UserPayloadRepresentation body) {
        try {
            return ResponseEntity.ok(UserRepresentationMapper.INSTANCE.map(itemService.save(UserRepresentationMapper.INSTANCE.payloadToModel(body))));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao add usuário");
        }
    }

    @Override
    public ResponseEntity<UserRepresentation> get(Integer id) {
        try {
            return ResponseEntity.ok(UserRepresentationMapper.INSTANCE.map(itemService.find(id.longValue())));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar usuário");
        }
    }

    @Override
    public ResponseEntity<UserRepresentation> update(Integer id, UserRepresentation body) {
        try {
            if (itemService.find(id.longValue()) == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
            return ResponseEntity.ok(UserRepresentationMapper.INSTANCE.map(itemService.save(UserRepresentationMapper.INSTANCE.toModel(body))));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar usuário");
        }
    }

    @Override
    public ResponseEntity<UserRepresentation> delete(Integer id) {
        try {
            var item = UserRepresentationMapper.INSTANCE.map(itemService.find(id.longValue()));
            itemService.delete(id.longValue());
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao deletar usuário");
        }
    }


}
