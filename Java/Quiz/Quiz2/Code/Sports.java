import java.io.FileWriter;
import java.io.IOException;

public class Sports {
	protected String sportName;
	protected String teamName;
	protected Integer point;
	protected Integer winMatch;
	protected Integer drawMatch;
	protected Integer loseMatch;
	protected Integer numberOfSets;
	protected Integer numberOfSetsAgaints;

	// CONSTRUCTORS
	public Sports(String teamName, Integer point, Integer winMatch, Integer drawMatch, Integer loseMatch,
			Integer numberOfSets, Integer numberOfSetsAgaints) {
		super();
		this.teamName = teamName;
		this.point = point;
		this.winMatch = winMatch;
		this.drawMatch = drawMatch;
		this.loseMatch = loseMatch;
		this.numberOfSets = numberOfSets;
		this.numberOfSetsAgaints = numberOfSetsAgaints;
	}

	public Sports() {
		this.point = 0;
		this.winMatch = 0;
		this.drawMatch = 0;
		this.loseMatch = 0;
		this.numberOfSets = 0;
		this.numberOfSetsAgaints = 0;
	}
	// CONSTRUCTORS

	// GETTER _ SETTERS
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getWinMatch() {
		return winMatch;
	}

	public void setWinMatch(Integer winMatch) {
		this.winMatch = winMatch;
	}

	public Integer getDrawMatch() {
		return drawMatch;
	}

	public void setDrawMatch(Integer drawMatch) {
		this.drawMatch = drawMatch;
	}

	public Integer getLoseMatch() {
		return loseMatch;
	}

	public void setLoseMatch(Integer loseMatch) {
		this.loseMatch = loseMatch;
	}

	public Integer getNumberOfSets() {
		return numberOfSets;
	}

	public void setNumberOfSets(Integer numberOfSets) {
		this.numberOfSets = numberOfSets;
	}

	public Integer getNumberOfSetsAgaints() {
		return numberOfSetsAgaints;
	}

	public void setNumberOfSetsAgaints(Integer numberOfSetsAgaints) {
		this.numberOfSetsAgaints = numberOfSetsAgaints;
	}
	// GETTER _ SETTERS

	// I wrote a common method for each class.
	// If there will be a change, I override it in subclasses.
	public void winMatch(int a, int b) {
		winMatch++;
		setWinMatch(winMatch);
		point += 3;
		setPoint(point);
		numberOfSets += a;
		setNumberOfSets(numberOfSets);
		numberOfSetsAgaints += b;
		setNumberOfSetsAgaints(numberOfSetsAgaints);

	}

	public void loseMatch(int a, int b) {
		loseMatch++;
		setLoseMatch(loseMatch);
		point += 0;
		setPoint(point);
		numberOfSets += a;
		setNumberOfSets(numberOfSets);
		numberOfSetsAgaints += b;
		setNumberOfSetsAgaints(numberOfSetsAgaints);

	}

	public void drawMatch(int a, int b) {
		drawMatch++;
		setDrawMatch(drawMatch);
		point += 1;
		setPoint(point);
		numberOfSets += a;
		setNumberOfSets(numberOfSets);
		numberOfSetsAgaints += b;
		setNumberOfSetsAgaints(numberOfSetsAgaints);

	}

	// write a output file for each subclass
	public void DisplayWriteOutput(String nameSport, int i) {
		System.out.println(i + ".\t" + teamName + "\t" + (getWinMatch() + getLoseMatch() + getDrawMatch()) + "\t"
				+ getWinMatch() + "\t" + getDrawMatch() + "\t" + getLoseMatch() + "\t" + getNumberOfSets() + ":"
				+ getNumberOfSetsAgaints() + "\t" + getPoint());
		try {
			FileWriter myWriter = new FileWriter(nameSport + ".txt", true);
			myWriter.write(i + ".\t" + teamName + "\t" + (getWinMatch() + getLoseMatch() + getDrawMatch()) + "\t"
					+ getWinMatch() + "\t" + getDrawMatch() + "\t" + getLoseMatch() + "\t" + getNumberOfSets() + ":"
					+ getNumberOfSetsAgaints() + "\t" + getPoint() + "\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
}
