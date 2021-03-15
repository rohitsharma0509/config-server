package com.scb.rider.configserver.service;

import com.scb.rider.configserver.model.dto.PropertyDto;
import org.springframework.cloud.config.environment.Environment;

public interface PropertyService {
	Environment findByApplicationAndProfileAndLabel(String application, String profile, String label);

	PropertyDto saveProperty(PropertyDto propertyDto);

	PropertyDto updateProperty(Long id, PropertyDto propertyDto);
}
