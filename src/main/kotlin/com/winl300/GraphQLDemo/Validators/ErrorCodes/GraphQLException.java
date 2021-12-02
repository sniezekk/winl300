package com.winl300.GraphQLDemo.Validators.ErrorCodes;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

/**
 * This class is written in Java rather than Kotlin because Kotlin lacks the ability to both extend something
 *  and implement something else at the same time, which is required for this error handling pattern.
 *
 * @author Korey Sniezek
 * @date 25Nov2021
 *
 * Note: See RuntimeException and GraphQLError classes for the documentation associated with those classes, no modifications
 *    have been done other then combining them through extends/implements
 */
public class GraphQLException extends RuntimeException implements GraphQLError {

    String customMessage;

    public GraphQLException(String customMessage) {
        this.customMessage = customMessage;
    }

    @Override
    public String getMessage()  {
        return customMessage;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }
}
