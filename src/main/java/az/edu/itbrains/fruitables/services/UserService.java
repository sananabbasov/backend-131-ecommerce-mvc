package az.edu.itbrains.fruitables.services;

import az.edu.itbrains.fruitables.dtos.auth.RegisterDto;
import az.edu.itbrains.fruitables.models.User;

public interface UserService {
    boolean registerUser(RegisterDto registerDto);

    User findUserByEmail(String username);
}
