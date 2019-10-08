package azi.foosball.model;

import java.util.List;

import azi.foosball.Player;
import azi.foosball.Team;


public class MatchForm {

	private List<Player> blueTeam;
	private List<Player> redTeam;
	private Team winner;


	public void setBlueTeam(List<Player> blueTeam) {

		this.blueTeam = blueTeam;
	}


	public void setRedTeam(List<Player> redTeam) {

		this.redTeam = redTeam;
	}

	public void setWinner(Team winner) {

		this.winner = winner;
	}
}
