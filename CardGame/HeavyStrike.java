public class HeavyStrike extends Strike {
    public HeavyStrike(String names) {
        super(names);
    }
    
    public String toString() {
        return "Card: " + super.getName();
    }
}