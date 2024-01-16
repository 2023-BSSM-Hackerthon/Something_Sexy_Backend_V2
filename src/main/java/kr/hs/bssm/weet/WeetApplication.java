package kr.hs.bssm.weet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@ConfigurationPropertiesScan
public class WeetApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeetApplication.class, args);
    }

}
