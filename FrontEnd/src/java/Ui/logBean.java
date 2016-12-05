/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;


import service.JerseyClient;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import model.Log;
import model.User;

/**
 *
 * @author Haadi
 */
@ManagedBean
@SessionScoped
public class logBean {

    /**
     * Creates a new instance of logBean
     */
    
    private String content;
    private User from;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }
    
    
     public void insertLog(String user){
        JerseyClient jersey=new JerseyClient("log");
        jersey.addPost(content, user);
        
    }
    
    public List<Log> getLogs(String user) throws IOException{
         JerseyClient jersey=new JerseyClient("log");
        return jersey.getLogs(user);
    }
    
    public static void main(String[] args) throws IOException{
        logBean log=new logBean();
        log.content="Hej alla";
        //log.insertLog("haadi94");
        List<Log> l=log.getLogs("haadi94");
        for(int i=0;i<l.size();i++)
            System.out.println(l.get(i).getContent());
    }
    
}
