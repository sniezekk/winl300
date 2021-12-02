package com.winl300.GraphQLDemo

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.winl300.GraphQLDemo.PeopleServices.*
import com.winl300.GraphQLDemo.Validators.CreatePersonInputValidator
import com.winl300.GraphQLDemo.Validators.DeletePersonInputValidator
import com.winl300.GraphQLDemo.Validators.UpdatePersonInputValidator
import java.util.*

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
            private val createPersonInputValidator: CreatePersonInputValidator,
            private val deletePersonInputValidator: DeletePersonInputValidator,
            private val updatePersonInputValidator: UpdatePersonInputValidator
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
    fun people(@InputArgument nameFilter: String?, @InputArgument ageFilter: Int?): List<Person> {
        return peopleService.getFiltered(nameFilter, ageFilter)
    }

    /**
     * This query gets a person object by id, returns null if id is not found
     *
     * @author Korey Sniezek
     * @date 1 Dec 2021
     * @param id, a string UUID
     * @return Person object, or null
     */
    @DgsQuery
    fun getPersonById(@InputArgument id: String): Person? {
        return peopleService.getPersonById(UUID.fromString(id))
    }

    /**
     * The addPerson Mutation is responsible for calling validation on user input and adding the person to the database
     *   if validation passes
     *
     *   @author Korey Sniezek
     *   @date 1 Dec 2021
     *   @param input CreateUserInput object
     *   @return a Person object or null
     */
    @DgsMutation
    fun addPerson(@InputArgument input: CreatePersonInput): Person? {
        createPersonInputValidator.validateAndThrowIfErrors(input)
        return peopleService.addPerson(input)
    }


    /**
     * This function is responsible for taking user input, validating that the person exists, deleting the person from
     *   the database, and returning the deleted person
     *
     *   @author Korey Sniezek
     *   @date 1 Dec 2021
     *   @param input DeletePersonInput
     *   @return a Person object, or null
     */
    @DgsMutation
    fun deletePerson(@InputArgument input: DeletePersonInput): Person? {
        deletePersonInputValidator.validateAndThrowIfErrors(input)
        return peopleService.deletePersonById(UUID.fromString(input.id))
    }

    /**
     * This function calls the validator for the updatePersonInputObject, then updates the person object through the
     *   people service
     *
     *   @author Korey Sniezek
     *   @date 1 Dec 2021
     *   @param input, Update person input
     *   @return a Person object or null
     */
    @DgsMutation
    fun updatePerson(@InputArgument input: UpdatePersonInput): Person? {
        updatePersonInputValidator.validateAndThrowIfErrors(input)
        return peopleService.updatePerson(input)
    }

}

