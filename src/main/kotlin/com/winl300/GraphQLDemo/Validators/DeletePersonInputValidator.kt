package com.winl300.GraphQLDemo.Validators

import com.winl300.GraphQLDemo.PeopleServices.DeletePersonInput
import com.winl300.GraphQLDemo.PeopleServices.Person
import com.winl300.GraphQLDemo.Validators.ErrorCodes.InputErrors
import com.winl300.GraphQLDemo.Validators.ErrorCodes.UserInputException
import org.springframework.stereotype.Component
import java.util.*

typealias GetPersonByIdProvider = (UUID) -> Person?
/**
 * This class validates the delete person input and throws appropriate errors if invalid
 *
 * @author Korey Sniezek
 * @date 1 Dec 2021
 */
@Component
class DeletePersonInputValidator (
    private val getPersonById: GetPersonByIdProvider
        ) {

    // top level validation
    fun validateAndThrowIfErrors(input: DeletePersonInput) {
        validatePersonExists(input)
    }

    // finds the person by id, if the person does not exist, throws error
    private fun validatePersonExists(input: DeletePersonInput) {
        val id = UUID.fromString(input.id)
        if(getPersonById(id) == null) throw UserInputException(
            InputErrors.PERSON_DOES_NOT_EXIST.getErrorMessage() + id
        )
    }
}