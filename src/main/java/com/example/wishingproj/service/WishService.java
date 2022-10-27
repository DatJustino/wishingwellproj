package com.example.wishingproj.service;

import com.example.wishingproj.model.Wish;
import com.example.wishingproj.repository.WishRepository;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public class WishService {

    WishRepository wishRepository = new WishRepository();
   // Connection conn = DatabaseConnectionManager.getConnection();

    public List<Wish> getAllWishes() {
      return wishRepository.getAllWishes();
    }
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
  }



