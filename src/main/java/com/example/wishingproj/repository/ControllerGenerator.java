package com.example.wishingproj.repository;

import org.springframework.ui.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class ControllerGenerator {

  public void createController(String realMail) {

    if (realMail.toLowerCase(Locale.ROOT).length() > 0 & realMail.toLowerCase(Locale.ROOT).length() < 30) {

      String email = realMail.substring(0, realMail.indexOf("@"))
          .replace(".","")
          .replace("_","")
          .replace("-","")
          .trim();

      String str =
          "package com.example.wishingproj.controller;\n" +
              "\n" +
              "import com.example.wishingproj.repository.UserRepository;\n" +
              "import com.example.wishingproj.repository.WishRepository;\n" +
              "import com.example.wishingproj.service.UserService;\n" +
              "import com.example.wishingproj.service.WishService;\n" +
              "import org.springframework.stereotype.Controller;\n" +
              "import org.springframework.ui.Model;\n" +
              "import org.springframework.web.bind.annotation.GetMapping;\n" +
              "import org.springframework.web.bind.annotation.PostMapping;\n" +
              "import org.springframework.web.context.request.WebRequest;\n" +
              "import java.util.ArrayList;\n" +
              "\n" +
              "@Controller\n" +
              "public class "+email+"{\n" +
              "\n" +
              "  WishService wishService = new WishService();\n" +
              "  UserService userService = new UserService();\n" +
              "    @GetMapping(\"/"+email+"\")\n" +
              "        public String index() {\n" +
              "      return \""+email+"\";\n" +
              "    }\n" +
              "\n" +
              "  @PostMapping(\"/"+email+"\")\n" +
              "  public String getWishes(WebRequest userpayload, Model model) {\n" +
              "      model.addAttribute(\"email\", userpayload.getParameter(\"email\"));\n" +
              "    wishService.getAllWishesFromUser(userpayload, model);\n" +
              "  return \""+email+"\";\n" +
              "  }\n" +
              "}";


      String path = "C:\\Users\\super\\IdeaProjects\\wishingproj\\src\\main\\java\\com\\example\\wishingproj\\controller\\" + email + ".java";
      try {
        File f = new File(path);
        if (!f.exists()) {
          FileWriter fw = new FileWriter(path);
          BufferedWriter bw = new BufferedWriter(fw);
          bw.write(str);
          bw.close();
        }

      } catch (
          IOException e) {
        e.printStackTrace();
      }
    }
  }
}
