package az.edu.itbrains.fruitables.config;

import az.edu.itbrains.fruitables.services.UserService;
import az.edu.itbrains.fruitables.services.impls.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class Configure {


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }




}
