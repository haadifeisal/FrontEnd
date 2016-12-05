/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;

import model.Log;
import model.Meddelandet;
import model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Haadi
 */
public class JerseyClient {
    
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8082/BackEnd/webresources"; //Detta är server 2

    public JerseyClient(String path) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path(path);
        /*
         du anger path i konstruktorn oxå för att bestämma vilken service
        du vill använda dig utav tex user eller log osv*/
        
    }
     public boolean login(String email, String password) throws IOException {
        String str = performGet(BASE_URI + "/user/login/" + email + "/" + password );

        return str.equals("true");

    }
      public void registerUser(String name, String email, String username, String password, String confirmpassword) throws ClientErrorException {
        Form form = new Form();
        form.param("name", name);
        form.param("email", email);
        form.param("username", username);
        form.param("password", password);
        form.param("confirmpassword", confirmpassword);
        webTarget.path("register").request().post(Entity.form(form));
    }
        public void addPost(String content, String from) {
        Form form = new Form();
        form.param("Content", content);
        form.param("From", from);
        webTarget.path("addpost").request().post(Entity.form(form));
    }

    public List<Log> getLogs(String from) throws IOException {
        List<Log> pList = new ArrayList<Log>();

        String sOutput = performGet(BASE_URI + "/log/getlogs/" + from);

        JSONArray jArray = null;

        try {
            jArray = new JSONArray(sOutput);//vi ändrar till json array eftersom det finns flera objekt
            //i strängen.
        } catch (JSONException ex) {
            Logger.getLogger(JerseyClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < jArray.length(); i++) {

            JSONObject jObject;

            try {
                jObject = jArray.getJSONObject(i);//vi hämtar från jsonarrayen alla jsonobjekt,

                pList.add(new Log(jObject.getString("content")  //och lägger in dem i pList.add()
                      ));
            } catch (JSONException ex) {
                Logger.getLogger(JerseyClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return pList;
    }
     private static String performGet(String customUrl) throws IOException { //denna metod returnerar url
         //till en sträng, alltså allt som finns i urlen blir till en sträng.
         //som man kan senare omvandla till jsonObject. 
        String jString = "";
        try {
            URL url = new URL(customUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output; // den läser alltin

            while ((output = br.readLine()) != null) {
                jString += output;
            }

            conn.disconnect();

        } catch (MalformedURLException ex) {
            Logger.getLogger(JerseyClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JerseyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jString; //soutput="{"confirmpassword":"hej",
        //"email":"haadi@hotmail.com","id":1,"name":"haadi feisal","password":"hej","username":"haadi94"}"
    }
     public void sendMessageTo(String Subject, String Content, String from, String to) {
        Form form = new Form();
        form.param("Subject", Subject);
        form.param("Content", Content);
        form.param("Sender", from);
        form.param("Receiver", to);

        webTarget.path("addmessage").request().post(Entity.form(form));

    }

    public List<Meddelandet> getMessages(String from) throws IOException {
        List<Meddelandet> mList = new ArrayList<Meddelandet>();

        String sOutput = performGet(BASE_URI + "/chat/getmessages/" + from);
       
        JSONArray jArray = null;

        try {
            jArray = new JSONArray(sOutput);  //Här omvandlar vi den till en json objkt för att kunna senare separera datat som finns i strängen via jobject.getString("email)
        } catch (JSONException ex) {
            Logger.getLogger(JerseyClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < jArray.length(); i++) {

            JSONObject jObject;
            try {
                jObject = jArray.getJSONObject(i);

                mList.add(new Meddelandet( //den plockar ut dessa värden ur json arrayen
                        //och hämtar beroende på tex email o sånt
                        jObject.getString("subject"),
                        jObject.getString("content"),
                        new User(jObject.getJSONObject("sender").getString("name")
                        ,jObject.getJSONObject("sender").getString("email"),
                        jObject.getJSONObject("sender").getString("username"),
                                jObject.getJSONObject("sender").getString("password"),
                        jObject.getJSONObject("sender").getString("confirmpassword")),
                         
                        new User(jObject.getJSONObject("reciever").getString("name")
                        ,jObject.getJSONObject("reciever").getString("email"),
                        jObject.getJSONObject("reciever").getString("username"),
                                jObject.getJSONObject("reciever").getString("password"),
                        jObject.getJSONObject("reciever").getString("confirmpassword"))));
            } catch (JSONException ex) {
                Logger.getLogger(JerseyClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return mList;
    }
     public User getUsersByName(String Email) throws IOException {

        String sOutput = performGet(BASE_URI + "/user/name/" + Email);

        sOutput = "[" + sOutput + "]";

        JSONArray jArray = null;

        try {
            jArray = new JSONArray(sOutput);
        } catch (JSONException ex) {
            Logger.getLogger(JerseyClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        User user = null;

        for (int i = 0; i < jArray.length(); i++) {

            try {
                JSONObject jObject = jArray.getJSONObject(i);

                user = new User(jObject.getString("name"),
                        jObject.getString("email"),
                        jObject.getString("username"),
                        jObject.getString("password"),
                jObject.getString("confirmpassword"));

            } catch (JSONException ex) {
                Logger.getLogger(JerseyClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return user;
    }
         public List<User> searchByName(String username) throws IOException {

        List<User> uList = new ArrayList<User>();
        String sOutput = performGet(BASE_URI + "/user/search/" + username);
       
        
        JSONArray jArray = null;
        try {
            jArray = new JSONArray(sOutput);
        } catch (JSONException ex) {
            Logger.getLogger(JerseyClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < jArray.length(); i++) {
            try {
                JSONObject jObject = jArray.getJSONObject(i);

                uList.add(new User(jObject.getString("name"),
                        jObject.getString("email"),
                        jObject.getString("username"),
                        jObject.getString("password"),
                         jObject.getString("confirmpassword")));

            } catch (JSONException ex) {
                Logger.getLogger(JerseyClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return uList;
    }
     
     public static void main(String[] args) throws IOException{
         JerseyClient jClient=new JerseyClient("chat");
         List<Meddelandet> l=jClient.getMessages("haadi94");
         for(int i=0;i<l.size();i++)
             System.out.println(l.get(i).getContent());
         
         //System.out.println(jClient.getUsersByName("haadi94").getEmail());
         //System.out.println(jClient.login("haadi94", "hej"));
     }
    
}
