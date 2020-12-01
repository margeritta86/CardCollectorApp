package com.pokemon.app.request;

import java.util.Objects;

public class UserRequest {

    private String email;
    private String password;
    private String name;

    public UserRequest(String email,String name, String password) {
        this.email = validEmail(email);
        this.password = validPassword(password);
        this.name = validName(name);
    }

    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private String validEmail(String email) {
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Zły format maila !");
        }
        return email;
    }

    private String validPassword(String password) {
        if (password.length() < 5) {
            throw new IllegalArgumentException("Hasło musi posiadać co najmniej 5 znaków !");
        }
        return password;
    }

    private String validName(String name) {
        if (name.length() < 5) {
            throw new IllegalArgumentException("Nazwa musi zawierać co najmniej 5 znaków !");
        }
        return name;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
