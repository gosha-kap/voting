package ru.gosha_kap.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@Entity
@Table(name = "users")
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)

@SqlResultSetMapping(name="VoteResult", classes = {
        @ConstructorResult(targetClass = Vote.class,
                columns = {@ColumnResult(name="user_id"), @ColumnResult(name="restaurant_id"),
                        @ColumnResult(name="date")})
})
public class User implements hasID {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="login")
    @NotBlank
    @Size(min = 2, max = 100)
    private String login;

    @Column(name="password")
    @NotBlank
    @Size(min = 5, max = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pass;

    @Column(name="name")
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Column(name="surName")
    @NotBlank
    @Size(min = 2, max = 100)
    private String  surName;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;


    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date registered = new Date();


    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="role")
    private Set<Role> roles = new HashSet<>();

    public User(@NotBlank @Size(min = 2, max = 100) String login, @NotBlank @Size(min = 5, max = 100) String pass, @NotNull Date registered, Set<Role> roles) {
        this.login = login;
        this.pass = pass;
        this.registered = registered;
        this.roles = roles;
    }

    public User(@NotBlank @Size(min = 2, max = 100) String login, @NotBlank @Size(min = 5, max = 100) String pass, @NotBlank @Size(min = 2, max = 100) String name, @NotBlank @Size(min = 2, max = 100) String surName) {
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.surName = surName;

    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {

    }

    public void addRole(Role role){
        getRoles().add(role);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                '}';
    }
}
