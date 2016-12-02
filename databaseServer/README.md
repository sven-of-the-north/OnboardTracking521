# Running server

  ../gradlew build
  java -jar build/libs/otsserver-1.0.0.jar "directory with software emulator"

# Deploying

  ../gradlew build
  // Move this file or install it on the local machine with
  dpkg -i build/distribution/otsserver-1.0.0-1_all.deb