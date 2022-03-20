package com.eon.demo.contactkeeperapi.data.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

}
