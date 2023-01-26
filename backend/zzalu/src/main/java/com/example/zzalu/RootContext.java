package com.example.zzalu;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootContext {
    @Bean
    public ModelMapper modelMapper() {
         ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull())
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE).setFieldMatchingEnabled(true);
        return modelMapper;
    }
}
