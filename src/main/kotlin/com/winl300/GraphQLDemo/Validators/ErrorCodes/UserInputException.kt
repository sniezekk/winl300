package com.winl300.GraphQLDemo.Validators.ErrorCodes

import graphql.ErrorClassification
import graphql.ErrorType

/**
 * This class is a child of the GraphQLException class, it takes in an error message and acts as a throwable exception
 *   that is readable by the user using graphql on the response.
 *
 *   @author Korey Sniezek
 *   @date 1 Dec 2021
 *   @param errorMessage A string representing an error message
 */
open class UserInputException(
    errorMessage: String? = "",
    private val parameters: Map<String, Any>? = mutableMapOf()
) : GraphQLException(errorMessage) {

    //returns the message string
    override val message: String?
        get() = super.message

    // returns the extension parameters of the error
    override fun getExtensions(): MutableMap<String, Any> {
        return mutableMapOf("parameters" to (parameters ?: mutableMapOf()))
    }

    //returns the type of the error
    override fun getErrorType(): ErrorClassification {
        return ErrorType.ValidationError
    }
}