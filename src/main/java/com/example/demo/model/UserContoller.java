package com.example.demo.model;

import com.example.demo.Metier.Services.UsersService;
import com.example.demo.Metier.entities.Event;
import com.example.demo.Metier.entities.EventType;
import com.example.demo.Metier.entities.Role;
import com.example.demo.Metier.entities.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class UserContoller implements Serializable {
    @EJB
    private UsersService userService ;
    private User loggedUser;

    private User userRegister;
    private  User user;
    private String email;
    private String password;
    private Role role;

    public UserContoller() {
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public User getUserReg() {
        return userRegister;
    }

    public void setUserReg(User userReg) {
        this.userRegister = userReg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @PostConstruct
    public void start(){
        userRegister = new User();
        user = new User();
    }
    public String register()
    {
        userService.registerUser(userRegister);
        this.loggedUser=userRegister;
        HttpSession session=SessionUtils.getSession();
        session.setAttribute("user", userRegister);
        return "home";
    }
    public List<User> getUsers(){
        return userService.getallUser();
    }
    public User getUserById(Long id) {
        System.out.println("  seesion --------- get att"+id);
        return userService.getuser(id);
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public String login()
    {

        User user1 = userService.login(email,password);

        if (user1 !=null )
        {
            this.loggedUser=user1;
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("user", user1);
            System.out.println("---------------------------------------a-aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa-----------------------------------------");
            return "home";
        }
        else
            return "login";
    }
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        this.loggedUser = null;
        return "login";
    }

    public String updateRole(Long id )
    {
        System.out.println("---------------------------------------"+id+"-----------------------------------------");
        userService.updateRole(id);
        return "user";
    }
    public List<Role> getUserRole()
    {
        return Arrays.asList(Role.values());
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean isAdmin(){
        return this.loggedUser != null && this.loggedUser.getRole().equals(Role.ADMIN);
    }
    public boolean isUser(){
        return this.loggedUser != null && this.loggedUser.getRole().equals(Role.USER);
    }
    public boolean isOganisater()
    {
        return this.loggedUser != null && this.loggedUser.getRole().equals(Role.ORGANIZER);
    }


}
