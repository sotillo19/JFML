# JFML: An open source Java Library for the IEEE Standard for Fuzzy Markup Language (IEEE Std 1855-2016)

JFML is an open source Java library which is aimed at facilitating interoperability and usability of fuzzy systems. 
Notice that JFML implements the new IEEE Std 1855 published and sponsored by the Standards Committee of the IEEE Computational Intelligence Society.

JFML has a web page associated with a complete documentation, a good variety of examples for both users and developers, etc., The web page is hosted at:
https://www.jfml.es.

## JFML-Core

From v1.3, JFML —now renamed as JFML-Core— is compiled with Java 11 and uses Maven to do so, whilst previous versions use Ant and Java 8. This module has two main purposes:
- It contains the definition of the main classes, such as FuzzyVariable.
- It applies an already built Fuzzy Logic System to a data set to predict the data output value.

### How to use

To build, you can clone the .git repository or download the library into a .zip file from the `Clone or download` option. You can also download the source code from the `releases` in GitHub.

We recommend following the next steps to use it if no modification is needed:
- Download the library into a .zip file from the _Clone or Download_ option or from the <a href="https://github.com/sotillo19/JFML/releases">releases</a>
- Unzip it in a local folder.
- At the root of the project, go (`cd`) to _Examples_.
- Run the following command:
```
java -jar ./lib/<jar-file> <problem> <fls> ./XMLFiles/<test-data-file>
```
This command is thoroughly explained in _Examples/README-EXAMPLES.txt_.

In case of changing the source files:
- Make the necessary changes.
- Do `mvn clean install`.
- A new artifact, as a JAR file, will be generated at the _target_ folder.
- Copy and paste that JAR to _Examples/lib_.
- At the root of the project, go (`cd`) to _Examples_.
- Run the main command.
