package com.winl300.GraphQLDemo.validatorTests

import com.winl300.GraphQLDemo.PeopleServices.CreatePersonInput
import com.winl300.GraphQLDemo.PeopleServices.PeopleService
import com.winl300.GraphQLDemo.PeopleServices.Person
import com.winl300.GraphQLDemo.Validators.ErrorCodes.InputErrors
import com.winl300.GraphQLDemo.Validators.ErrorCodes.UserInputException
import com.winl300.GraphQLDemo.Validators.CreatePersonInputValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

/**
 * Tests for the CreatePersonInputValidator, test function names are self documenting
 *
 * @author Korey Sniezek
 * @date 1 Dec 2021
 */
class CreatePersonInputValidatorTest {

    /**
     * The following mocks the behavior of a database that finds the person exists if their name matches the 'existingPerson'
     * variable, and otherwise does not find them, this allows us to test without having a dependency on any other service.
     * This is done through typealiases, please see the CreatePersonInputValidator to see the typealias source
     */
    private val existingPerson = "Existing Person"
    private val validator: CreatePersonInputValidator = CreatePersonInputValidator() {
        if(it == existingPerson) listOf(Person(name = existingPerson, age = 10))
        else emptyList()
    }

    private val MINIMUM_AGE = 0
    private val MAXIMUM_AGE = 125

    private val person = CreatePersonInput(name = "Test Name", age = 1)
    @Test
    fun `given a minimum age, when validated, returns positive`(){
            validator.validateAndThrowIfErrors(
                person.copy(age = MINIMUM_AGE)
            )
    }

    @Test
    fun `given a maximum age, when validated, returns positive`(){
            validator.validateAndThrowIfErrors(
                person.copy(age = MAXIMUM_AGE)
            )
    }

    @Test
    fun `given a minimum age minus 1, when validated, returns an error`() {
        val exception = Assertions.assertThrows(UserInputException::class.java) {
            validator.validateAndThrowIfErrors(
                person.copy(age = MINIMUM_AGE - 1)
            )
        }
        Assertions.assertEquals(exception.message, InputErrors.AGE_INVALID.getErrorMessage())
    }

    @Test
    fun `given a maximum age plus 1, when validated, returns an error`(){
        val exception = Assertions.assertThrows(UserInputException::class.java) {
            validator.validateAndThrowIfErrors(
                person.copy(age = MAXIMUM_AGE + 1)
            )
        }
        Assertions.assertEquals(exception.message, InputErrors.AGE_INVALID.getErrorMessage())
    }

    @Test
    fun `given a name that is an empty string, when validated, returns an error`(){
        val exception = Assertions.assertThrows(UserInputException::class.java) {
            validator.validateAndThrowIfErrors(
                person.copy(name = "")
            )
        }
        Assertions.assertEquals(exception.message, InputErrors.PERSON_REQUIRES_NAME.getErrorMessage())
    }

    @Test
    fun `given a name that exists in the database, when validated, returns an error`(){
        val exception = Assertions.assertThrows(UserInputException::class.java) {
            validator.validateAndThrowIfErrors(
                person.copy(name = existingPerson)
            )
        }
        Assertions.assertEquals(exception.message, InputErrors.PERSON_ALREADY_EXISTS.getErrorMessage())
    }
}