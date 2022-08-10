package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    public EventDTO update(Long id, EventDTO dto) {
        try {
            Event event = eventRepository.getOne(id);
            dtoToEntity(dto, event);
            event = eventRepository.save(event);
            return new EventDTO(event);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Evento não encontrado!");
        }

    }

    private void dtoToEntity(EventDTO dto, Event event) {
        event.setDate(dto.getDate());
        event.setName(dto.getName());
        event.setUrl(dto.getUrl());

        City city = cityRepository.getOne(dto.getCityId());
        event.setCity(city);
    }
}
