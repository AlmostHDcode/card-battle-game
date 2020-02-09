import java.util.Random;

public class Card implements Playable {
    private String name;
    
    public Card(String names) {
        name = names;
    }
    
    public String getName() {
        return name;
    }
    
    //player p is the target player, plays the card selected by the player and calculates the damage
    public void playCard(Player p) {
        Random generator = new Random();
        int r = generator.nextInt(p.getHandSize() - 1);
        
        if(name.equals("Light Strike")) {
        	p.setLives(p.getLives() - 2);
        } else if(name.equals("Medium Strike")) {
        	p.setLives(p.getLives() - 3);
        } else if(name.equals("Heavy Strike")) {
        	p.setLives(p.getLives() - (1 * p.getHandSize() / 2));
        } else if(name.equals("Light Grapple")) {
            p.setLives(p.getLives() - 2);
        } else if(name.equals("Medium Grapple")) {
            p.setLives(p.getLives() - 1);
        } else if(name.equals("Heavy Grapple")) {
            p.printHand();
            p.setLives(p.getLives() - 1);
        } else if(name.equals("Life Shield")) {
            
        } else if(name.equals("Light Shield")) {
            p.removeCard(r);
        } else if(name.equals("Heavy Shield")) {
        }
    }
}