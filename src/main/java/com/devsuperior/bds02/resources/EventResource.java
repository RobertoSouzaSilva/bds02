package com.devsuperior.bds02.resources;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/events")
public class EventResource {

    @Autowired
    private EventService eventService;

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto) {
        EventDTO cat = eventService.update(id, dto);
        return ResponseEntity.ok().body(cat);
    }
}