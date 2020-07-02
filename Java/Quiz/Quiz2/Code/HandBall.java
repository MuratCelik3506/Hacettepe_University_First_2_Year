
public class HandBall extends Sports {

	public HandBall(String teamName) {
		super();
		this.sportName = "Handball";
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
		winMatch++;
		setWinMatch(winMatch);
		point += 2;
		setPoint(point);
		numberOfSets += a;
		setNumberOfSets(numberOfSets);
		numberOfSetsAgaints += b;
		setNumberOfSetsAgaints(numberOfSetsAgaints);

	}

}
