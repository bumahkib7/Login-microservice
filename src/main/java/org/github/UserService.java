package org.github;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Transactional
@ApplicationScoped
public class UserService {

    @Inject
    UserRepo userRepo;

    Logger logger = Logger.getLogger(UserService.class.getName());

    public static UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone()
        );

    }


    //

    // create new user
    public User createNewUser(UserDTO userDTO) {
        var user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        return userRepo.save(user);
    }

    //find user by username
    public User findUserByUsername(String username) {
        var user = userRepo.findByUsername(username);
        Optional<User> userOptional = Optional.ofNullable(user);
        userOptional.ifPresentOrElse(user1 -> {
            logger.info("User found");
        }, this::run);
        return user;
    }

    private void run() {
        logger.info("User not found");
    }

    //find user by UUID
    public User findUserByUUID(String uuid) {
        Stream<User> userStream = userRepo.findAll().stream();
        Optional<User> user = userStream.filter(u -> u.getId() == UUID.fromString(uuid)).findFirst();
        return user.orElseThrow();
    }


    //delete user by UUID
    public User deleteUserByUUID(String uuid) {
        var user = userRepo.findUserById(UUID.fromString(uuid));
        userRepo.delete(user);
        return user;
    }
}




