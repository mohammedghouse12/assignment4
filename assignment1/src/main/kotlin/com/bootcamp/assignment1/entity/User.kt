package com.bootcamp.assignment1.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    var id: Int = 0

    @Column(name = "name")
    var name: String

    @Column(name = "email")
    var email: String

    @Column(name = "nationality")
    var nationality: String

    @Column(name = "salary")
    var salary: Int

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var userDetails: UserDetails? = null

    fun addUserDetails(details: UserDetails) {
        userDetails = details
        details.user = this
    }

    constructor(name: String, email: String, nationality: String, salary: Int) {
        this.name = name
        this.email = email
        this.nationality = nationality
        this.salary = salary
    }
}