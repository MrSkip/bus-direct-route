package com.traveling.busdirectroute;

import com.traveling.busdirectroute.exception.FileNotFoundRuntimeException;
import com.traveling.busdirectroute.service.FileCacheService;
import com.traveling.busdirectroute.util.SystemPropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class BusDirectRouteApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusDirectRouteApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BusDirectRouteApplication.class, args);
    }

    @Bean
    public ApplicationRunner validatePropertiesSet(@Value("${app.file-data-location-property-name}") String fileDataLocationPropertyName,
                                                   @Autowired FileCacheService fileCacheService) {
        return args -> {
            final String path = SystemPropertiesUtils.obtain(fileDataLocationPropertyName);
            if (!new File(path).exists()) {
                throw new FileNotFoundRuntimeException(String.format("File %s doesn't exist", path));
            }
            LOGGER.info("Data file location set to: {}", path);
            fileCacheService.loadBusRoutes();
        };
    }


}
