import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ControlCheck {
	// read input
	public static ArrayList<String> readInput(String fileInput) {
		try {
			File fileObj = new File(fileInput);
			Scanner file = new Scanner(fileObj);
			ArrayList<String> inputfile = new ArrayList<String>();
			while (file.hasNextLine()) {
				inputfile.add(file.nextLine());
			}
			file.close();
			return inputfile;
		} catch (

		Exception e) {
			System.out.println("Exception in ControlCheck 24");
		}
		return null;
	}

	// assign teams
	public static HashSet<String> sportTeam(String teamSport,String file) throws FileNotFoundException {
		ArrayList<String> teamArray = readInput(file);
		HashSet<String> teamSet = new HashSet<String>();
		for (int i = 0; i < teamArray.size(); i++) {
			String line = teamArray.get(i);
			if (teamSport.matches(String.valueOf(line.charAt(0)))) {
				String[] array = line.split("\t");
				teamSet.add(array[1]);
				teamSet.add(array[2]);
			}
		}
		return teamSet;
	}

	// run a checkPoint method
	public static void DisplayTwo(Sports[] object, String s,String fileInput) {
		for (int i = 0; i < 4; i++) {
			checkPoint(object[i], s, fileInput);
		}
	}

	// analyze match scores
	public static void checkPoint(Sports object, String teamSport,String fileInput) {

		ArrayList<String> inputfile = readInput(fileInput);
		for (int i = 0; i < inputfile.size(); i++) {

			String line = inputfile.get(i);

			if (teamSport.matches(String.valueOf(line.charAt(0)))) {

				String[] array = line.split("\t");
				String[] goal = array[3].split(":");
				int teamOneGoal = Integer.parseInt(goal[0]);
				int teamTwoGoal = Integer.parseInt(goal[1]);

				if (object.teamName.equals(array[1])) {
					houseOwner(object, teamOneGoal, teamTwoGoal);
				} else if (object.teamName.equals(array[2])) {
					houseGuest(object, teamOneGoal, teamTwoGoal);
				}

			}
		}

	}

	// if the match will be played at your stadium
	public static void houseOwner(Sports object, int teamOneGoal, int teamTwoGoal) {
		if (teamOneGoal > teamTwoGoal) {
			object.winMatch(teamOneGoal, teamTwoGoal);
		} else if (teamOneGoal == teamTwoGoal) {
			object.drawMatch(teamOneGoal, teamTwoGoal);
		} else {
			object.loseMatch(teamOneGoal, teamTwoGoal);
		}
	}

	// if the match is played at the opponent's stadium
	public static void houseGuest(Sports object, int teamOneGoal, int teamTwoGoal) {
		if (teamOneGoal < teamTwoGoal) {
			object.winMatch(teamTwoGoal, teamOneGoal);
		} else if (teamOneGoal == teamTwoGoal) {
			object.drawMatch(teamTwoGoal, teamOneGoal);
		} else {
			object.loseMatch(teamTwoGoal, teamOneGoal);
		}
	}

	// create a result table
	public static void sortedArray(Sports[] array) {
		int i, j, min_idx;

		for (i = 0; i < 3; i++) {

			min_idx = i;
			for (j = i + 1; j < 4; j++)
				if (array[j].point < array[min_idx].point) {
					min_idx = j;
				} else if (array[j].point == array[min_idx].point) {
					if ((array[j].numberOfSets - array[j].numberOfSetsAgaints) < (array[min_idx].numberOfSets
							- array[min_idx].numberOfSetsAgaints)) {
						min_idx = j;
					}
				}
			Sports object = array[i];
			array[i] = array[min_idx];
			array[min_idx] = object;

		}
		sortedArrayDisplay(array);
	}

	// print a result table
	public static void sortedArrayDisplay(Sports[] array) {
		for (int i = 3, j = 1; i >= 0; i--, j++) {
			array[i].DisplayWriteOutput(array[i].sportName, j);
		}
		System.out.println("*******************");
	}

}
