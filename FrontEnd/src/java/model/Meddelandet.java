/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Haadi
 */

public class Meddelandet implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
   
    private String subject;
   
    
    private String content;
   
    private User reciever;
    
    private User sender;

    private String recieverUsername;
    private String senderUsername;
    public Meddelandet() {
    }

    public Meddelandet(Integer id) {
        this.id = id;
    }

    public Meddelandet(Integer id, String subject, String content) {
        this.id = id;
        this.subject = subject;
        this.content = content;
    }
    
    public Meddelandet(String subject, String content, User sender, User reciever) {
        this.subject = subject;
        this.content = content;
        this.sender = sender;
        this.reciever = reciever;
    }
    
    public Meddelandet(String subject, String content, String senderUsername, String recieverUsername) {
        this.subject = subject;
        this.content = content;
        this.senderUsername = senderUsername;
        this.recieverUsername = recieverUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public User getReciever() {
        return reciever;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meddelandet)) {
            return false;
        }
        Meddelandet other = (Meddelandet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Meddelandet[ id=" + id + " ]";
    }
    
}
