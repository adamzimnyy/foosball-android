package azi.foosball.model;

import java.util.List;

import azi.foosball.Player;


public class PlayerStatisticsDto {

	private  final Player player;

	private final WinRates winRates;

	private  final List<PlayerMatchDto> matches;

	PlayerStatisticsDto(Player player, WinRates winRates, List<PlayerMatchDto> matches) {

		this.player = player;
		this.winRates = winRates;
		this.matches = matches;
	}

	public Player getPlayer() {

		return player;
	}

	public List<PlayerMatchDto> getMatches() {

		return matches;
	}

	public WinRates getWinRates() {

		return winRates;
	}
}
