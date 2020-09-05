package com.elias.gadspraticeproject.model;

public class Submit {
    private String FirstName;
    private String LastName;
    private String EmailAddress;
    private String GithubLink;

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getGithubLink() {
        return GithubLink;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public void setGithubLink(String githubLink) {
        GithubLink = githubLink;
    }
}
