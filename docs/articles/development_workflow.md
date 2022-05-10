# Development Workflow

We develop with Github using pull requests (see this [Github guide](https://guides.github.com/introduction/flow/) for a short introduction).
Members can open branches in the project itself, non-member contributions (welcome!) shall be developed in a fork.
Community PRs should be directed at the `development` branch and must be reviewed by at least one member. 
Each PullRequest must contain a list of the changed topics, for instance as a list of bullet points. Simply referring to the commit messages is not sufficient.


## `development` branch

The development branch is always `development`. Expect changes on this branch from time to time. Every PR shall include an
updated project version in pom.xml. Since commits to `development` will usually be bugfixes, in the x.y.z semantic versioning
schema, z shall be incremented. Also, a tag shall indicate the incremented version. A CI job will provide the built artifacts
to github packages.

## `main` branch
The releases mark the development milestones on the `main` branch with a certain feature completeness. That's why the version
on the main branch shall always represent at least a minor release holding version x.y.0. Like on the `development` branch,
the person creating the PR is responsible for incrementing the version in the pom and tag it. A CI job will release the codebase
to the [maven central repository](https://mvnrepository.com/artifact/io.admin-shell.aas).

## metamodel version
Note, that the versioning scheme of this project is not directly aligned with the release process of the meta-model defined in
the document [Details of the Asset Administration Shell Pt. 1](https://github.com/admin-shell-io/aas-specs)! The community strives to keep the repository up to date with 
the latest release of the meta-model.