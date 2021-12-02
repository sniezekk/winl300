package com.winl300.GraphQLDemo.validatorTests

import com.winl300.GraphQLDemo.PeopleServices.DeletePersonInput
import com.winl300.GraphQLDemo.PeopleServices.Person
import org.junit.jupiter.api.Assertions
import com.winl300.GraphQLDemo.Validators.DeletePersonInputValidator
import com.winl300.GraphQLDemo.Validators.ErrorCodes.InputErrors
import com.winl300.GraphQLDemo.Validators.ErrorCodes.UserInputException
import org.junit.jupiter.api.Test
import java.util.*

class DeletePersonInputValidatorTest {
    private val knownId = UUID.randomUUID()
    private val knownPerson = Person(
        name = "Known Person",
        age = 42,
        id = knownId
    )
    private val validator = DeletePersonInputValidator() {
        if(it != knownId) {
            null
        } else knownPerson

    }

    @Test
    fun `if person exists in database, when validated, throws no errors`() {
        validator.validateAndThrowIfErrors(
            DeletePersonInput(
                id = knownId.toString()
            )
        )
    }
    @Test
    fun `if person does not exist in database, when validated, throws an error`() {
        val id = UUID.randomUUID().toString()
        val exception = Assertions.assertThrows(UserInputException::class.java) {
            validator.validateAndThrowIfErrors(
                DeletePersonInput(
                    id = id
                )
            )
        }
        assert(exception.message == InputErrors.PERSON_DOES_NOT_EXIST.getErrorMessage() + id)
    }
}