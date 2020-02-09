import java.util.Scanner;
import java.util.Random;

public class Game {
    public void run() {
        Scanner scan = new Scanner(System.in);
        Random generator = new Random();
        
        Player p1 = new Player("User", 10);
        Player p2 = new Player("Computer", 10);
        
        //decks are created and three cards are drawn to the hand
        p1.createDeck();
        p2.createDeck();
        
        p1.draw();
        p1.draw();
        p1.draw();
        
        p2.draw();
        p2.draw();
        p2.draw();
        
        //when the deck runs out the draw method stops taking from the deck so only the hand cards are left, get played and are removed
        //when one persons hand runs out or runs out of lives the loop stops and the game ends
        while( (p1.getLives() > 0 && p2.getLives() > 0) && (p1.getHandSize() > 0 || p2.getHandSize() > 0) ) {
            p1.printHand();
            
            System.out.println("start from 0 (top to bottom) and type the number of the card you wish to play");
            int pCard = scan.nextInt();
            
            int compCard = generator.nextInt(p2.getHandSize());
            
            System.out.println("User played: " + p1.getCard(pCard));
            System.out.println("Computer played: " + p2.getCard(compCard));
            
            //checks how the cards match up
            //checks what sub sub type then calls playCard to calculate damages
            //carries out any special effects of each card
            if(p1.getCard(pCard) instanceof Strike && p2.getCard(compCard) instanceof Grapple) {
                if(p1.getCard(pCard) instanceof LightStrike) {
                    p1.getCard(pCard).playCard(p2);
                    p1.draw();
                    System.out.println(p1.getName() + " did 2 damage and draws a card");
                    p1.removeCard(pCard);
                    p2.removeCard(compCard);
                }else if(p1.getCard(pCard) instanceof MediumStrike) {
                    p1.getCard(pCard).playCard(p2);
                    System.out.println(p1.getName() + " did 3 damage");
                    p1.removeCard(pCard);
                    p2.removeCard(compCard);
                }else if(p1.getCard(pCard) instanceof HeavyStrike) {
                    p1.removeCard(pCard);
                    p1.getCard(pCard).playCard(p2);
                    p1.discardHand();
                    p2.removeCard(compCard);
                    System.out.println(p1.getName() + " discards hand and does 1 damage plus 1 damage for every 2 cards discarded");
                    p1.draw();
                    p1.draw();
                    p1.draw();
                }
            }else if(p1.getCard(pCard) instanceof Grapple && p2.getCard(compCard) instanceof Shield) {
                if(p1.getCard(pCard) instanceof LightGrapple) {
                    p1.getCard(pCard).playCard(p2);
                    p1.draw();
                    System.out.println(p1.getName() + " did 2 damage, draws a card, and opponent cannot use a Shield next turn");
                    p1.removeCard(pCard);
                    p2.removeCard(compCard);
                }else if(p1.getCard(pCard) instanceof MediumGrapple) {
                    p1.getCard(pCard).playCard(p2);
                    p1.setLives(p1.getLives() + 2);
                    System.out.println(p1.getName() + " did 1 damage, got 2 lives back, and opponent cannot use a Strike next turn");
                    p1.removeCard(pCard);
                    p2.removeCard(compCard);
                }else if(p1.getCard(pCard) instanceof HeavyGrapple) {
                    p1.getCard(pCard).playCard(p2);
                    System.out.println(p1.getName() + " did 1 damage, looks at opponents hand, and opponent cannot use a grapple next turn");
                    p1.removeCard(pCard);
                    p2.removeCard(compCard);
                }
            }else if(p1.getCard(pCard) instanceof Shield && p2.getCard(compCard) instanceof Strike) {
                if(p1.getCard(pCard) instanceof LifeShield) {
                    p1.getCard(pCard).playCard(p2);
                    p1.setLives(p1.getLives() + 2);
                    System.out.println(p1.getName() + " gets 2 lives back");
                    p2.removeCard(compCard);
                }else if(p1.getCard(pCard) instanceof LightShield) {
                    p1.getCard(pCard).playCard(p2);
                    System.out.println(p2.getName() + " has to discard a random card");
                }else if(p1.getCard(pCard) instanceof HeavyShield) {
                    p1.getCard(pCard).playCard(p2);
                    p1.addCard(p2.getCard(compCard));
                    p2.removeCard(compCard);
                    System.out.println(p1.getName() + " gets the card from the opponent and adds to hand");
                }
            }else if(p2.getCard(compCard) instanceof Strike && p1.getCard(pCard) instanceof Grapple) {
                if(p2.getCard(compCard) instanceof LightStrike) {
                    p2.getCard(compCard).playCard(p1);
                    p2.draw();
                    System.out.println(p2.getName() + " did 2 damage and draws a card");
                    p2.removeCard(compCard);
                    p1.removeCard(pCard);
                }else if(p2.getCard(compCard) instanceof MediumStrike) {
                    p2.getCard(compCard).playCard(p1);
                    System.out.println(p2.getName() + " did 3 damage");
                    p1.removeCard(pCard);
                    p2.removeCard(compCard);
                }else if(p2.getCard(compCard) instanceof HeavyStrike) {
                    p2.removeCard(compCard);
                    p2.getCard(compCard).playCard(p1);
                    p2.discardHand();
                    p1.removeCard(pCard);
                    System.out.println(p2.getName() + " discards hand and does 1 damage plus 1 damage for every 2 cards discarded");
                    p2.draw();
                    p2.draw();
                    p2.draw();
                }
            }else if(p2.getCard(compCard) instanceof Grapple && p1.getCard(pCard) instanceof Shield) {
                if(p2.getCard(compCard) instanceof LightGrapple) {
                    p2.getCard(compCard).playCard(p1);
                    p2.draw();
                    System.out.println(p2.getName() + " did 2 damage, draws a card, and opponent cannot block next turn");
                    p2.removeCard(compCard);
                    p1.removeCard(pCard);
                }else if(p2.getCard(compCard) instanceof MediumGrapple) {
                    p2.getCard(compCard).playCard(p1);
                    p2.setLives(p2.getLives() + 2);
                    System.out.println(p2.getName() + " did 1 damage, got 2 lives back, and opponent cannot attack next turn");
                    p2.removeCard(compCard);
                    p1.removeCard(pCard);
                }else if(p2.getCard(compCard) instanceof HeavyGrapple) {
                    p2.getCard(compCard).playCard(p1);
                    System.out.println(p2.getName() + " did 1 damage, looks at opponents hand, and opponent cannot grapple next turn");
                    p2.removeCard(compCard);
                    p1.removeCard(pCard);
                }
            }else if(p2.getCard(compCard) instanceof Shield && p1.getCard(pCard) instanceof Strike) {
                if(p2.getCard(compCard) instanceof LifeShield) {
                    p2.getCard(compCard).playCard(p1);
                    p2.setLives(p2.getLives() + 2);
                    System.out.println(p2.getName() + " gets 2 lives back");
                    p1.removeCard(pCard);
                }else if(p2.getCard(compCard) instanceof LightShield) {
                    p2.getCard(compCard).playCard(p1);
                    System.out.println(p1.getName() + " has to discard a random card");
                }else if(p2.getCard(compCard) instanceof HeavyShield) {
                    p2.getCard(compCard).playCard(p1);
                    p2.addCard(p1.getCard(pCard));
                    p1.removeCard(pCard);
                    System.out.println(p2.getName() + " gets the card from the opponent and adds to hand");
                }
            }else {
                System.out.println("It is a draw, both cards are a type of Strike, Grapple, or Shield");
                p1.removeCard(pCard);
                p2.removeCard(compCard);
            }
            
            p1.draw();
            p2.draw();
            
            System.out.println();
            System.out.println(p1);
            System.out.println(p2);
            System.out.println();
        }
        if(p1.getHandSize() <= 0) {
            System.out.println(p1.getName() + " is out of cards");
        }else if(p2.getHandSize() <= 0) {
            System.out.println(p2.getName() + " is out of cards");
        }
        if(p1.getLives() <= 0 || ( p1.getLives() < p2.getLives() ) ) {
            System.out.println(p2.getName() + " wins");
            System.out.println("Final Score:");
            System.out.println(p1);
            System.out.println(p2);
        }else if(p2.getLives() <= 0 || ( p1.getLives() > p2.getLives() ) ) {
            System.out.println(p1.getName() + " wins");
            System.out.println("Final Score:");
            System.out.println(p1);
            System.out.println(p2);
        }
    }
}