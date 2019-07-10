# Readme for ICSSTool
This file contains notes and issues for the ICSSTool.
For assignment instructions, see [ASSIGNMENT.md](ASSIGNMENT.md)
This tutorial is tested with java version 12 (OpenJDK) and IntelliJ. It will probably work with java 11 as well but you need to make changes to the pom.xml

## Running ISCSSTool
ICSSTool is a pom.xml based, maven-runnable application.
You can compile the application with the following command:

```mvn compile```

then run it with either

```mvn exec:java``` 
or
```mvn javfx:run```

maven will automatically generate/update the parser from the supplied g4 file.

You can also run the application from an IDE, e.g. IntellIJ. To do so, import ICSSTool as maven project. 
When you make changes to the .g4 file make sure you run `mvn generate-sources` prior to compiling. Most IDE's do not update the antlr parser automatically.

Since java is modular, javafx is not bundled by default. Depending on your IDE you may need to download javafx and add it to your module path. See also: https://openjfx.io/openjfx-docs/

## Known issues
* Packaging works, but running the rar standalone can be troublesome because of the javafx and antlr-runtime dependencies. You can uncomment the maven-shade-plugin in pom.xml to create a (huge) fat jar. It removes module encapsulation which will trigger a warning.
* ICSSTool comes with tests to verify the AST based on sample input files. These are not true unittests but will make your life easier.