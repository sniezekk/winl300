
# GraphQL Demo For WINL300
## Project Information
### Author: 
Korey Sniezek
### Project Name: 
GraphQL Demo For WINL300
### Project Tech:
Built with IntelliJ, using Maven package manager. 
Uses Kotlin, Java, GraphQL, SpringBoot, and NetflixDGS
### Project Purpose: 
This demo project explores how to use the Netflix DGS framework of GraphQL within the Spring-Boot framework.
### Project Description:
This project will mimic a queryable endpoint for storing, fetching, and mutating data about people. The goal is not to 
replicate a database, but to simulate the interface of a microservice between the database layer and the UI in a 
microservice architecture environment.
### RUN INSTRUCTIONS
This is powered by springboot. Run the main class in "GraphQLDemoApplication.kt", make sure that your IDE has the 
associated maven plugin installed (This was built on intelliJ, so that is ideal. Alternatively, you can go to the 
command line and run mvn spring-boot:run, assuming that you have mvn installed.

## References/Citations
#### Spring-Boot project was initialized with the Spring-Boot initializer at https://start.spring.io/
#### NetflixDGS Initial Setup Instructions: https://netflix.github.io/dgs/getting-started/
#### General Tutorial for GraphQL: https://www.howtographql.com/
#### Error Handling Resource: https://betterprogramming.pub/error-handling-with-graphql-spring-boot-and-kotlin-ed55f9da4221

## Future Development:
Implementing Subscriptions > will require a second service of some sort to post real-time updates
Implement a data loader to get rid of the n+1 problem, is not really needed with this size of project
Integration tests with DgsQueryExecutor/SpringBootTest
switch to code generation plugin for data classes
Access control implementation
Implement Schema level interface