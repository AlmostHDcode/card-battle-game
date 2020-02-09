public class LightStrike extends Strike {
    public LightStrike(String names) {
        super(names);
    }
    
    public String toString() {
        return "Card: " + super.getName();
    }
}