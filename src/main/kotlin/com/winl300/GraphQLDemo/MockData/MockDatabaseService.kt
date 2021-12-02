package com.winl300.GraphQLDemo.MockData

import com.winl300.GraphQLDemo.PeopleServices.Person
import org.springframework.stereotype.Component

/**
 * The purpose of this class is to act as a simulated database
 *
 * @author Korey Sniezek
 * @date 25Nov2021
 *
 * Note: Getters/Setters are built into Kotlin by default.
 */
@Component
class MockDatabaseService {
    val people = mutableListOf(
        Person(name = "Joe Dirt", age = 42),
        Person(name = "Joe Bob", age = 4),
        Person(name = "Jane Dirt", age = 11),
        Person(name = "Joe Strummer", age = 15),
        Person(name = "Andy Anderson", age = 18),
        Person(name = "Marcy Mark", age = 29),
        Person(name = "Anna Apple", age = 48),
        Person(name = "Derpy McDerpson", age = 42),
        Person(name = "John Doe", age = 11),
        Person(name = "Jane Doe", age = 42),
        Person(name = "Joe Dirt", age = 1),
        Person(name = "Bob Dole", age = 100),
        Person(name = "Lucy McPersonFace", age = 3),
    )

    /**
     * This adds a person to the mock database, validation is done by higher layer service
     *
     * @author Korey Sniezek
     * @date 25Nov2021
     * @param input, requires a person object
     */
    fun addPerson(input: Person) {
        people.add(input)
    }

    /**
     * This removes a person from the mock database, validation is done by a higher layer service
     *
     * @author Korey Sniezek
     * @date 1 Dec 2021
     * @param input, a person object to be deleted
     */
    fun removePerson(input: Person) {
        people.remove(input)
    }
}