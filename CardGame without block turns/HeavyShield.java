public class HeavyShield extends Shield {
    public HeavyShield(String names) {
        super(names);
    }
    
    public String toString() {
        return "Card: " + super.getName();
    }
}