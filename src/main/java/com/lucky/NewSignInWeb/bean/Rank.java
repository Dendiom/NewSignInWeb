package com.lucky.NewSignInWeb.bean;

public class Rank {

    private String nickname;
    private String grade;
    private Long count;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "nickname='" + nickname + '\'' +
                ", grade='" + grade + '\'' +
                ", count=" + count +
                '}';
    }
}
