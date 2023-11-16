6# Details for P6 Huffman Coding

## Starter Code and Using Git
**_You should have installed all software (Java, Git, VS Code) before completing this project._** You can find the [directions for installation here](https://coursework.cs.duke.edu/201fall23/resources-201/-/blob/main/installingSoftware.md) (including workarounds for submitting without Git if needed).

We'll be using Git and the installation of GitLab at [coursework.cs.duke.edu](https://coursework.cs.duke.edu). All code for classwork will be kept here. Git is software used for version control, and GitLab is an online repository to store code in the cloud using Git.

**[This document details the workflow](https://coursework.cs.duke.edu/201fall23/resources-201/-/blob/main/projectWorkflow.md) for downloading the starter code for the project, updating your code on coursework using Git, and ultimately submitting to Gradescope for autograding.** We recommend that you read and follow the directions carefully this first time working on a project! While coding, we recommend that you periodically (perhaps when completing a method or small section) push your changes as explained in below.

## Details on Git with a Partner

You may find it helpful to begin by reading the Working Together section of the [Git tutorial](https://gitlab.oit.duke.edu/academic-technology/cct/-/tree/master/git) from the Duke Colab. For more, see the [Git tutoraial by Gitlab](https://docs.gitlab.com/ee/tutorials/make_your_first_git_commit.html) including the link to an [extensive video tutorial](https://www.youtube.com/watch?v=4lxvVj7wlZw) if you prefer that.

One person should fork the starter code and then add their partner as a collaborator on the project. Choose Settings>Members>Invite Members. Then use the autocomplete feature to invite your partner to the project as a *maintainer*. Both of you can now clone and push to this project. See the [gitlab documentation here](https://docs.gitlab.com/ee/user/project/members/).

Now you should be ready to clone the code to your local machines.

1. Both students should clone the same repository and import it into VS Code just like previous projects.  
2. After both students have cloned and imported, one person should make a change (you could just write a comment in the code, for example). Commit and push this change. 
3. The other partner will then issue a git pull request. Simply use the command-line (in the same project directory where you cloned the starter code for the project) and type:
```bash
git pull
```
4. If the other partner now opens the project in VS Code again, they should see the modified code with the edit created by the first partner. 
5. You can continue this workflow: Whenever one person finishes work on the project, they commit and push. Whenever anyone starts work on the project, they begin by downloading the current version from the shared online repository using a git pull command.

This process works as long as only one person is editing at a time, and **you always pull before editing** and remember to **commit/push when finished**. If you forget to pull before editing your local code, you might end up working from an old version of the code different than what is in the shared online gitlab repository. If that happens, you may experience an error when you attempt to push your code back to the shared online repository. 

There are many ways to resolve these conflicts, essentially you just need to pick which of the different versions of the code you want to go with. See the [working together Git tutorial](https://gitlab.oit.duke.edu/academic-technology/cct/-/blob/master/git/working_together.md) and the [branching and merging Git tutorial](https://gitlab.oit.duke.edu/academic-technology/cct/-/blob/master/git/branching_merging.md) from the Duke Colab for more information. You can also refer to our [Git troubleshooting document](https://coursework.cs.duke.edu/201-public-documentation/resources-201/-/blob/main/troubleshooting.md#git-faq). 
