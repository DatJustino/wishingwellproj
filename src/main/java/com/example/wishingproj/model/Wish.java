package com.example.wishingproj.model;

public class Wish {
  private int id;
  private String description;
  private String email;


    public Wish(String description, String email) {
      this.description = description;
      this.email = email;
    }

    public Wish(int id, String description, String email) {
      this.id = id;
      this.description = description;
      this.email = email;
  }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }
    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Wish " + id + " description: " + description +
        ", email: " + email;
  }
}

