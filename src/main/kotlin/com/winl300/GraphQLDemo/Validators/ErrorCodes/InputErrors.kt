package com.winl300.GraphQLDemo.Validators.ErrorCodes

/**
 * The purpose of this class is to consolidate all the errors that can be created by faulty user input
 *      when creating, modifying, or deleting a person object, values and messages should be self-explanatory
 *
 * @author Korey Sniezek
 * @date 1 Dec 2021
 */
enum class InputErrors : BaseError {
    PERSON_REQUIRES_NAME {
        override fun getErrorMessage() = "A person needs to have a name"
    },
    AGE_INVALID {
        override fun getErrorMessage(): String = "The Person's age must be between 0 and 125"
    },
    PERSON_ALREADY_EXISTS{
        override fun getErrorMessage(): String = "This person cannot be added, as they already exist in database"
    },
    PERSON_DOES_NOT_EXIST{
        override fun getErrorMessage(): String = "The person does not exist. Id: "
    },
    NO_UPDATES_FOUND{
        override fun getErrorMessage(): String = "No changes were attached to this input"
    }
}