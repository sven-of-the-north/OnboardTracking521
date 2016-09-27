# Onboard Tracking System (OTS) for SENG 521

## Getting Started

### Requirements
- Java 8 jdk (must have the jar command)

### IDE Support
From the root project run either

   ./gradlew eclipse

   ./gradlew idea

depending on which ide you would like to use then import the generated
project files into the respective IDE

## Building and packaging

### Whole Source Tree
To build the whole source tree from the root folder run

   ./gradlew build

This will run unit tests and generate jar files in

   <subproject>/build/libs

To create all packages from the root folder run

   ./gradlew package

This will create .deb files in

   <subproject>/build/distributions


## Working in Individual Projects
Run the same commands as above except in the subproject folder instead of

   ./gradlew

run

   ../gradlew

## Committing
Before committing please ensure
- You have built and tested the changed packages
- You have bumped a version according to semantic version
-- Example 1.2.3
--- 1 is the MAJOR version. It should be changed if there has been an API breaking change
--- 2 is the MINOR version. It should be changed when a new feature has been added in that does not break the existing API
--- 3 is the BUG-FIX version. It should be changed when making minor changes or bugfixes