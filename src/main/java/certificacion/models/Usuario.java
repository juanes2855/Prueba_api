package certificacion.models;

public class Usuario {

    private int user_id;
    private String email;
    private String username;
    private String password;

    public Usuario(int user_id, String email, String username, String password) {
        this.user_id = user_id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

