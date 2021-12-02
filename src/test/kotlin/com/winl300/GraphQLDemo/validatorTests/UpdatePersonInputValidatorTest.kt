package com.winl300.GraphQLDemo.validatorTests

import com.winl300.GraphQLDemo.PeopleServices.Person
import com.winl300.GraphQLDemo.PeopleServices.UpdatePersonInput
import com.winl300.GraphQLDemo.Validators.ErrorCodes.InputErrors
import com.winl300.GraphQLDemo.Validators.ErrorCodes.UserInputException
import com.winl300.GraphQLDemo.Validators.UpdatePersonInputValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class UpdatePersonInputValidatorTest {
    private val MINIMUM_AGE = 0
    private val MAXIMUM_AGE = 125

    private val knownId = UUID.randomUUID()
    private val knownPerson = Person(id = knownId, name = "Known Person", age = 5)
    private val validator = UpdatePersonInputValidator() {
        if(it != knownId) null
        else knownPerson
    }

    @Test
    fun `given a person who is found in the database, and changes exist, throws no errors`() {
        validator.validateAndThrowIfErrors(
            UpdatePersonInput(
                id = knownId.toString(),
                newName = "Test Change",
                newAge = 73
            )
        )
    }

    @Test
    fun `given a person who is not found in the database, throws an error`() {
        val exception = Assertions.assertThrows(UserInputException::class.java) {
            validator.validateAndThrowIfErrors(
                UpdatePersonInput(id = UUID.randomUUID().toString())
            )
        }
        Assertions.assertEquals(exception.message, InputErrors.PERSON_DOES_NOT_EXIST.getErrorMessage())
    }

    @Test
    fun `given a person who is in the database, if there are no changes, throws an error`() {
        val exception = Assertions.assertThrows(UserInputException::class.java) {
            validator.validateAndThrowIfErrors(
                UpdatePersonInput(id = knownId.toString())
            )
        }
        Assertions.assertEquals(exception.message, InputErrors.NO_UPDATES_FOUND.getErrorMessage())
    }

    @Test
    fun `given a person who is in the database, but has an age below the minimum value, throws an error`() {
        val exception = Assertions.assertThrows(UserInputException::class.java) {
            validator.validateAndThrowIfErrors(
                UpdatePersonInput(id = knownId.toString(), newAge = MINIMUM_AGE - 1)
            )
        }
        Assertions.assertEquals(exception.message, InputErrors.AGE_INVALID.getErrorMessage())
    }

    @Test
    fun `given a person who is in the database, but has an age above the maximum value, throws an error`() {
        val exception = Assertions.assertThrows(UserInputException::class.java) {
            validator.validateAndThrowIfErrors(
                UpdatePersonInput(id = knownId.toString(), newAge = MAXIMUM_AGE + 1)
            )
        }
        Assertions.assertEquals(exception.message, InputErrors.AGE_INVALID.getErrorMessage())
    }

    @Test
    fun `given a person who is in the database, but has an empty string name, throws an error`() {
        val exception = Assertions.assertThrows(UserInputException::class.java) {
            validator.validateAndThrowIfErrors(
                UpdatePersonInput(id = knownId.toString(), newName = "")
            )
        }
        Assertions.assertEquals(exception.message, InputErrors.PERSON_REQUIRES_NAME.getErrorMessage())
    }

    @Test
    fun `given a person who is in the database, but has a whitespace name, throws an error`() {
        val exception = Assertions.assertThrows(UserInputException::class.java) {
            validator.validateAndThrowIfErrors(
                UpdatePersonInput(id = knownId.toString(), newName = "    ")
            )
        }
        Assertions.assertEquals(exception.message, InputErrors.PERSON_REQUIRES_NAME.getErrorMessage())
    }
}