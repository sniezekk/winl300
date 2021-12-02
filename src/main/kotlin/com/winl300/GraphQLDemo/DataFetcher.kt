package com.winl300.GraphQLDemo

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.winl300.GraphQLDemo.PeopleServices.PeopleService
import com.winl300.GraphQLDemo.PeopleServices.Person
import com.winl300.GraphQLDemo.PeopleServices.CreatePersonInput
import com.winl300.GraphQLDemo.Validators.CreatePersonInputValidator

/**
 * The purpose of this class is to interface with the GraphQL/Netflix DGS schema and pass the input arguments
 *  to their associated functions
 *
 *  @author Korey Sniezek
 *  @date 25Nov2021
 *  @param peopleService, Dependent on the people service to function.
 */
@DgsComponent
class DataFetcher (
            private val peopleService: PeopleService,
            private val createPersonInputValidator: CreatePersonInputValidator
        ) {

    /**
     * The purpose of this query is to a filtered list of all the people objects from the peopleService
     *
     * @author Korey Sniezek
     * @date 25Nov2021
     * @param nameFilter, a string to match exactly with existing people objects
     * @return List<Person> of People objects
     */
    @DgsQuery
    fun people(@InputArgument nameFilter : String?, @InputArgument ageFilter: Int?): List<Person> {
        return peopleService.getFiltered(nameFilter, ageFilter)
    }

    /**
     * The addPerson Mutation is responsible for calling validation on user input and adding the person to the database
     *   if validation passes
     *
     *   @author Korey Sniezek
     *   @date 1 Dec 2021
     *   @param input CreateUserInput object
     */
    @DgsMutation
    fun addPerson(@InputArgument input: CreatePersonInput): Person? {
        createPersonInputValidator.validateAndThrowIfErrors(input)
        return peopleService.addPerson(input)
        }
    }

