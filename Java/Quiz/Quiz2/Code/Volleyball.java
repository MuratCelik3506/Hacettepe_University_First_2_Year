
public class Volleyball extends Sports {

	public Volleyball(String teamName) {
		super();
		this.sportName = "Volleyball";
		this.teamName = teamName;
		this.point = 0;
		this.winMatch = 0;
		this.drawMatch = 0;
		this.loseMatch = 0;
		this.numberOfSets = 0;
		this.numberOfSetsAgaints = 0;
	}

	@Override
	public void winMatch(int a, int b) {
		if (a - b >= 2) {
			point += 3;
			setPoint(point);
		} else {
			point += 2;
			setPoint(point);
		}
		winMatch++;
		setWinMatch(winMatch);
		numberOfSets += a;
		setNumberOfSets(numberOfSets);
		numberOfSetsAgaints += b;
		setNumberOfSetsAgaints(numberOfSetsAgaints);

	}

	@Override
	public void loseMatch(int a, int b) {
		if (b - a >= 2) {
			point += 0;
			setPoint(point);
		} else {
			point += 1;
			setPoint(point);
		}
		loseMatch++;
		setLoseMatch(loseMatch);
		numberOfSets += a;
		setNumberOfSets(numberOfSets);
		numberOfSetsAgaints += b;
		setNumberOfSetsAgaints(numberOfSetsAgaints);

	}

}
