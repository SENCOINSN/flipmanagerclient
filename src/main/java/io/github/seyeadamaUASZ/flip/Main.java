package io.github.seyeadamaUASZ.flip;

import io.github.seyeadamaUASZ.flip.dto.FeatureDTO;
import io.github.seyeadamaUASZ.flip.helper.FlipManagerDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    private FlipManagerDialect managerUtils;


    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Override
    public void run(String... args) throws Exception {
        boolean check = managerUtils.flipCheck("feature_add_block_user");
        System.out.println(" check "+ check);

    }
}
