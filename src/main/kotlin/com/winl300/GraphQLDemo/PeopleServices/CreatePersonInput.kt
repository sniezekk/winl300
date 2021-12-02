package com.winl300.GraphQLDemo.PeopleServices

/**
 * The purpose of this data class is to separate what is required for the creation of a person on input with the
 *   object that is stored in the database. The advantage to this is that if the person object has more fields that
 *   are not necessary, or can be added later, it will not require the user to input all of the unnecessary fields
 *
 * @author Korey Sniezek
 * @date 25Nov2021
 */
data class CreatePersonInput (
    // The person's full name
    val name: String,

    // The person's age
    val age: Int
    )