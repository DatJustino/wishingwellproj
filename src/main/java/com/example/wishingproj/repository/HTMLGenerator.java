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

public class HTMLGenerator {

  //getparameteremail.trim.remove@.remove.com
 public void GenerateFile(String getLogin) {
   //getlogin == filename-- placeholder


   String indexpage = "<!DOCTYPE html>\n" +
       "<html>\n" +
       "<head>\n" +
       "\n" +
       "<link rel=\"stylesheet\" href=\"index.css\">\n"+
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
       
       "</form>\n" +
       "\n" +
       "<h2> My wishes </h2>\n" +
       "<table>\n" +
       "  <th>Table header</th>\n" +
       "    <tr>\n" +
       "      <td>\n" +
       "        <div class=\"wish-cell\">ghvjb<br><br> <form action=\"/\" method=\"get\">\n" +
       "        <button type=\"submit\">Delete</button>\n" +
       "        </form></div>\n" +
       "        </td>\n" +
       "    </tr>\n" +
       "    <tr>\n" +
       "        <td>ghvjb</td>\n" +
       "    </tr>\n" +
       "</table>\n" +
       "  \n" +
       "<table>\n" +
       "    <tr th:each=\"wish : ${wishList}\">\n" +
       "        <td th:text=\"${wish}\"></td>\n" +
       "    </tr>\n" +
       "</table>\n" +
       "\n" +
       "\n" +
       "<form action=\"/\" method=\"get\">\n" +
       "    <button type=\"submit\"> HOME</button>\n" +
       "</form>\n" +
       "  \n" +
       "  \n" +
       "  <ul>\n" +
       "    <li><div class=\"wish-li-div\">The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! Fox nymphs grab quick-jived waltz. Brick quiz whangs jumpy veldt<br>\n" +
       "      </div>\n" +
       "      <form action=\"/\" method=\"get\">\n" +
       "     <button type=\"submit\">Delete </button>\n" +
       "      </form><br><br>\n" +
       "      </li>\n" +
       "    <li><div class=\"wish-li-div\">ønske xdgfchvjbkvjhbknjbll rgfeawgrsfeadw efadwASGRDSEAEF GRFEDASGFAWEF GRFAGEAFS GFFGSFE<br>\n" +
       "      </div>\n" +
       "      <form action=\"/\" method=\"get\">\n" +
       "     <button type=\"submit\">Delete </button>\n" +
       "      </form><br><br>\n" +
       "      </li>\n" +
       "    <li><div class=\"wish-li-div\"><h2> My wishes </h2>\n" +
       "\n" +
       "<p th:text=\"${name}\"></p>\n" +
       "\n" +
       "<table>\n" +
       "    <tr th:each=\"wish : ${wishList}\">\n" +
       "        <td th:text=\"${wish}\"></td>\n" +
       "    </tr>\n" +
       "</table>\n" +
       "\n" +
       "\n" +
       "<form action=\"/\" method=\"get\">\n" +
       "    <button type=\"submit\"> HOME</button><br>\n" +
       "      </div>\n" +
       "      <form action=\"/\" method=\"get\">\n" +
       "     <button type=\"submit\">Delete </button>\n" +
       "      </form><br><br>\n" +
       "      </li>\n" +
       "  </ul>\n" +
       "  \n" +
       "  <p><br><br><br><br><br><br><br><p>\n" +
       "  </div>\n" +
       "</div>" +
       "  <footer class=\"morepink\">\n" +
       "\n" +
       "    <p class=\"footer-text\">\n" +
       "             Wishing Well DK<br>\n" +
       "        Overgaden Oven Vandet 44, 1415 København<br>\n" +
       "        wishinwell@justino.dk <br>\n" +
       "      \t33123456\n" +
       "    </p>\n" +
       "</footer>\n" +
       "</body>";

   String path = "C:\\Users\\super\\IdeaProjects\\wishingproj\\src\\main\\resources\\templates\\userprofiles\\" + getLogin + ".html";
   try {
     File f = new File(path);
     if (!f.exists() && !f.isDirectory()) {
       FileWriter fw = new FileWriter(path);
       BufferedWriter bw = new BufferedWriter(fw);
       bw.write(indexpage);
       bw.close();
     }

     } catch(IOException e){
       e.printStackTrace();
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
