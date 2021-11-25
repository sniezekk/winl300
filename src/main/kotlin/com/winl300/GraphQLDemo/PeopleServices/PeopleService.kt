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
     * @return List<Person>
     */
    fun getFiltered(nameFilter: String?): List<Person> {
        return if(nameFilter != null) database.people.filter { it.name == nameFilter }
        else database.people
    }

}