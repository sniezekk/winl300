package com.winl300.GraphQLDemo.PeopleServices

/**
 * The purpose of this class is to contain common information about a person.
 *
 * @author: Korey Sniezek
 * @date: 25Nov2021
 * Note: Kotlin data classes use built in methods for fetching data, therefore, there are no obvious data fetch or set
 *  methods
 */
data class Person (
            val name: String,
            val age: Int
        )