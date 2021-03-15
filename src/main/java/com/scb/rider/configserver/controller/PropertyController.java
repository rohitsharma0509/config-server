package com.scb.rider.configserver.controller;

import com.scb.rider.configserver.exception.PropertyNotFoundException;
import com.scb.rider.configserver.model.dto.PropertyDto;
import com.scb.rider.configserver.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/props")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyDto> saveNewProperty(@RequestBody PropertyDto propertyDto) {
        PropertyDto createdPropertyDto = propertyService.saveProperty(propertyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPropertyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDto> updateExistingProperty(@PathVariable Long id, @RequestBody PropertyDto propertyDto) {
        try {
            PropertyDto updatedPropertyDto = propertyService.updateProperty(id, propertyDto);
            return ResponseEntity.ok(updatedPropertyDto);
        } catch(PropertyNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{application}")
    public ResponseEntity<Environment> getPropertyByApplication(@PathVariable String application) {
        Environment env = propertyService.findByApplicationAndProfileAndLabel(application, null, null);
        return ResponseEntity.ok(env);
    }

    @GetMapping("/{application}/{profile}")
    public ResponseEntity<Environment> getPropertyByApplication(@PathVariable String application, @PathVariable String profile) {
        Environment env = propertyService.findByApplicationAndProfileAndLabel(application, profile, null);
        return ResponseEntity.ok(env);
    }

    @GetMapping("/{application}/{profile}/{label}")
    public ResponseEntity<Environment> getPropertyByApplication(@PathVariable String application, @PathVariable String profile, @PathVariable String label) {
        Environment env = propertyService.findByApplicationAndProfileAndLabel(application, profile, label);
        return ResponseEntity.ok(env);
    }

}