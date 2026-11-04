package shopease;
public class Admin extends User {
    public Admin(String n, String e, String p) { super(n, e, p); }
    @Override public String getRole() { return "ADMIN"; }
}
