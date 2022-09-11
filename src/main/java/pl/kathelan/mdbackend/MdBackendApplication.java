package pl.kathelan.mdbackend;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.kathelan.mdbackend.api.dtos.RoleDto;
import pl.kathelan.mdbackend.entities.Role;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableWebMvc
public class MdBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdBackendApplication.class, args);
    }

}
