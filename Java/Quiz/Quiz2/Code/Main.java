import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;

public class Main {
	// b21827263 Murat Celik Quiz2
	public static void main(String[] args) throws FileNotFoundException {
		/*
		 * I divided it into 4 groups. 1) Volleyball 2) Basketball 3) Handball 4)
		 * IceHockey
		 */
		String fileInput = args[0];
		// 1) Volleyball
		HashSet<String> vole = ControlCheck.sportTeam("V", fileInput);
		Iterator<String> teamNameVolley = vole.iterator();

		Volleyball VteamOne = new Volleyball(teamNameVolley.next());
		Volleyball VteamTwo = new Volleyball(teamNameVolley.next());
		Volleyball VteamThree = new Volleyball(teamNameVolley.next());
		Volleyball VteamFour = new Volleyball(teamNameVolley.next());
		Volleyball[] volleyballArray = { VteamOne, VteamTwo, VteamThree, VteamFour };

		ControlCheck.DisplayTwo(volleyballArray, "V", fileInput);
		ControlCheck.sortedArray(volleyballArray);
		// 1) Volleyball

		// 2) Basketball
		HashSet<String> basket = ControlCheck.sportTeam("B", fileInput);
		Iterator<String> teamNameBasket = basket.iterator();

		Basketball BteamOne = new Basketball(teamNameBasket.next());
		Basketball BteamTwo = new Basketball(teamNameBasket.next());
		Basketball BteamThree = new Basketball(teamNameBasket.next());
		Basketball BteamFour = new Basketball(teamNameBasket.next());
		Basketball[] basketballArray = { BteamOne, BteamTwo, BteamThree, BteamFour };

		ControlCheck.DisplayTwo(basketballArray, "B", fileInput);
		ControlCheck.sortedArray(basketballArray);
		// 2) Basketball

		// 3) Handball
		HashSet<String> hand = ControlCheck.sportTeam("H", fileInput);
		Iterator<String> teamNameHand = hand.iterator();

		HandBall HteamOne = new HandBall(teamNameHand.next());
		HandBall HteamTwo = new HandBall(teamNameHand.next());
		HandBall HteamThree = new HandBall(teamNameHand.next());
		HandBall HteamFour = new HandBall(teamNameHand.next());
		HandBall[] handballArray = { HteamOne, HteamTwo, HteamThree, HteamFour };

		ControlCheck.DisplayTwo(handballArray, "H", fileInput);
		ControlCheck.sortedArray(handballArray);
		// 3) Handball

		// 4) IceHockey
		HashSet<String> ice = ControlCheck.sportTeam("I", fileInput);
		Iterator<String> teamNameIce = ice.iterator();

		IceHockey IteamOne = new IceHockey(teamNameIce.next());
		IceHockey IteamTwo = new IceHockey(teamNameIce.next());
		IceHockey IteamThree = new IceHockey(teamNameIce.next());
		IceHockey IteamFour = new IceHockey(teamNameIce.next());
		IceHockey[] iceHockeyArray = { IteamOne, IteamTwo, IteamThree, IteamFour };

		ControlCheck.DisplayTwo(iceHockeyArray, "I", fileInput);
		ControlCheck.sortedArray(iceHockeyArray);
		// 4) IceHockey
	}

}
