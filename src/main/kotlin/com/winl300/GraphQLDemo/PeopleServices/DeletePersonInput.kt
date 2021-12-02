package com.winl300.GraphQLDemo.PeopleServices

/**
 * This class represents the input for deleting a person, an id (String form of UUID) is required
 *
 * @author Korey Sniezek
 * @date 1 Dec 2021
 * @param id String UUID
 */
data class DeletePersonInput (
    // String representation of a UUID
    val id: String
        )