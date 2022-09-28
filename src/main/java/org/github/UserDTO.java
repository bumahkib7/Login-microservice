package org.github;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * It's a data transfer object (DTO) that holds the data for a user
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;


}
