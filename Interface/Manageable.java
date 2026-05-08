package Interface;

// ════════════════════════════════════════════════════════════
// Manageable Interface
// Any class that implements this MUST provide:
//   - getId()      → returns the unique identifier of the entity
//   - getSummary() → returns a short one-line summary string
//
// Applied to entities that can be searched, listed, and managed:
// Customer, Staff, ClothingItem
// ════════════════════════════════════════════════════════════
public interface Manageable {

    // Contract: return the unique ID of this entity
    String getId();

    // Contract: return a short summary of this entity (one line)
    String getSummary();
}
