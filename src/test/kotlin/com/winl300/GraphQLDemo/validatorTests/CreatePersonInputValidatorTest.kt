package com.winl300.GraphQLDemo.validatorTests

import com.winl300.GraphQLDemo.PeopleServices.CreatePersonInput
import com.winl300.GraphQLDemo.Validators.ErrorCodes.InputErrors
import com.winl300.GraphQLDemo.Validators.ErrorCodes.UserInputException
import com.winl300.GraphQLDemo.Validators.CreatePersonInputValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CreatePersonInputValidatorTest(private val validator: CreatePersonInputValidator) {

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
}