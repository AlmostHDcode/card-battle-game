public class LightGrapple extends Grapple {
    public LightGrapple(String names) {
        super(names);
    }
    
    public String toString() {
        return "Card: " + super.getName();
    }
}