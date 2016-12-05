/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



/**
 *
 * @author Haadi
 */

public class Log  {

 
   
    private Integer id;
    
    private String content;
    
    private User fromuser;

    public Log() {
    }

    public Log(Integer id) {
        this.id = id;
    }
    public Log(String content){
        this.content=content;
    }

    public Log(Integer id, String content) {
        this.id = id;
        this.content = content;
    }
    
    public Log(String content, User fromuser) {
        this.content = content;
        this.fromuser = fromuser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getFromuser() {
        return fromuser;
    }

    public void setFromuser(User fromuser) {
        this.fromuser = fromuser;
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
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Log[ id=" + id + " ]";
    }
    
}
