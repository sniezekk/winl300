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
    val people = listOf(
            Person(name = "Joe Dirt", age = 42)
    )
}