public class HeavyGrapple extends Grapple {
    public HeavyGrapple(String names) {
        super(names);
    }
    
    public String toString() {
        return "Card: " + super.getName();
    }
}