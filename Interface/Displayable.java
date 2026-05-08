package Interface;

// ════════════════════════════════════════════════════════════
// Displayable Interface
// Any class that implements this MUST provide a display() method
// that prints its own information to the console.
// ════════════════════════════════════════════════════════════
public interface Displayable {

    // Contract: every implementing class must define how it displays itself
    void display();
}
