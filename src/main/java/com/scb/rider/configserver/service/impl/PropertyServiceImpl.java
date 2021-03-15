package com.scb.rider.configserver.service.impl;

import com.scb.rider.configserver.exception.PropertyNotFoundException;
import com.scb.rider.configserver.mapper.PropertyMapper;
import com.scb.rider.configserver.model.dto.PropertyDto;
import com.scb.rider.configserver.model.entity.Property;
import com.scb.rider.configserver.repository.PropertyRepository;
import com.scb.rider.configserver.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.environment.JdbcEnvironmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private JdbcEnvironmentRepository jdbcEnvironmentRepository;

    @Override
    public Environment findByApplicationAndProfileAndLabel(String application, String profile, String label) {
        return jdbcEnvironmentRepository.findOne(application, profile, label);
    }

    @Override
    public PropertyDto saveProperty(PropertyDto propertyDto) {
        return propertyMapper.toPropertyDto(propertyRepository.save(propertyMapper.toProperty(propertyDto)));
    }

    @Override
    public PropertyDto updateProperty(Long id, PropertyDto propertyDto) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException("Property not exist with id: "+id));
        property.setLabel(getValueToBeSaved(propertyDto.getLabel(), property.getLabel()));
        property.setProfile(getValueToBeSaved(propertyDto.getProfile(), property.getProfile()));
        property.setKey(getValueToBeSaved(propertyDto.getKey(), property.getKey()));
        property.setValue(getValueToBeSaved(propertyDto.getValue(), property.getValue()));
        property.setApplication(getValueToBeSaved(propertyDto.getApplication(), property.getApplication()));
        return propertyMapper.toPropertyDto(propertyRepository.save(property));
    }

    private String getValueToBeSaved(String  newValue, String existingValue) {
        if(StringUtils.isEmpty(newValue)) {
            return existingValue;
        } else {
            return newValue;
        }
    }
}
