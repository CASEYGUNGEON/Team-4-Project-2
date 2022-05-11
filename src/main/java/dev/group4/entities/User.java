package dev.group4.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reg_user")
public class User {

    /*@Column(name = "user_id")
    private String id;*/

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

}
