package pkgPokerBLL;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import pkgPokerEnum.eCardNo;
import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;

public class Hand {

	private UUID HandID;
	private boolean bIsScored;
	private HandScore HS;
	private ArrayList<Card> CardsInHand = new ArrayList<Card>();

	public Hand() {

	}

	public void AddCardToHand(Card c) {
		CardsInHand.add(c);
	}

	public ArrayList<Card> getCardsInHand() {
		return CardsInHand;
	}

	public HandScore getHandScore() {
		return HS;
	}

	public Hand EvaluateHand() {
		Hand h = Hand.EvaluateHand(this);
		return h;
	}

	private static Hand EvaluateHand(Hand h) {

		Collections.sort(h.getCardsInHand());

		// Another way to sort
		// Collections.sort(h.getCardsInHand(), Card.CardRank);

		HandScore hs = new HandScore();
		try {
			Class<?> c = Class.forName("pkgPokerBLL.Hand");

			for (eHandStrength hstr : eHandStrength.values()) {
				Class[] cArg = new Class[2];
				cArg[0] = pkgPokerBLL.Hand.class;
				cArg[1] = pkgPokerBLL.HandScore.class;

				Method meth = c.getMethod(hstr.getEvalMethod(), cArg);
				Object o = meth.invoke(null, new Object[] { h, hs });

				// If o = true, that means the hand evaluated- skip the rest of
				// the evaluations
				if ((Boolean) o) {
					break;
				}
			}

			h.bIsScored = true;
			h.HS = hs;

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return h;
	}

	// TODO: Implement This Method
	public static boolean isHandRoyalFlush(Hand h, HandScore hs) {
		boolean bool = false;
		if (isHandFlush(h, hs) && isHandStraight(h, hs) && isAce(h.getCardsInHand())) {
			bool = true;
			hs.setHandStrength(eHandStrength.RoyalFlush);
		} else
			bool = false;

		return bool;

	}

	// TODO: Implement This Method
	public static boolean isHandStraightFlush(Hand h, HandScore hs) {
		boolean bool = false;
		if (isHandStraight(h, hs) && isHandFlush(h, hs)) {
			bool = true;
			hs.setHandStrength(eHandStrength.StraightFlush);
		} else
			bool = false;
		return bool;
	}

	// TODO: Implement This Method
	public static boolean isHandFourOfAKind(Hand h, HandScore hs) {
		if ((h.getCardsInHand().get(0).geteRank() == h.getCardsInHand().get(1).geteRank()
				&& h.getCardsInHand().get(0).geteRank() == h.getCardsInHand().get(2).geteRank()
				&& h.getCardsInHand().get(0).geteRank() == h.getCardsInHand().get(3).geteRank()
				|| h.getCardsInHand().get(1).geteRank() == h.getCardsInHand().get(2).geteRank()
						&& h.getCardsInHand().get(1).geteRank() == h.getCardsInHand().get(3).geteRank()
						&& h.getCardsInHand().get(1).geteRank() == h.getCardsInHand().get(4).geteRank())) {
			
			hs.setHandStrength(eHandStrength.FourOfAKind);
			return true;
		} else
			return false;

	}

	// TODO: Implement This Method
	public static boolean isHandFlush(Hand h, HandScore hs) {
		boolean bool = false;

		if (h.CardsInHand.get(eCardNo.FirstCard.getCardNo()).geteSuit() == h.CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).geteSuit()
				&& h.CardsInHand.get(eCardNo.FirstCard.getCardNo()).geteSuit() == h.CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).geteSuit()
				&& h.CardsInHand.get(eCardNo.FirstCard.getCardNo()).geteSuit() == h.CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).geteSuit()
				&& h.CardsInHand.get(eCardNo.FirstCard.getCardNo()).geteSuit() == h.CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).geteSuit()) {
			bool = true;
			hs.setHandStrength(eHandStrength.Flush);
		} else
			bool = false;
		return bool;
	}

	// TODO: Implement This Method
	public static boolean isHandStraight(Hand h, HandScore hs) {
		
		if (isStraight(h.getCardsInHand())){
			hs.setHandStrength(eHandStrength.Straight);
			return true;	
		}
		return false;
	}

	public static boolean isAce(ArrayList<Card> c) {
		boolean bool = false;
		if (c.get(0).geteRank() == eRank.ACE) {
			if ((c.get(1).geteRank() == eRank.KING || (c.get(1).geteRank() == eRank.FIVE))) {

				bool = true;
			} else
				bool = false;
		}
		return bool;

	}

	public static boolean isStraight(ArrayList<Card> c) {
		boolean isStraight = true;
		int i = 0;
		if (Hand.isAce(c) == true) {
			i = 1;
		} else {
			i = 0;
		}
		for (; i < 3; i++) {
			if (c.get(i).geteRank().getiRankNbr() == c.get(i + 1).geteRank().getiRankNbr()) {
				isStraight = true;
			} else
				isStraight = false;
		}
		return isStraight;

	}

	// TODO: Implement This Method
	public static boolean isHandThreeOfAKind(Hand h, HandScore hs) {
		if (((h.getCardsInHand().get(0).geteRank() == h.getCardsInHand().get(1).geteRank()
				&& h.getCardsInHand().get(0).geteRank() == h.getCardsInHand().get(2).geteRank())
				|| h.getCardsInHand().get(1).geteRank() == h.getCardsInHand().get(2).geteRank()
						&& h.getCardsInHand().get(1).geteRank() == h.getCardsInHand().get(3).geteRank())
				|| (h.getCardsInHand().get(2).geteRank() == h.getCardsInHand().get(3).geteRank()
						&& h.getCardsInHand().get(2).geteRank() == h.getCardsInHand().get(4).geteRank())) {
			hs.setHandStrength(eHandStrength.FullHouse);
			return true;
		} else

			return false;
	}

	// TODO: Implement This Method
	public static boolean isHandTwoPair(Hand h, HandScore hs) {
		boolean isTwoPair = false;
		if (isAcesAndEights(h, hs) == true) {

			isTwoPair = false;
		} else {

			if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
					.get(eCardNo.SecondCard.getCardNo()).geteRank())
					&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
							.get(eCardNo.FourthCard.getCardNo()).geteRank())) {
				isTwoPair = true;
				hs.setHandStrength(eHandStrength.TwoPair);
				hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
				hs.setLoHand(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank());
			}
			if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
					.get(eCardNo.SecondCard.getCardNo()).geteRank())
					&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
							.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
				isTwoPair = true;
				hs.setHandStrength(eHandStrength.TwoPair);
				hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
				hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
			} else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
					.get(eCardNo.ThirdCard.getCardNo()).geteRank())
					&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
							.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
				isTwoPair = true;
				hs.setHandStrength(eHandStrength.TwoPair);
				hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
				hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
			}
		}
		return isTwoPair;

	}

	// TODO: Implement This Method
	public static boolean isHandPair(Hand h, HandScore hs) {
		boolean isHandPair = false;
		if (h.getCardsInHand().get(0).geteRank() == h.getCardsInHand().get(1).geteRank()
				^ h.getCardsInHand().get(1).geteRank() == h.getCardsInHand().get(2).geteRank()
				^ h.getCardsInHand().get(2).geteRank() == h.getCardsInHand().get(3).geteRank()
				^ h.getCardsInHand().get(3).geteRank() == h.getCardsInHand().get(4).geteRank()) {
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
		} else
			isHandPair = false;

		return isHandPair;
	}

	// TODO: Implement This Method
	public static boolean isHandHighCard(Hand h, HandScore hs) {
		boolean bool = false;
		if (!isAcesAndEights(h, hs) && !isHandPair(h, hs) && !isHandTwoPair(h, hs) && !isHandThreeOfAKind(h, hs)
				&& !isHandStraight(h, hs) && !isHandRoyalFlush(h, hs) && !isHandFullHouse(h, hs)
				&& !isHandFourOfAKind(h, hs) && isHandFlush(h, hs)) {
			bool = true;
			hs.setHandStrength(eHandStrength.HighCard);
		} else
			bool = false;

		return bool;
	}

	// TODO: Implement This Method
	public static boolean isAcesAndEights(Hand h, HandScore hs) {

		boolean isAcesAndEights = false;
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FourthCard.getCardNo()).geteRank())) {
			isAcesAndEights = true;
			hs.setHandStrength(eHandStrength.AcesAndEights);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank());
		}
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isAcesAndEights = true;
			hs.setHandStrength(eHandStrength.AcesAndEights);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
		} else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isAcesAndEights = true;
			hs.setHandStrength(eHandStrength.AcesAndEights);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
		}
		return isAcesAndEights;
	}

	public static boolean isHandFullHouse(Hand h, HandScore hs) {

		boolean isFullHouse = false;

		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
		} else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
		}

		return isFullHouse;

	}
}
