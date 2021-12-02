package com.winl300.GraphQLDemo.PeopleServices

import com.winl300.GraphQLDemo.MockData.MockDatabaseService
import org.springframework.stereotype.Component
import java.util.*

/**
 * This service acts as a layer between the mock database and the rest of the services
 *
 * @author Korey Sniezek
 * @date 1 Dec 2021
 */
@Component
class PeopleService(
        private val database: MockDatabaseService
) {

    /**
     * Get all if no name filter is passed in, get exact matches otherwise.
     *
     * @author Korey Sniezek
     * @date 25Nov2021
     * @param nameFilter, a string representing an expected person's name. If null, returns all
     * @param ageFilter, an integer representing age
     * @return List<Person>
     */
    fun getFiltered(nameFilter: String? = null, ageFilter: Int? = null): List<Person> {
        if(nameFilter == null && ageFilter == null) return database.people

        var people: List<Person> = database.people
        if(nameFilter != null) people = people.filter { it.name == nameFilter }
        if(ageFilter != null) people = people.filter { it.age == ageFilter }

        return people
    }

    /**
     * This function converts person input to a person object the adds it to the database, then returns the new person
     *
     * @author Korey Sniezek
     * @date 1 Dec 2021
     * @param input CreatePersonInput
     * @return Person
     */
    fun addPerson(input: CreatePersonInput): Person {
        val newPerson = Person.fromInput(input)
        database.addPerson(newPerson)
        return newPerson
    }

    /**
     * Returns a person object if the input id matches an id in the database
     *
     * @author Korey Sniezek
     * @param id UUID
     * @return a Person or Null
     */
    fun getPersonById(id: UUID): Person? {
        return database.people.firstOrNull{ it.id == id }
    }

    /**
     * Finds a person by id, deletes them from the database and returns the person object
     *
     * @author Korey Sniezek
     * @param id UUID
     * @return a person or Null
     */
    fun deletePersonById(id: UUID): Person {
        val person = database.people.first{ it.id == id}
        database.removePerson(person)
        return person
    }

    /**
     * Update person creates a new person object from the old person object, plus any changes that have been made.
     *   it deletes it from the database and readds the new object to reduce complexity
     *
     *   @author Korey Sniezek
     *   @param input UpdatePersonInput object
     *   @date 1 Dec 2021
     */
    fun updatePerson(input: UpdatePersonInput): Person {
        val id = UUID.fromString(input.id)
        val oldPerson = deletePersonById(id)
        val newPerson = Person(
                    id = id,
                    name = input.newName ?: oldPerson.name,
                    age = input.newAge ?: oldPerson.age
                )
        database.addPerson(newPerson)
        return newPerson
    }

    /**
     * Probably not the most elegant way to get a random person from the list, but will be used to help demonstrate
     *   calling functions from graphql
     *
     *   @author Korey Sniezek
     *   @return a random Person
     *   @date 1 Dec 2021
     */
    fun getRandomPerson(): Person {
        return database.people.asSequence().shuffled().take(1).first()
    }
}