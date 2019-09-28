package azi.foosball.model;

import java.util.List;

import azi.foosball.Player;
import azi.foosball.Team;


public class MatchForm {

	List<Player> blueTeam;
	List<Player> redTeam;
	Team winner;

	public List<Player> getBlueTeam() {

		return blueTeam;
	}

	public void setBlueTeam(List<Player> blueTeam) {

		this.blueTeam = blueTeam;
	}

	public List<Player> getRedTeam() {

		return redTeam;
	}

	public void setRedTeam(List<Player> redTeam) {

		this.redTeam = redTeam;
	}

	public Team getWinner() {

		return winner;
	}

	public void setWinner(Team winner) {

		this.winner = winner;
	}
}
