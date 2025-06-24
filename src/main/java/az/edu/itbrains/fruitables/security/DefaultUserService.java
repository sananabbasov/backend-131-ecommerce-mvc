package az.edu.itbrains.fruitables.security;

import az.edu.itbrains.fruitables.models.User;
import az.edu.itbrains.fruitables.repositories.UserRepository;
import az.edu.itbrains.fruitables.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User findUser = userRepository.findByEmail(username);
        if (findUser != null){
            org.springframework.security.core.userdetails.User loggedUser = new org.springframework.security.core.userdetails.User(
              findUser.getEmail(),
              findUser.getPassword(),
              true,
              true,
              true,
              true,
              findUser.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
            );
            return loggedUser;
        }
        throw new UsernameNotFoundException("User not found");
    }
}
