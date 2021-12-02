package com.winl300.GraphQLDemo.Validators.ErrorCodes

/**
 * The purpose of this class is to act as a common abstract class for future error codes, if the shape of the
 *    error interface changes, the change will cascade through all of the associated classes
 *
 * @author Korey Sniezek
 * @date 29Nov2021
 */
interface BaseError {
    fun getErrorMessage(): String
}