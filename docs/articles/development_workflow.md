# Development Workflow

We develop with Github using pull requests (see this [Github guide](https://guides.github.com/introduction/flow/) for a short introduction).

**Development branch.** The development branch is always `master`. Expect changes on this branch from time to time.

**Releases.** The releases mark the development milestones on the `master` branch with a certain feature completeness.

## Pull Requests

**Feature branches.** We develop using the feature branches, see this [section of the Git book](https://git-scm.com/book/en/v2/Git-Branching-Branching-Workflows). We use `feature/'feature-name'` and `bugfix/'bugfix-name'` as a naming convention.

If you are a member of the development team, create a feature branch directly within the repository.

Otherwise, if you are a non-member contributor, fork the repository and create the feature branch in your forked repository. See this Github tuturial for more guidance.

**Branch Prefix.** Each PullRequest must contained a list of the changed topics, for instance as a list of bulletpoints. Simply refering to the commit messages is not sufficient.

**Reviews.** Each PullRequest is reviewed by the Maintainers of the project. In order to simplify the workflow, please assign the PullRequest directly to the Maintainer you think is most knowledgable about your changes.

## Commit Messages

The commit messages should follow the guidelines from https://chris.beams.io/posts/git-commit:

- Separate subject from body with a blank line
- Limit the subject line to 50 characters
- Capitalize the subject line
- Do not end the subject line with a period
- Use the imperative mood in the subject line
- Wrap the body at 72 characters
- Use the body to explain what and why (instead of how)
