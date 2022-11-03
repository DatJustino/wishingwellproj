package com.example.wishingproj.repository;

import com.example.wishingproj.model.Wish;
import org.springframework.ui.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishRepository {
    //List<Wish> wishes = new ArrayList<>();
    Connection conn = DatabaseConnectionManager.getConnection();

  public boolean checkIfEmailExists (String email) {

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

//   PreparedStatement pst2 =  conn.prepareStatement("select * from wishing_well.wishing_list");
//            ResultSet resultset2 = pst2.executeQuery();

  public List<Wish> getAllWishesFromUser(String email, Model model){
    List<Wish> wishes = new ArrayList<>();

    try {
      PreparedStatement pst =  conn.prepareStatement("select * from wishing_well.wishing_list where email=?"); {
        pst.setString(1, email);
        ResultSet resultset = pst.executeQuery();

        // VI skal have joinet Email til Wishene: det gøres ved at lave Email om til FK
        // og binde dem sammen via en join forbindelse

        while(resultset.next()){
          wishes.add(new Wish(
              resultset.getInt("wish_id"),
              resultset.getString("wish_description"),
              resultset.getString("email")));
        }
        model.addAttribute("wishList", wishes); //----------------her sendes wishes til formen

        ArrayList<Integer> IDs = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();

        for(Wish w : wishes) {
          IDs.add(w.getId());
          model.addAttribute("idList", IDs);

          descriptions.add(w.getDescription());
          model.addAttribute("descriptionList", descriptions);

        }

        //sout terminal
        for (Wish w:wishes) {
          System.out.println(w.toString());
        }
      }
 /*       PreparedStatement pst =  conn.prepareStatement("select * from wishing_well.wishing_list"); {
          ResultSet resultset = pst.executeQuery();
   */
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return wishes;
  }

    public List<Wish> getAllWishes(){
      List<Wish> wishes = new ArrayList<>();

      try {
        PreparedStatement pst =  conn.prepareStatement("select * from wishing_well.wishing_list"); {
          ResultSet resultset = pst.executeQuery();
            // VI skal have joinet Email til Wishene: det gøres ved at lave Email om til FK
          // og binde dem sammen via en join forbindelse
          while(resultset.next()){
            wishes.add(new Wish(
                resultset.getInt("wish_id"),
                resultset.getString("wish_description"),
                resultset.getString("email")));
          }

        }
 /*       PreparedStatement pst =  conn.prepareStatement("select * from wishing_well.wishing_list"); {
          ResultSet resultset = pst.executeQuery();
   */
      }
      catch (SQLException e) {
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
      try {
        PreparedStatement psts = conn.prepareStatement("INSERT INTO wishing_well.wishing_list (wish_description, email) VALUES (?,?)");
        psts.setString(1, wish.getDescription());
        psts.setString(2, wish.getEmail());
        psts.executeUpdate();

      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

  public void deleteWish(String wish_id) {
      int id = Integer.parseInt(wish_id);
    try {
      PreparedStatement psts = conn.prepareStatement("delete from wishing_well.wishing_list where wish_id=?");
      psts.setInt(1, id);
      psts.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  }

