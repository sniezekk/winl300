package com.winl300.GraphQLDemo.Validators

import com.winl300.GraphQLDemo.PeopleServices.PeopleService
import com.winl300.GraphQLDemo.PeopleServices.CreatePersonInput
import com.winl300.GraphQLDemo.Validators.ErrorCodes.InputErrors
import com.winl300.GraphQLDemo.Validators.ErrorCodes.UserInputException
import org.springframework.stereotype.Component

/**
 * This class validates CreatePersonInput objects. If any of the validation fails, it throws an exception with an appropriate
 *    message
 *
 *    @author Korey Sniezek
 *    @date 1 Dec 2021
 */
@Component
class CreatePersonInputValidator(private val peopleService: PeopleService) {

    // top level validation function, calls other validation functions
    fun validateAndThrowIfErrors(input: CreatePersonInput) {
        validateName(input)
        validateAge(input)
        validatePersonDoesNotExistInDatabase(input)
    }

    // person cannot exist in database
    private fun validatePersonDoesNotExistInDatabase(input: CreatePersonInput) {
        if(peopleService.getFiltered(nameFilter = input.name).isNotEmpty()) throw UserInputException(
            errorMessage = InputErrors.PERSON_ALREADY_EXISTS.getErrorMessage()
        )
    }

    // person must have a non-blank name
    private fun validateName(input: CreatePersonInput) {
        if(input.name.isBlank()) throw UserInputException(
            errorMessage = InputErrors.PERSON_REQUIRES_NAME.getErrorMessage()
        )
    }

    // person's age must be in valid range
    private fun validateAge(input: CreatePersonInput) {
        if (input.age < 0 || input.age > 125) throw UserInputException(
                errorMessage = InputErrors.AGE_INVALID.getErrorMessage()
        )
    }
}