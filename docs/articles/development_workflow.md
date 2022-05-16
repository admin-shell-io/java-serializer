# Development Workflow

We develop with Github using pull requests (PRs, see this [Github guide](https://guides.github.com/introduction/flow/) for a short introduction).
Members can open branches in the project itself, non-member contributions (welcome!) shall be developed in a fork.
Community PRs should be directed at the `development` branch and must be reviewed by at least one member. 
Each PR must contain a list of the changed topics, for instance as a list of bullet points. Simply referring to the commit messages is not sufficient.

## `development` branch

The development branch is always `development`. Expect changes on this branch from time to time. The contributor is responsible
to adjust the version in the pom.xml file. Generally we use the semantic versioning schema of x.y.z (x -> major, y -> minor, z -> bugfix).
The version on `development` shall be dependent on the version of `main`: x<sub>main</sub>.y<sub>main</sub>.1+z<sub>main</sub>-SNAPSHOT.
An exception is the commit preparing the release via PR to `main`. A CI job will provide the built artifacts to github packages as snapshot-releases.

## `main` branch
The releases mark the development milestones on the `main` branch with a certain feature completeness. The `main` branch
shall receive updates exclusively via PRs from the development branch. Like on the `development` branch,
the person creating the PR is responsible to update the version in the pom and tag it. The version should be the current version
of the `developement` branch, stripped of "-SNAPSHOT". 

A CI job will release the codebase to the [maven central repository](https://mvnrepository.com/artifact/io.admin-shell.aas).

## metamodel version
Note, that the versioning scheme of this project is not directly aligned with the release process of the meta-model defined in
the document [Details of the Asset Administration Shell Pt. 1](https://github.com/admin-shell-io/aas-specs)! The community strives to keep the repository up to date with 
the latest release of the meta-model.