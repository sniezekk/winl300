package com.winl300.GraphQLDemo.Validators

import com.winl300.GraphQLDemo.PeopleServices.UpdatePersonInput
import com.winl300.GraphQLDemo.Validators.ErrorCodes.InputErrors
import com.winl300.GraphQLDemo.Validators.ErrorCodes.UserInputException
import org.springframework.stereotype.Component
import java.util.*

/**
 * This class validates the UpdatePersonInput
 *
 * @author Korey Sniezek
 * @param getPersonById getPersonIdProvider, as defined by a type alias in another validator
 * @date 1 Dec 2021
 */
@Component
class UpdatePersonInputValidator(private val getPersonById: GetPersonByIdProvider) {

    // top level validator, calls other validation functions
    fun validateAndThrowIfErrors(input: UpdatePersonInput) {
        validatePersonExists(input)
        validateChangesExist(input)
        validateNameInput(input)
        validateAgeInput(input)
    }

    // throws error if person does not exist in database
    private fun validatePersonExists(input: UpdatePersonInput) {
        if(getPersonById(UUID.fromString(input.id)) == null) throw UserInputException(
            InputErrors.PERSON_DOES_NOT_EXIST.getErrorMessage()
        )
    }

    // throws error if no age and name are passed in
    private fun validateChangesExist(input: UpdatePersonInput) {
        if(input.newAge == null && input.newName == null) throw UserInputException(
            InputErrors.NO_UPDATES_FOUND.getErrorMessage()
        )
    }

    // throws an error if the name is blank/whitespace
    private fun validateNameInput(input: UpdatePersonInput) {
        if (input.newName != null && input.newName.isBlank()) throw UserInputException(
            InputErrors.PERSON_REQUIRES_NAME.getErrorMessage()
        )
    }

    // throws an error if age is outside of age range
    private fun validateAgeInput(input: UpdatePersonInput) {
        if (input.newAge != null && (input.newAge < 0 || input.newAge > 125)) throw UserInputException(
            InputErrors.AGE_INVALID.getErrorMessage()
        )
    }
}