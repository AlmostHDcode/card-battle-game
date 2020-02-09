public class MediumStrike extends Strike {
    public MediumStrike(String names) {
        super(names);
    }
    
    public String toString() {
        return "Card: " + super.getName();
    }
}