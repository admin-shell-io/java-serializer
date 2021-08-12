# Java Dataformat Library

The AAS Java Dataformat Library is a collection of software modules to serialize and
deserialze instances of the Asset Administration Shell from and to Java
instances. De-/serialization works according to the dataformat schemas published in
the document 'Details of the Asset Administration Shell', published on
[www.plattform-i40.de](https://www.plattform-i40.de).


# Build and Use

You can build the project using Maven by simply executing at the repository
root:

`mvn clean install`

or by integrating the respective modules as dependencies, for instance:

```
<dependency>
  <groupId>io.admin-shell.aas</groupId>
  <artifactId>dataformat-json</artifactId>
  <version>latest-version</version>
<dependency>
```
> **_NOTE:_**  The library is yet not available as dependency but will be deployed via Maven Central in the future.

# Project Structure

The project contains several modules:

- `dataformat-parent` Maven parent module that contains the respective de-/serializers for the different data formats.
- `dataformat-core` Location of the general classes and interfaces that are used by more than one de-/serializer.
- `dataformat-aasx` AASX de-/serializer
- `dataformat-json` JSON de-/serializer
- `dataformat-xml` XML de-/serializer
- `dataformat-uanodeset` OPC UA I4AAS NodeSet de-/serializer



# How to Contribute

We always look for contributions, bug reports, feature requests etc. Simply open an [issue](https://github.com/admin-shell-io/java-serializer/issues) or - even better - directly propose a change through a [pull request](https://github.com/admin-shell-io/java-serializer/pulls).


# Contributors

| Name        | Affiliation           | Github Account | parent | core  | aasx | json | xml | json-ld | validator| uanodeset |
--- | --- | --- | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---:
| Mohammad Alreeni | Fraunhofer IWU | []() |  |  |  |  | x |  |  |  |
| Sebastian Bader | Fraunhofer IAIS | [sebbader](https://github.com/sebbader) | x |  |  |  |  | x | x |  |
| Matthias Böckmann | Fraunhofer IAIS | [maboeckmann](https://github.com/maboeckmann) | x |  |  |  |  | x | x |  |
| Maximilian Conradi | Fraunhofer IESE | []() |  |  | x |  | x |  |  |  |
| Helge Dickel | SAP SE | [heldic](https://github.com/heldic) | x |  |  | x | x |  |  |  |
| Daniel Espen | Fraunhofer IESE | [daespen](https://github.com/daespen) |  | x | x | x | x |  |  |  |
| Michael Jacoby | Fraunhofer IOSB| [mjacoby](https://github.com/mjacoby) | x | x |  | x | x |  |  |  |
| Jens Müller | Fraunhofer IOSB | [JensMueller2709](https://github.com/JensMueller2709) |  |  |  | x |  |  |  |  |
| Orthodoxos Kipouridis | SAP SE | [akiskips](https://github.com/akiskips) | x |  |  | x | x |  |  |  |
| Bastian Rössl | Fraunhofer IOSB-INA | [br-iosb](https://github.com/br-iosb) |  |  |  | x |  |  |  | x |
| Frank Schnicke | Fraunhofer IESE | [frankschnicke](https://github.com/frankschnicke) |  |  | x |  | x |  |  |  |
| Manuel Sauer | SAP SE | [Manu3756](https://github.com/Manu3756) | x |  |  |  |  |  |  |  |
| Arno Weiss | Fraunhofer IWU | [alw-iwu](https://github.com/alw-iwu) |  |  |  | x |  |  |  |  |

This project was initiated by SAP and Fraunhofer to provide a foundation for the
AAS development and to foster its dissemination.
