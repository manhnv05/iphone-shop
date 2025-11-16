package com.example.mobile_shop;

import com.cloudinary.Cloudinary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
public class ihoneshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ihoneshopApplication.class, args);
    }

    @Bean
    public Cloudinary uploadImgProduct() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "dwusxbhbr");
        config.put("api_key", "288669855548549");
        config.put("api_secret", "w2HmbVZgdBSaUTANN0Yk-sGodbg");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }

}
