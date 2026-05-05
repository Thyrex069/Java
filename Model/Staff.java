package Model;

public class Staff {

    // ── Fields ───────────────────────────────────────────────
    private String staffId;
    private String name;
    private String role;
    private String shift;
    private String phoneNumber;
    private String startDate;
    private double salary;

    // ── Static counter ───────────────────────────────────────
    private static int staffCount = 0;

    // ── Constructor ──────────────────────────────────────────
    public Staff(String staffId, String name, String startDate) {
        setStaffId(staffId);
        setName(name);
        setStartDate(startDate);
        staffCount++;
    }

    // ── Validation helper ────────────────────────────────────
    private void validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            System.out.println(fieldName + " cannot be null or empty");
        }
    }

    // ── Static method ────────────────────────────────────────
    public static int getStaffCount() {
        return staffCount;
    }

    // ── Getters ──────────────────────────────────────────────
    public String getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getShift() {
        return shift;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public double getSalary() {
        return salary;
    }

    // ── Setters ──────────────────────────────────────────────
    private void setStaffId(String staffId) {
        validateString(staffId, "Staff ID");
        this.staffId = staffId;
    }

    public void setName(String name) {
        validateString(name, "Name");
        this.name = name;
    }

    public void setRole(String role) {
        validateString(role, "Role");
        this.role = role;
    }

    public void setShift(String shift) {
        validateString(shift, "Shift");
        this.shift = shift;
    }

    public void setPhoneNumber(String phoneNumber) {
        validateString(phoneNumber, "Phone Number");
        this.phoneNumber = phoneNumber;
    }

    private void setStartDate(String startDate) {
        validateString(startDate, "Start Date");
        this.startDate = startDate;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            System.out.println("Salary cannot be negative");
        } else {
            this.salary = salary;
        }
    }
}
