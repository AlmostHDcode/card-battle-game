public class LifeShield extends Shield {
    public LifeShield(String names) {
        super(names);
    }
    
    public String toString() {
        return "Card: " + super.getName();
    }
}