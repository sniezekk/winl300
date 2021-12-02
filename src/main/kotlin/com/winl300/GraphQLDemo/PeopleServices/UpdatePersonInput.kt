package com.winl300.GraphQLDemo.PeopleServices

/**
 * This class is to update an Existing person object. Id is required, the name and age fields are only required
 *      to change the existing values
 *
 * @author Korey Sniezek
 * @date 1 Dec 2021
 * @param id String representation of a UUID
 * @param newName The new name to be assigned to this person, does not change if null
 * @param newAge The new age to be assigned to this person, does not change if null
 */
data class UpdatePersonInput (
    val id: String,
    val newName: String? = null,
    val newAge: Int? = null
)
