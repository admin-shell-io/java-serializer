# Java Dataformat Serializers

The AAS Java Serializers are collection of software modules to serialize and
deserialze instances of the Asset Administration Shell into and from Java
instances. The serializers work according to the dataformat schemas published in
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

# Project Structure

The project contains several modules:

- dataformat-parent (this folder): Maven parent module that contains the
respective serializers for the different data formats.
- dataformat-core: Location of the general classes and interfaces that are used
by more than one serializer.
- dataformat-json: JSON serializer and deserializer
- dataformat-xml: XML serializer and deserializer



# How to Contribute

We always look for contributions, bug reports, feature requests etc. Simply open
an [issue](https://github.com/admin-shell-io/java-serializer/issues) or - even
better - directly propose a change through a [pull request](https://github.com/admin-shell-io/java-serializer/pulls).


# Contributers

| Name | Affiliation | Github Account |
|:--| -- | -- |
| Sebastian Bader | Fraunhoder IAIS | [sebbader](https://github.com/sebbader) |
| Matthias Böckmann | Fraunhoder IAIS | [maboeckmann](https://github.com/maboeckmann) |
| Chang Qin | Fraunhoder IAIS | [changqin26](https://github.com/changqin26) |
| Michael Jacoby | Fraunhoder IOSB | []() |
| Maximilian Conradi | Fraunhoder IESE | []() |
| Jan-Wilhelm Blume | Fraunhoder IOSB | []() |
| Frank Schnicke | Fraunhoder IESE | []() |
| Jens Müller | Fraunhoder IOSB | []() |
| Daniel Espen | Fraunhoder IESE | []() |
| Bastian Espen | Fraunhoder IOSB | []() |
| Michael Jacoby | Fraunhoder IOSB | []() |
