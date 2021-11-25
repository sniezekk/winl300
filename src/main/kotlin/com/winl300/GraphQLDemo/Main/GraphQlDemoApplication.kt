package com.winl300.GraphQLDemo.Main

import com.winl300.GraphQLDemo.SpringConfiguration.SpringConfiguration
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Main function runs on startup, starts a spring application and Imports the configuration from the SpringConfiguration
 * 	class
 *
 * 	@author Korey Sniezek
 * 	@date 25Nov2021
 * 	@version 1.0
 * 	@param args provided by spring or the CLI, and are out of scope of the project
 */
@SpringBootApplication
@ImportAutoConfiguration(SpringConfiguration::class)
class GraphQlDemoApplication

fun main(args: Array<String>) {
	runApplication<GraphQlDemoApplication>(*args)
}
