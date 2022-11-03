package com.example.wishingproj.controller;

import com.example.wishingproj.repository.ControllerGenerator;
import com.example.wishingproj.repository.HTMLGenerator;
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
  ControllerGenerator controllerGenerator = new ControllerGenerator();
  HTMLGenerator htmlGenerator = new HTMLGenerator();

  @GetMapping("/mypage")
  public String myPage(){
    return "mypage";
  }

  @PostMapping("/mypage")
  public String createUser(WebRequest loginpayload, Model model) {
    model.addAttribute("email", loginpayload.getParameter("email"));
    userService.create(loginpayload);
    controllerGenerator.createController(loginpayload.getParameter("email"));
    htmlGenerator.GenerateFile(loginpayload.getParameter("email"));

    return "mypage";
  }

  @PostMapping("/login")
  public String getWishes(WebRequest userpayload, Model model) {
    //if userexists get wishes
    // else if user doesnt exist, return to indexpage with error message user doesnt exist.

    model.addAttribute("email", userpayload.getParameter("email"));

    boolean hasEmail;
    boolean password;
    hasEmail = wishService.getAllWishesFromUser(userpayload, model);
    //  password = userService.create();
    if (hasEmail){
      return "mypage";
    } else return "index";
    //første email er variabelnavn og andet email er email fra html formen
    //den her model skal være der, da det er den, der sætter email ind i hidden form
    //til brug i wishing_lidt. Uden den kommer email ikke ind i wishing_list
  }

  @PostMapping("/createwish")
  public String createWish(WebRequest wishpayload, Model model) {
    wishService.create(wishpayload);
    model.addAttribute("email", wishpayload.getParameter("email"));
    wishService.getAllWishesFromUser(wishpayload, model);

    return "mypage";
  }
  @PostMapping("/deletewish")
  public String deleteWish(WebRequest deleteWishPayload, Model model){
    model.addAttribute("email", deleteWishPayload.getParameter("email"));
    wishService.deleteWish(deleteWishPayload, model);
    wishService.getAllWishesFromUser(deleteWishPayload, model);
    return "mypage";
  }

}
