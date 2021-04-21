package business.entities;

public class User {

    private int id; // just used to demo retrieval of autogen keys in UserMapper
    private String email;
    private String password; // Should be hashed and secured
    private String role;
    private String phone_number;
    private String name;
    private int balance; // In cents to prevent rounding errors

    public User(String email, String password, String role, String phone_number, String name) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone_number = phone_number;
        this.name = name;
        this.balance = 0;
    }

    public User(String email, String password, String role, String phone_number, String name, int balance) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone_number = phone_number;
        this.name = name;
        this.balance = balance;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

}
