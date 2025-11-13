package ru.stqa.mantis.model;

public record UserData(String username, String password, String realName, String email) {
  public UserData(){
      this("", "", "", "");
  }
  public UserData withUsername(String username){
      return new UserData(username, password, realName, email);
  }
  public UserData withPassword(String password){
      return new UserData(username, password, realName, email);
  }
  public UserData withRealName(String realName){
      return new UserData(username, password, realName, email);
  }
 public UserData withEmail(String email){
      return new UserData(username, password, realName, email);
 }

}
