/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Haadi
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
    , @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByConfirmpassword", query = "SELECT u FROM User u WHERE u.confirmpassword = :confirmpassword")})
public class User implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reciever")
    private List<Meddelandet> meddelandetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Meddelandet> meddelandetList1;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "confirmpassword")
    private String confirmpassword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromuser")
    private List<Log> logList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reciever")
    private List<Meddelandet> chatList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Meddelandet> chatList1;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String email, String username, String password, String confirmpassword) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }
    
     public User(String name, String email, String username, String password, String confirmpassword) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    @XmlTransient
    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    @XmlTransient
    public List<Meddelandet> getChatList() {
        return chatList;
    }

    public void setChatList(List<Meddelandet> chatList) {
        this.chatList = chatList;
    }

    @XmlTransient
    public List<Meddelandet> getChatList1() {
        return chatList1;
    }

    public void setChatList1(List<Meddelandet> chatList1) {
        this.chatList1 = chatList1;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.User[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Meddelandet> getMeddelandetList() {
        return meddelandetList;
    }

    public void setMeddelandetList(List<Meddelandet> meddelandetList) {
        this.meddelandetList = meddelandetList;
    }

    @XmlTransient
    public List<Meddelandet> getMeddelandetList1() {
        return meddelandetList1;
    }

    public void setMeddelandetList1(List<Meddelandet> meddelandetList1) {
        this.meddelandetList1 = meddelandetList1;
    }
    
}
