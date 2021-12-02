package com.winl300.GraphQLDemo.SpringConfiguration

import com.winl300.GraphQLDemo.DataFetcher
import com.winl300.GraphQLDemo.MockData.MockDatabaseService
import com.winl300.GraphQLDemo.PeopleServices.PeopleService
import com.winl300.GraphQLDemo.Validators.CreatePersonInputValidator
import com.winl300.GraphQLDemo.Validators.DeletePersonInputValidator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * The purpose of this class is to provide a central point for the initializing of all beans for this demo project.
 *    In larger projects, this would be separated into various configuration files that would then be initialized by the
 *    top-level spring configuration file, but that would be overkill for a small demo project of this size.
 *    So, for readability's sake, I have placed them all in one file.
 *
 *    @uthor: Korey Sniezek
 *    @date: 25Nov2021
 *
 *    NOTE: All bean names should be self explanatory, see the corresponding files for the documentation on each
 */
@Configuration
class BeanConfiguration {

    @Bean
    fun mockDatabaseService(): MockDatabaseService = MockDatabaseService()

    @Bean
    fun peopleService(): PeopleService = PeopleService(mockDatabaseService())

    @Bean
    fun dataFetcher(): DataFetcher = DataFetcher(
        peopleService(),
        createPersonInputValidator(),
        deletePersonInputValidator()
    )

    @Bean
    fun createPersonInputValidator(): CreatePersonInputValidator = CreatePersonInputValidator() {
        peopleService().getFiltered(nameFilter = it)
    }


    @Bean
    fun deletePersonInputValidator(): DeletePersonInputValidator = DeletePersonInputValidator() {
        peopleService().getPersonById(it)
    }
}