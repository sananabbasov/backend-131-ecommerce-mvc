package az.edu.itbrains.fruitables.services.impls;

import az.edu.itbrains.fruitables.dtos.auth.RegisterDto;
import az.edu.itbrains.fruitables.models.User;
import az.edu.itbrains.fruitables.repositories.UserRepository;
import az.edu.itbrains.fruitables.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private  UserRepository userRepository;
    private  ModelMapper modelMapper;
    private  PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder){

        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }




    @Override
    public boolean registerUser(RegisterDto registerDto) {

        User findUser = userRepository.findByEmail(registerDto.getEmail());
        if (findUser != null){
            return false;
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setSurname(registerDto.getSurname());
        user.setEmail(registerDto.getEmail());
        String password = passwordEncoder.encode(registerDto.getPassword());
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    @Override
    public User findUserByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}
