package org.vicho314.tda;

public class Player{

	private int id;
	private String name;
	private String color;
	private int wins;
	private int losses;
	private int draws;
	private int rem_pieces;

	public Player(int id, String name, String color, int wins, int losses, int draws, int rem_pieces) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.wins = wins;
		this.losses = losses;
		this.draws = draws;
		this.rem_pieces = rem_pieces;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public int getRem_pieces() {
		return rem_pieces;
	}

	public void setRem_pieces(int rem_pieces) {
		this.rem_pieces = rem_pieces;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void updateStats(int winResult){
		switch(winResult) {
			case 1:
				this.wins+=1;
				break;
			case 0:
				this.draws+=1;
				break;
			case -1:
				this.losses+=1;
				break;
			default:
				break;
        }
	}
}
