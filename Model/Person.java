package Model;

import Interface.Displayable;

public class Person implements Displayable {

    // ── Fields ───────────────────────────────────────────────
    // protected so Customer and Staff can access them directly
    protected String id;
    protected String name;
    protected String phone;

    // ── Constructor ──────────────────────────────────────────
    public Person(String id, String name, String phone) {
        this.id    = cleanText(id,    "NO_ID");
        this.name  = cleanText(name,  "Unknown Name");
        this.phone = cleanText(phone, "No Phone");
    }

    // ── Validation helper ────────────────────────────────────
    // Returns defaultValue if the string is null or blank
    protected String cleanText(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return value.trim();
    }

    // ── Getters ──────────────────────────────────────────────
    public String getId()    { return id; }
    public String getName() { return name; }
    public String getPhone( ) { return phone; }

    // ── Setters ──────────────────────────────────────────────
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            this.phone = phone.trim();
        }
    }

    // ── Displayable ──────────────────────────────────────────
    @Override
    public void displayInfo() {
        System.out.println("ID    : " + id);
        System.out.println("Name  : " + name);
        System.out.println("Phone : " + phone);
    }
}
