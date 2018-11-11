package com.borsch.shift.cft.features.account.domain.model;


public final class Account  {

    //private String id;
    private String login;
    private String firstName;
    private String secondName;
    private String city;
    private String university;
    private String dormitory;
    private String room;
    private Double eatRate;
    private Double cookRate;
    private String vkontakte;
    private String telegram;
    private String email;
  ;

    public Account( String firstName, String secondName, String city, String university, String dormitory,
                   String room, String vkontakte, String telegram, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.city = city;
        this.university = university;
        this.dormitory = dormitory;
        this.room = room;
        this.vkontakte = vkontakte;
        this.telegram = telegram;
        this.email = email;
    }

  //  public String getId() {
  //      return id;
  //  }


    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getCity() {
        return city;
    }

    public String getUniversity() {
        return university;
    }

    public String getDormitory() {
        return dormitory;
    }

    public String getRoom() {
        return room;
    }

    public String getEmail() {
        return email;
    }

    public String getVkontakte() {
        return vkontakte;
    }

    public String getTelegram() {
        return telegram;
    }

    public Double getCookRate() {
        return cookRate;
    }

    public Double getEatRate() {
        return eatRate;
    }
}
