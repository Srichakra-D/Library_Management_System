package model;

public class Member extends User {

    public Member(String name, String email, String password){
        super(name, Role.MEMBER, email, password);
    }
    
}
