package pkgPokerBLL;

import java.util.ArrayList;
import java.util.UUID;

import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;

public class Deck {

	private UUID DeckID;
	private ArrayList<Card> DeckCards = new ArrayList<Card>();
	
	public Deck()
	{
		//TODO: Implement This Constructor (no-arg Deck should build up a deck with 52 cards)
		for (eRank Rank : eRank.values()){
			for (eSuit Suit: eSuit.values()){
				Card c= new Card(Rank, Suit);
				DeckCards.add(c);
			}
		}
		
		
		//	This method will do a for/each, returning each rank in the enum.
		for (eRank Rank : eRank.values()) {
			System.out.println(Rank.getiRankNbr());
		}
	}
	
	public Card DrawCard()
	{
		//	TODO: Implement this method... should draw a card from the deck.-DONE
		DeckCards.remove(0);
		
		return null;
	}
}
