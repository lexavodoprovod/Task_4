package com.hololeenko.task_4.model.entity;

import java.util.Objects;

public class User extends AbstractEntity {

    private String name;
    private String login;
    private String password;
    private UserRole role = UserRole.USER;


    private User(UserBuilder userBuilder) {
        this.name = userBuilder.name;
        this.login = userBuilder.login;
        this.password = userBuilder.password;
    }

    public static class UserBuilder{
        private String name;
        private String login;
        private String password;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public User build(){
            if(name == null || login == null || password == null){
                throw new IllegalStateException("Missing required parameters for Database \"User\"");
            }
            return new User(this);
        }


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setAdminRole() {
        this.role = UserRole.ADMIN;
    }

    public void serUserRole() {
        this.role = UserRole.USER;
    }

    @Override
    public boolean equals(Object o) {
       if(this == o){
           return true;
       }

       if(o == null){
           return false;
       }

       if(getClass() != o.getClass()){
           return false;
       }

       User user = (User) o;

       return name.equals(user.name) && login.equals(user.login) && password.equals(user.password);
    }

    @Override
    public int hashCode() {

        int total = 31;

        total = total * 31 + (name != null ? name.hashCode() : 0 );

        total = total * 31 + (login != null ? login.hashCode() : 0);

        total = total * 31 + (password != null ? password.hashCode() : 0);

        return total;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
