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
        ) {
    companion object {
        /**
         * This companion object creates a new person object from a PersonInput object
         *
         * @author Korey Sniezek
         * @date 25Nov2021
         * @param input, a PersonInput object
         */
        fun fromInput(input: CreatePersonInput): Person {
            return Person(name = input.name, age = input.age)
        }
    }
}
