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


@Controller
public class MyPageController {

  WishService wishService = new WishService();
  UserService userService = new UserService();


  @GetMapping("/mypage")
  public String myPage(){
    return "mypage";
  }

  @PostMapping("/mypage")
  public String createUser(WebRequest loginpayload, Model model) {
    model.addAttribute("email", loginpayload.getParameter("email"));
    userService.create(loginpayload);

    return "mypage";
  }

  @PostMapping("/createwish")
  public String createWish(WebRequest wishpayload, Model model) {
    wishService.create(wishpayload);
    model.addAttribute("email", wishpayload.getParameter("email"));

    return "mypage";
  }

}
