package Model;

// Staff IS-A Person → correct inheritance
public class Staff extends Person {

    // ── Fields ───────────────────────────────────────────────
    private String role;
    private String shift;
    private String startDate;
    private double salary;

    // ── Static counter ───────────────────────────────────────
    private static int staffCount = 0;

    // ── Constructor ──────────────────────────────────────────
    public Staff(String staffId, String name, String phone,
                 String role, String shift, String startDate, double salary) {
        super(staffId, name, phone); // id, name, phone handled by Person
        setRole(role);
        setShift(shift);
        setStartDate(startDate);
        setSalary(salary);
        staffCount++;
    }

    // ── Static method ────────────────────────────────────────
    public static int getStaffCount() { return staffCount; }

    // ── Getters ──────────────────────────────────────────────
    public String getStaffId()   { return id; } // inherited from Person
    public String getRole()      { return role; }
    public String getShift()     { return shift; }
    public String getStartDate() { return startDate; }
    public double getSalary()    { return salary; }

    // ── Setters ──────────────────────────────────────────────
    public void setRole(String role) {
        this.role = (role == null || role.trim().isEmpty()) ? "General Staff" : role.trim();
    }

    public void setShift(String shift) {
        this.shift = (shift == null || shift.trim().isEmpty()) ? "Unknown Shift" : shift.trim();
    }

    public void setStartDate(String startDate) {
        this.startDate = (startDate == null || startDate.trim().isEmpty()) ? "No Start Date" : startDate.trim();
    }

    public void setSalary(double salary) {
        this.salary = (salary >= 0) ? salary : 0;
    }

    // ── Business method ──────────────────────────────────────
    // Staff processes (confirms) an order — order stores the Staff object that did it
    public boolean processOrder(Order order) {
        if (order == null) {
            System.out.println("Cannot process a null order.");
            return false;
        }
        return order.confirmOrder(this);
    }

    // ── Displayable ──────────────────────────────────────────
    @Override
    public void displayInfo() {
        System.out.println("--- Staff ---");
        System.out.println("  Staff ID   : " + id);
        System.out.println("  Name       : " + name);
        System.out.println("  Phone      : " + phone);
        System.out.println("  Role       : " + role);
        System.out.println("  Shift      : " + shift);
        System.out.println("  Start Date : " + startDate);
        System.out.printf( "  Salary     : $%.2f%n", salary);
    }
}
