# flashcards
  flashcards application

This is a desktop application for flashcard learning written in Java version 11 and using the MySql database. The application allows you to create your own sets of flashcards using the interface or a CSV file. To use the application, you need JRE and MySql.

  Getting started:

1. Run the following command in your cmd:

git clone https://github.com/Zdzimi/flashcards.git

2. Go to the file:

flashcards\flashcards-data\src\main\resources\sql\shema.sql

Create a shema using this code in your MySql database.

3. Go to the file:

flashcards\flashcards-data\src\main\resources\db.properties.template

Replace:

mysql://host:port/database by your database url

alien by your username

1234 by your password

Finally save file as db.properties

4. Back to your cmd and run commands:

cd flashcards

mvn install

cd flashcards-desktop/target

java -jar flashcards-desktop-1.0-SNAPSHOT-jar-with-dependencies.jar
