package com.example.wishingproj.service;

import com.example.wishingproj.model.User;
import com.example.wishingproj.model.Wish;
import com.example.wishingproj.repository.WishRepository;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public class WishService {
    WishRepository wishRepository = new WishRepository();
   // Connection conn = DatabaseConnectionManager.getConnection();

  /*
    public List<Wish> getAllWishes() {
      return wishRepository.getAllWishes();
    }*/
/*

    public Wish getWish(String email){
      return wishRepository.getWish(email);
    }
*/

    public void create(WebRequest wishpayload) {
      Wish wish = new Wish(
          wishpayload.getParameter("description"),
          wishpayload.getParameter("email")
      );
      wishRepository.create(wish);
    }

  public boolean getAllWishesFromUser(WebRequest loginpayload, Model model){;

    boolean hasEmail;
    hasEmail = wishRepository.checkIfEmailExists(loginpayload.getParameter("email"));
    if (hasEmail) {
      wishRepository.getAllWishesFromUser(loginpayload.getParameter("email"), model);
      return true;
    } else {
      System.out.println("denne bruger findes ikke i systemet, lav en ny");
      return false;
    }
  }
  public void deleteWish(WebRequest deleteWishPayload, Model model){
      String wish_id = deleteWishPayload.getParameter("wish_id");
    System.out.println(wish_id);
      model.addAttribute(wish_id);
      wishRepository.deleteWish(wish_id);
  }

  }



