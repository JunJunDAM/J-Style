package com.bisai.bisai.model;


/**
 * Created by sergi on 27/03/2017.
 */

public class User {


    private static final long serialVersionUID = 1L;

    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private boolean activated = false;
    private String langKey;
    private String resetKey;




    public User(Long id, String resetKey, String langKey, boolean activated, String email, String lastName, String login, String firstName) {
        this.id = id;
        this.resetKey = resetKey;
        this.langKey = langKey;
        this.activated = activated;
        this.email = email;
        this.lastName = lastName;
        this.login = login;
        this.firstName = firstName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (activated != user.activated) return false;
        if (!id.equals(user.id)) return false;
        if (!login.equals(user.login)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!email.equals(user.email)) return false;
        if (!langKey.equals(user.langKey)) return false;
        return resetKey.equals(user.resetKey);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (activated ? 1 : 0);
        result = 31 * result + langKey.hashCode();
        result = 31 * result + resetKey.hashCode();
        return result;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
