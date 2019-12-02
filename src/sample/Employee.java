package sample;

public class Employee {

    private String name;
    private String username;
    private String password;
    private String email;

    Employee(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setUsername(String myUsername) {

    }

    public void setEmail(String myEmail) {

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
