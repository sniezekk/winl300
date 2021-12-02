package com.winl300.GraphQLDemo.SpringConfiguration

import com.winl300.GraphQLDemo.MockData.MockDatabaseService
import org.springframework.boot.autoconfigure.ImportAutoConfiguration

/**
 * The purpose of this class is to create top level beans that do not have a dependency on anything else
 *   this initializes them at startup, see the BeanConfiguration class for an idea of what beans are getting created
 *
 *   @uthor: Korey Sniezek
 *   @date: 25Nov2021
 */
@ImportAutoConfiguration(
        value = [
            BeanConfiguration::class
        ]
)
class SpringConfiguration