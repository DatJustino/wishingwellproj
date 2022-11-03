package com.example.wishingproj.controller;

import com.example.wishingproj.repository.UserRepository;
import com.example.wishingproj.repository.WishRepository;
import com.example.wishingproj.service.UserService;
import com.example.wishingproj.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;

@Controller
public class ggg{

  WishService wishService = new WishService();
  UserService userService = new UserService();
    @GetMapping("/ggg")
        public String index() {
      return "ggg";
    }

  @PostMapping("/ggg")
  public String getWishes(WebRequest userpayload, Model model) {
      model.addAttribute("email", userpayload.getParameter("email"));
    wishService.getAllWishesFromUser(userpayload, model);
  return "ggg";
  }
}