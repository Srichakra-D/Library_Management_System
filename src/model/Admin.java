package model;

public class Admin extends User {
    
    public Admin(String name, String email, String password){
        super(name, Role.ADMIN, email, password);
    }
}
