package shopease;
public abstract class User {
    private static int counter = 0;
    private final int id;
    private final String name, email;
    private final String password;
    protected User(String name, String email, String password) {
        this.id = ++counter; this.name = name; this.email = email; this.password = password;
    }
    public boolean checkPassword(String p) { return password.equals(p); }
    public abstract String getRole();
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
