package com.example.wishingproj.repository;

import com.example.wishingproj.model.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class HTMLGenerator {

  //getparameteremail.trim.remove@.remove.com
 public void GenerateFile(String getLogin) {
   //getlogin == filename-- placeholder
   if (getLogin.toLowerCase(Locale.ROOT).length() > 1 & getLogin.toLowerCase(Locale.ROOT).length() < 30) {

     String email = getLogin.substring(0, getLogin.indexOf("@"))
         .replace(".","")
         .replace("_","")
         .replace("-","")
         .trim();


     String indexpage = "<!DOCTYPE html>\n" +
         "<html>\n" +
         "<head>\n" +
         "\n" +
         "<link rel=\"stylesheet\" href=\"index.css\">\n" +
         "<meta name=\"description\" content=\"Wishing-list website\">\n" +
         "<meta name=\"keywords\" content=\"wishing, share wishes, friends and family\">\n" +
         "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
         "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
         "<meta name=\"author\"content=\"I. Justino, Veronica Bistrup Frydkjær\">\n" +
         "<meta http-equiv=\"refresh\" content=\"1200\">\n" +
         "</head>\n" +
         "  \n" +
         "<body>\n" +
         "<nav><ul class=\"morepink\">\n" +
         "  <li><a href=\"/\"title=\"refresh the page?\">Main Menu</a></li>\n" +
         "  <li><a href=\"PLACEHOLDER\"title=\"PLACEHOLDER\">Om Wishing Well</a></li>\n" +
         "  <li><a href=\"PLACEHOLDER\"title=\"PLACEHOLDER\">PlaceHolder</a></li>\n" +
         "</ul></nav>\n" +
         "  \n" +
         "  \n" +
         "<div class=\"midter-div pink\">\n" +
         " <div id=\"smal-midterdiv\" class=\"pink\">\n" +
         "    <br><h1>Save and share your wishing list</h1><br>\n" +
         "  <div id=\"login-div\">\n" +
         "    <br>\n" +

         "<br>\n" + "<div class=\"midter-div pink\">  \n" +
         "<div id=\"smal-midterdiv\" class=\"pink\">\n" +

         "<h2> My wishes </h2>\n" +
         "\n" +
         "<table>\n" +
         "    <tr th:each=\"description : ${descriptionList}\">\n" +
         "        <td  th:text=\"${description}\"> </td>\n" +
         "        <td  th:each=\"id : ${idList}\">  </td>\n" +
         "        <td  th:text=\"${id}\">\n" +
         "        <td th:each=\"id : ${idList}\"> <form action=\"/deletewish\" method=\"post\">\n" +
         "                <input type=\"hidden\" name=\"email\" th:value=\"${email}\">\n" +
         "                <input type=\"hidden\" name=\"wish_id\" th:value=\"${id}\">\n" +
         "            <button type=\"submit\"> Delete </button>\n" +
         "            </form>\n" +
         "    </td></td>\n" +
         "    </tr>\n" +
         "</table>\n" +
         "\n" +
         "\n" +
         "\n" +
         "\n" +
         "</div>\n" +
         "</div>\n" +
         "\n" +
         "\n" +
         "<form action=\"/\" method=\"get\">\n" +
         "    <button type=\"submit\"> HOME</button>\n" +
         "</form>\n" +
         "<br>\n" +
         "<form action=\"/"+email+"\" method=\"post\">\n" +
         " <input type=\"hidden\" name=\"email\" value=\""+getLogin+"\">\n" +
         " <button type=\"submit\"> getwishes! </button>\n" +
         "</form>\n" +
         "\n" +
         "\n" +
         "</body>\n" +
         "</html>";

     String path = "C:\\Users\\super\\IdeaProjects\\wishingproj\\src\\main\\resources\\templates\\" + email + ".html";
     try {
       File f = new File(path);
       if (!f.exists() && !f.isDirectory()) {
         FileWriter fw = new FileWriter(path);
         BufferedWriter bw = new BufferedWriter(fw);
         bw.write(indexpage);
         bw.close();
       }

     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 }

  public User getUser(String email){
    Connection conn = DatabaseConnectionManager.getConnection();

    try { //join wishing_well.wishing_list on users.email = wishing_list.email
      PreparedStatement pst =  conn.prepareStatement("select email from wishing_well.users where users.email=?"); {
        pst.setString(1, email);
        ResultSet resultset = pst.executeQuery();

        if (resultset.next()) {
          User user = new User(
              resultset.getString("email"),
              resultset.getString("name"));
        }
      }
          /* if (resultset.next()){
            users.add(new User(
                resultset.getInt("id"),
                resultset.getString("email"),
                resultset.getString("name")));

        }*/


        /*
        for users = 0; i< users.size;i++ {
         users fra listen = users.email
         users.get(i)
         return user;
         */

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }


  public static void main(String[] args) {
    HTMLGenerator htmlGenerator = new HTMLGenerator();
    htmlGenerator.GenerateFile("ian");
  }

}
