public class LightShield extends Shield {
    public LightShield(String names) {
        super(names);
    }
    
    public String toString() {
        return "Card: " + super.getName();
    }
}