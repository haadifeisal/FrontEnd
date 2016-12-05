/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;


import service.JerseyClient;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Meddelandet;
import model.User;

/**
 *
 * @author Haadi
 */
@ManagedBean
@SessionScoped
public class chatBean {

    /**
     * Creates a new instance of chatBean
     */
    
    private String subject;
    private String content;
    private String sender;
    private String reciever;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }
    
    public List<Meddelandet> getMessages(String username) throws IOException{
        JerseyClient jersey=new JerseyClient("chat");
        
        return jersey.getMessages(username);
    }
    
    public void sendMessage(String fromUser){
        JerseyClient jersey=new JerseyClient("chat");
        jersey.sendMessageTo(subject, content, fromUser, reciever);
        //ChatController.insertMessage(subject,content,fromUser,reciever);
        /*
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Reciever username does not exists", "fail");  
            FacesContext.getCurrentInstance().addMessage(null, msg);*/
    }
    
    public static void main(String[] args) throws IOException{
        chatBean chat=new chatBean();
        chat.setSubject("Djur");
        chat.setContent("Du är häst");
        chat.setReciever("haadi94");
        chat.sendMessage("haadi94");
       /*List<Meddelandet> l=chat.getMessages("haadi94");
       for(int i=0;i<l.size();i++)
           System.out.println(l.get(i).getContent());*/
    }
    
}
