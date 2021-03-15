package com.scb.rider.configserver.mapper;

import com.scb.rider.configserver.model.dto.PropertyDto;
import com.scb.rider.configserver.model.entity.Property;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PropertyMapper {

    public Property toProperty(PropertyDto propertyDto) {
        if(Objects.isNull(propertyDto)) {
            return null;
        }
        Property property = Property.builder()
                .id(propertyDto.getId())
                .key(propertyDto.getKey())
                .value(propertyDto.getValue())
                .application(propertyDto.getApplication())
                .profile(propertyDto.getProfile())
                .label(propertyDto.getLabel())
                .build();
        return property;
    }

    public PropertyDto toPropertyDto(Property property) {
        if(Objects.isNull(property)) {
            return null;
        }
        PropertyDto propertyDto = PropertyDto.builder()
                .id(property.getId())
                .key(property.getKey())
                .value(property.getValue())
                .application(property.getApplication())
                .profile(property.getProfile())
                .label(property.getLabel())
                .build();
        return propertyDto;
    }
}
