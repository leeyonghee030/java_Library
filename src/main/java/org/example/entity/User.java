package org.example.entity;

public class User {
    int userId;  //고유번호
    String name; // 유저 이름
    String userName; //유저 아이디
    String userPassWord; //유저 비번

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public User(String name, String userName, String userPassWord){
        this.name = name;
        this.userName = userName;
        this.userPassWord = userPassWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassWord='" + userPassWord + '\'' +
                '}';
    }
}


