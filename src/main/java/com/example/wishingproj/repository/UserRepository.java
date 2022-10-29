package com.example.wishingproj.repository;

import com.example.wishingproj.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    List<User> users = new ArrayList<>();
    Connection conn = DatabaseConnectionManager.getConnection();


  public boolean emailVerification(String email) {
    try {
      PreparedStatement psts = conn.prepareStatement("SELECT * from wishing_well.users where email=?");
      psts.setString(1, email);
      ResultSet resultset = psts.executeQuery();
      if(resultset.next()){
        return true;
      } else return false;

    } catch (SQLException e) {
      System.out.println("duplicate entry");
      throw new RuntimeException(e);
    }
  }


  public boolean passwordVerification(String email, String password) {

    try {
      PreparedStatement psts = conn.prepareStatement("SELECT * from wishing_well.users where email=? and password=?");
      psts.setString(1, email);
      psts.setString(2, password);
      ResultSet resultset = psts.executeQuery();
      if(resultset.next()){
        return true;
      } else return false;

    } catch (SQLException e) {
      System.out.println("wrong password!");
      throw new RuntimeException(e);
    }
  }


  /* public List<User> getAllUsers(){
      try {
        PreparedStatement pst =  conn.prepareStatement("select * from wishing_well.users"); {
          ResultSet resultset = pst.executeQuery();
          while(resultset.next()){
            users.add(new User(
                resultset.getInt("id"),
                resultset.getString("email"),
                resultset.getString("name")));
          }

        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return users;
    }
*/
    public User getUser(String email){
      try { //join wishing_well.wishing_list on users.email = wishing_list.email
        PreparedStatement pst =  conn.prepareStatement("select * from wishing_well.users where users.email=?"); {
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
    public void create(User user) {

      try {
          PreparedStatement psts = conn.prepareStatement("INSERT INTO wishing_well.users (email, name, password) VALUES (?,?,?)");
          psts.setString(1, user.getEmail());
          psts.setString(2, user.getPassword());
          psts.setString(3, user.getName());
          psts.executeUpdate();

      } catch (SQLException e) {
        System.out.println("duplicate entry");
        throw new RuntimeException(e);
      }
    }
  }

