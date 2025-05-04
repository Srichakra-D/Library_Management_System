package model;

public abstract class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private static int generate_id = 0;

    public User(String name, Role role, String email, String password){
        generate_id++;
        this.id = generate_id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public int getId(){ return id;}
    public String getName(){ return name;}
    public Role getRole(){ return role;}
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }

    public void setPassword(String password){ this.password = password; }

    public boolean checkPassword(String pass){
        return password.equals(pass);
    }


    @Override
    public String toString(){
        return String.format("Id: %d | Name: %s | Role:  %s", id, name, role);
    }
}