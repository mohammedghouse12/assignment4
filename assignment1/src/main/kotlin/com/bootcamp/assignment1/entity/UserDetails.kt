package com.bootcamp.assignment1.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "user_details")
class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    val id: Int = 0

    @Column(name = "age")
    var age: Int? = null

    @Column(name = "date_of_birth")
    var dateOfBirth: String? = null

    @Column(name = "gender")
    var gender: String? = null

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @JsonBackReference
    var user : User? = null

    constructor()

}