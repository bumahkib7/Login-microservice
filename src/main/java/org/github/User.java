package org.github;


import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Username;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @org.hibernate.validator.constraints.UUID
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id = UUID.randomUUID();

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    @Username
    private String username;

    @Column(name = "password")
    @Password
    private String password;

    @Column(name = "email")
    @Email
    private String email;


    @Column(name = "phone_number")
    @Digits(integer = 10, fraction = 0)
    private String phone;





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
