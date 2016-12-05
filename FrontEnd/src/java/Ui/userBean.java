/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import service.JerseyClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.User;

/**
 *
 * @author Haadi
 */
@ManagedBean
@SessionScoped
public class userBean {

    /**
     * Creates a new instance of userBean
     */
    public static final String AUTHENTICATION = "test";
    private String username;
    private String password;
    private String visitUser;
    private User searchedUser;

   
  
    private String name;//Använder du denn i db?
    private String confirmPassword;
    private String email;
    private boolean loggedIn = false;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getName() {
        return name;
    }

    public void setVisitUser(String visitUser) {
        this.visitUser = visitUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    private boolean checklogin() throws IOException{
         JerseyClient jersey=new JerseyClient("user");
          return jersey.login(username, password);
       
    }
    
    public String isAutenticated() throws IOException{
        if(checklogin()){
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTHENTICATION, username);
            return "profile.xhtml";
        }   
        return "";
    }
      public String getVisitUser() {
        return visitUser;
    }
    public String logout(){
        loggedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().
                handleNavigation(FacesContext.getCurrentInstance(), null,"/login.xhtml");
        return "/login.xhtml";
    }
    
    private void register(){ 
        JerseyClient jersey=new JerseyClient("user");
        jersey.registerUser(name, email, username, password, confirmPassword);  //Detta är väl void
       // UserController.register(new User(name,email,username,password,confirmPassword));
    }
    
    public String registerAutenticated(){
        register();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration success", "success");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "login.xhtml";
    }
    public String visitProfile(String email) throws IOException{
        searchedUser=this.getUserByName(email);
        
        return "visit.xhtml";
    }
    public String getProfileEmail(){
        return searchedUser.getEmail();
    }
   
    public User getUserByName(String username) throws IOException{
        //Först ta in username och omvandla till email??
         JerseyClient jersey=new JerseyClient("user");
        
        return jersey.getUsersByName(username);
    }
    public List<User> getVisitedUser() throws IOException{
         JerseyClient jersey=new JerseyClient("user");
        List<User> l=jersey.searchByName(visitUser);
        List<User> tmp=new ArrayList<User>();
        for(int i=0;i<l.size();i++){
            if(!l.get(i).getUsername().equals(username))
                tmp.add(l.get(i));
            
        }
        return tmp;
    }
    
    public List<User> getUsersByUsername() throws IOException{
        JerseyClient jersey=new JerseyClient("user");
        return jersey.searchByName(visitUser);
    }
     public User getSearchedUser() {
         
        return searchedUser;
    }

    public void setSearchedUser(String name) throws IOException {
        
        searchedUser = this.getUserByName(name);
    }
    
    public static void main(String[] args) throws IOException{
        userBean ub=new userBean();
        ub.username="haadi94";
        ub.visitUser="h";
        List<User> l=ub.getVisitedUser();
        for(int i=0;i<l.size();i++){
            System.out.println(l.get(i).getEmail());
        }
        ub.searchedUser=ub.getUserByName("haadi94");
        //System.out.println(ub.getUserByName("haadi94").getEmail());
    }
    
    
}
