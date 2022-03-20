package com.eon.demo.contactkeeperapi.data.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ContactEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user"), nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private ContactType contactType;

}
