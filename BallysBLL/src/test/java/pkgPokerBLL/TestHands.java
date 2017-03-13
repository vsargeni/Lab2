package pkgPokerBLL;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;

public class TestHands {

	@Test
	public void TestFullHouse() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be a full house
		assertEquals(eHandStrength.FullHouse.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// LO hand better be 'Three'
		assertEquals(eRank.THREE.getiRankNbr(), h.getHandScore().getLoHand().getiRankNbr());

		// Full House has no kickers.
		assertEquals(0, h.getHandScore().getKickers().size());
	}

	@Test
	public void Test4ofaKind() {

		Hand a = new Hand();
		a.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		a.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		a.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		a.AddCardToHand(new Card(eRank.FOUR, eSuit.HEARTS));
		a.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		a.EvaluateHand();
		
		Hand b = new Hand();
		b.AddCardToHand(new Card(eRank.SEVEN, eSuit.CLUBS));
		b.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		b.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		b.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		b.AddCardToHand(new Card(eRank.FOUR, eSuit.HEARTS));
		b.EvaluateHand();

		// hand check
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(), a.getHandScore().getHandStrength().getHandStrength());
		//kickers
		assertEquals(1, a.getHandScore().getKickers().size());
		
		//hand check 2
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(), b.getHandScore().getHandStrength().getHandStrength());
		assertEquals(1, b.getHandScore().getKickers().size());
	}

	@Test
	public void TestStraightFlush() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SIX, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FIVE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TWO, eSuit.CLUBS));
		h.EvaluateHand();

		// hand check
		assertEquals(eHandStrength.StraightFlush.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		assertEquals(0, h.getHandScore().getKickers().size());

	}

	@Test
	public void TestisStraight() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SIX, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FIVE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.TWO, eSuit.CLUBS));
		h.EvaluateHand();
		
		// complete the deal... or face the wheel
		Hand b = new Hand();
		b.AddCardToHand(new Card(eRank.ACE, eSuit.CLUBS));
		b.AddCardToHand(new Card(eRank.FIVE, eSuit.DIAMONDS));
		b.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		b.AddCardToHand(new Card(eRank.THREE, eSuit.HEARTS));
		b.AddCardToHand(new Card(eRank.TWO, eSuit.CLUBS));
		b.EvaluateHand();

		// hand check
		assertEquals(eHandStrength.Straight.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
		// kickers
		assertEquals(0, h.getHandScore().getKickers().size());
		
		//hand check 2
		assertEquals(eHandStrength.Straight.getHandStrength(), b.getHandScore().getHandStrength().getHandStrength());
		//kickers
		assertEquals(0, b.getHandScore().getKickers().size());
	}

	@Test
	public void testHandpair() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SEVEN, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.TWO, eSuit.CLUBS));
		h.EvaluateHand();

		// hand check
		assertEquals(eHandStrength.Pair.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
		// kickers
		assertEquals(3, h.getHandScore().getKickers().size());

	}

	@Test
	public void testisFlush() {

		Hand a = new Hand();
		a.AddCardToHand(new Card(eRank.TEN, eSuit.CLUBS));
		a.AddCardToHand(new Card(eRank.SEVEN, eSuit.CLUBS));
		a.AddCardToHand(new Card(eRank.FIVE, eSuit.CLUBS));
		a.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		a.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		a.EvaluateHand();

		// hand check
		assertEquals(eHandStrength.Flush.getHandStrength(), a.getHandScore().getHandStrength().getHandStrength());
		//kickers
		assertEquals(0, a.getHandScore().getKickers().size());
	}

	@Test
	public void threeofakind() {

		Hand a = new Hand();
		a.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		a.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		a.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		a.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		a.AddCardToHand(new Card(eRank.TWO, eSuit.CLUBS));
		a.EvaluateHand();

		Hand b = new Hand();
		b.AddCardToHand(new Card(eRank.TEN, eSuit.CLUBS));
		b.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		b.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		b.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		b.AddCardToHand(new Card(eRank.TWO, eSuit.CLUBS));
		b.EvaluateHand();
		
		// hand check
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),
				a.getHandScore().getHandStrength().getHandStrength());
		//kickers
		assertEquals(2, a.getHandScore().getKickers().size());
		
		//hand check 2
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),
				b.getHandScore().getHandStrength().getHandStrength());
		//kickers
		assertEquals(2, b.getHandScore().getKickers().size());

	}

	@Test
	public void TestROYALFLUSH() {

		Hand a = new Hand();
		a.AddCardToHand(new Card(eRank.ACE, eSuit.SPADES));
		a.AddCardToHand(new Card(eRank.KING, eSuit.SPADES));
		a.AddCardToHand(new Card(eRank.QUEEN, eSuit.SPADES));
		a.AddCardToHand(new Card(eRank.JACK, eSuit.SPADES));
		a.AddCardToHand(new Card(eRank.TEN, eSuit.SPADES));
		a.EvaluateHand();

		// hand check
		assertEquals(eHandStrength.RoyalFlush.getHandStrength(), a.getHandScore().getHandStrength().getHandStrength());
		assertEquals(0, a.getHandScore().getKickers().size());

	}

	@Test
	public void Testhighcard() {

		Hand a = new Hand();
		a.AddCardToHand(new Card(eRank.QUEEN, eSuit.CLUBS));
		a.AddCardToHand(new Card(eRank.TEN, eSuit.DIAMONDS));
		a.AddCardToHand(new Card(eRank.SEVEN, eSuit.CLUBS));
		a.AddCardToHand(new Card(eRank.FIVE, eSuit.SPADES));
		a.AddCardToHand(new Card(eRank.TWO, eSuit.HEARTS));

		a.EvaluateHand();

		// hand check
		assertEquals(eHandStrength.HighCard.getHandStrength(), a.getHandScore().getHandStrength().getHandStrength());
		assertEquals(4, a.getHandScore().getKickers().size());
	}

	@Test
	public void testtwopair() {

		Hand a = new Hand();
		a.AddCardToHand(new Card(eRank.SIX, eSuit.CLUBS));
		a.AddCardToHand(new Card(eRank.SIX, eSuit.DIAMONDS));
		a.AddCardToHand(new Card(eRank.THREE, eSuit.SPADES));
		a.AddCardToHand(new Card(eRank.THREE, eSuit.HEARTS));
		a.AddCardToHand(new Card(eRank.TWO, eSuit.CLUBS));
		a.EvaluateHand();

		Hand b = new Hand();
		b.AddCardToHand(new Card(eRank.SIX, eSuit.CLUBS));
		b.AddCardToHand(new Card(eRank.SIX, eSuit.DIAMONDS));
		b.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		b.AddCardToHand(new Card(eRank.THREE, eSuit.HEARTS));
		b.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		b.EvaluateHand();

		// hand check 1
		assertEquals(eHandStrength.TwoPair.getHandStrength(), a.getHandScore().getHandStrength().getHandStrength());
		assertEquals(1, a.getHandScore().getKickers().size());

		// hand check 2
		assertEquals(eHandStrength.TwoPair.getHandStrength(), b.getHandScore().getHandStrength().getHandStrength());
		assertEquals(1, b.getHandScore().getKickers().size());
	}

	@Test
	public void testAcesandEights() {

		Hand a = new Hand();
		a.AddCardToHand(new Card(eRank.ACE, eSuit.CLUBS));
		a.AddCardToHand(new Card(eRank.ACE, eSuit.DIAMONDS));
		a.AddCardToHand(new Card(eRank.EIGHT, eSuit.SPADES));
		a.AddCardToHand(new Card(eRank.EIGHT, eSuit.HEARTS));
		a.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		a.EvaluateHand();

		Hand b = new Hand();
		b.AddCardToHand(new Card(eRank.ACE, eSuit.CLUBS));
		b.AddCardToHand(new Card(eRank.ACE, eSuit.DIAMONDS));
		b.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		b.AddCardToHand(new Card(eRank.EIGHT, eSuit.SPADES));
		b.AddCardToHand(new Card(eRank.EIGHT, eSuit.HEARTS));

		b.EvaluateHand();

		// hand check 1
		assertEquals(eHandStrength.AcesAndEights.getHandStrength(),
				a.getHandScore().getHandStrength().getHandStrength());
		assertEquals(1, a.getHandScore().getKickers().size());
		// hand check 2
		assertEquals(eHandStrength.AcesAndEights.getHandStrength(),
				b.getHandScore().getHandStrength().getHandStrength());
		assertEquals(1, b.getHandScore().getKickers().size());

	}

}
