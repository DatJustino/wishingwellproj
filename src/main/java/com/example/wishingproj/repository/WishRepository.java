package com.example.wishingproj.repository;

import com.example.wishingproj.model.User;
import com.example.wishingproj.model.Wish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishRepository {
    List<Wish> wishes = new ArrayList<>();
    Connection conn = DatabaseConnectionManager.getConnection();


    public List<Wish> getAllWishes(){

      try {
        PreparedStatement pst =  conn.prepareStatement("select * from wishing_well.wishing_list"); {
          ResultSet resultset = pst.executeQuery();
          while(resultset.next()){
            wishes.add(new Wish(
                resultset.getString("wish_description"),
                resultset.getString("email")));
          }

        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return wishes;
    }
/*
    public Wish getWish(String email){

      try {
        PreparedStatement pst =  conn.prepareStatement("select * from wishing_well where email=?"); {
          pst.setString(1, email);
          ResultSet resultset = pst.executeQuery();

          if (resultset.next()){
            wishes.add(new Wish(
                resultset.getInt("id"),
                resultset.getString("name"),
                resultset.getString("year")));
          }
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return null;
    }*/

    public void create(Wish wish) {
UserRepository userlist = new UserRepository();
      try {
        String email;

        PreparedStatement psts = conn.prepareStatement("INSERT INTO wishing_well.wishing_list (wish_description, email) VALUES (?,?)");
        psts.setString(1, wish.getDescription());
        psts.setString(2, wish.getEmail());
        psts.executeUpdate();

      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

    }

  }

