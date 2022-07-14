package studio.genesis.manager.order.controllers;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import studio.genesis.manager.order.mappers.ItemRepresentationMapper;
import studio.genesis.manager.order.models.Item;
import studio.genesis.manager.order.responses.ItemPayloadRepresentation;
import studio.genesis.manager.order.responses.ItemRepresentation;
import studio.genesis.manager.order.services.ItemService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class ItemControllerTest {

    @InjectMocks
    private ItemController controller;

    @Mock
    private ItemService itemService;

    @Random Item model1;
    @Random Item model2;

    @Test
    void pagination() {
        List<Item> list = new ArrayList<>();
        list.add(model1);
        list.add(model2);
        PageRequest pageRequest = PageRequest.of(0, 50, Sort.by(Sort.Direction.valueOf("DESC"), "name"));
        Mockito.when(itemService.pageable(pageRequest)).thenReturn(new PageImpl<>(list, pageRequest, list.size()));
        var result = controller.pagination("DESC", "name", 0, 50);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void all() {
        List<Item> list = new ArrayList<>();
        list.add(model1);
        list.add(model2);
        Mockito.when(itemService.all()).thenReturn(list);
        var result = controller.all();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void add() {
        var payload = new ItemPayloadRepresentation();
        payload.setName("Teste");
        var toModel = new Item();
        toModel.setName(payload.getName());
        Mockito.when(itemService.save(toModel)).thenReturn(model1);
        var result = controller.add(payload);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void get() {
        model1.setId(1l);
        Mockito.when(itemService.find(model1.getId())).thenReturn(model1);
        var result = controller.get(model1.getId().intValue());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void update() {
        model1.setId(1l);
        Mockito.when(itemService.save(model1)).thenReturn(model1);
        Mockito.when(itemService.find(model1.getId())).thenReturn(model1);
        var payload = new ItemRepresentation();
        payload.setName(model1.getName());
        payload.setId(model1.getId().intValue());
        var result = controller.update(model1.getId().intValue(), payload);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void delete() {
        var result = controller.delete(model1.getId().intValue());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}