package com.winl300.GraphQLDemo.PeopleServices

import com.winl300.GraphQLDemo.MockData.MockDatabaseService
import org.springframework.stereotype.Component

@Component
class PeopleService(
        private val database: MockDatabaseService
) {

    /**
     * Get all if no name filter is passed in, get exact matches otherwise.
     *
     * @author: Korey Sniezek
     * @date: 25Nov2021
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

    fun addPerson(input: CreatePersonInput): Person {
        val newPerson = Person.fromInput(input)
        database.addPerson(newPerson)
        return newPerson
    }

}