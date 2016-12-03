# Running server

    // Update src/main/resources/application.properties to have the proper path
    ../gradlew bootRun

# Deploying

    ../gradlew build
    //Move this file or install it on the local machine with
    dpkg -i build/distribution/otspi-1.0.0-1_all.deb