package org.vicho314.tda;

/**
 * Representa un jugador.
 */
public class Player{
	/**
	 * Campo id, usado para definir turno.
	 */
	private int id;
	/**
	 * Nombre del jugador.
	 */
	private String name;
	/**
	 * Nombre del color de la pieza.
	 */
	private String color;
	/**
	 * Cantidad de victorias.
	 */
	private int wins;
	/**
	 * Cantidad de derrotas.
	 */
	private int losses;
	/**
	 * Cantidad de empates.
	 */
	private int draws;
	/**
	 * Fichas restantes.
	 */
	private int rem_pieces;

	/**
	 * Constructor
	 * @param id Turno
	 * @param name String
	 * @param color String
	 * @param wins int>0
	 * @param losses int>0
	 * @param draws int>0
	 * @param rem_pieces Fichas
	 */
	public Player(int id, String name, String color, int wins, int losses, int draws, int rem_pieces) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.wins = wins;
		this.losses = losses;
		this.draws = draws;
		this.rem_pieces = rem_pieces;
	}

	/**
	 * Getter para wins
	 * @return wins
	 */
	public int getWins() {
		return wins;
	}
/*
	public void setWins(int wins) {
		this.wins = wins;
	}
*/

	/**
	 * Getter para losses
	 * @return losses
	 */
	public int getLosses() {
		return losses;
	}
/*
	public void setLosses(int losses) {
		this.losses = losses;
	}
*/

	/**
	 * Getter para draws
	 * @return draws
	 */
	public int getDraws() {
		return draws;
	}
/*
	public void setDraws(int draws) {
		this.draws = draws;
	}
*/

	/**
	 * Getter para rem_pieces
	 * @return rem_pieces
	 */
	public int getRem_pieces() {
		return rem_pieces;
	}
/*
	public void setRem_pieces(int rem_pieces) {
		this.rem_pieces = rem_pieces;
	}
*/

	/**
	 * Getter para id
	 * @return id
	 */
	public int getId() {
		return id;
	}
/*
	public void setId(int id) {
		this.id = id;
	}
*/

	/**
	 * Getter para name
	 * @return name
	 */
	public String getName() {
		return name;
	}
/*
	public void setName(String name) {
		this.name = name;
	}
*/

	/**
	 * Getter para color
	 * @return color
	 */
	public String getColor() {
		return color;
	}
/*
	public void setColor(String color) {
		this.color = color;
	}
*/

	/**
	 * Actualiza las estadísticas del jugador (wins, loss, etc) en base al resultado entero.
	 * @param winResult 1=win,0=draw,-1=loss
	 */
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

	/**
	 * Actualiza las estadísticas del jugador (wins, loss, etc) en base a la pieza ganadora.
	 * @param pz Piece!=null
	 */
	public void updateStats(Piece pz){
		if(pz.getColor() == this.getColor() ) {
			this.updateStats(1);
			return;
		}
		else if(pz.getColor() != this.getColor() ) {
			this.updateStats(-1);
			return;
        	}
		else if(pz.getColor() == null) {
			this.updateStats(0);
			return;
		}
	}

	/**
	 * Retorna true si no quedan fichas, false en caso contrario
	 * @return boolean
	 */
	public boolean noFichas(){
		return (this.rem_pieces <= 0);
	}

	/**
	 * Actualiza la cantidad de fichas, añadiendo x.
	 * @param x int
	 */
	public void updateFichas(int x){
		this.rem_pieces+=x;
	}

	/**
	 * Muestra las estadísticas del jugador por pantalla.
	 */
	public void printStats(){
		System.out.printf("\nP%d: %d Wins, %d Draws, %d Losses, %d Fichas restantes\n",this.getId(),this.getWins(),this.getDraws(),this.getLosses(),this.getRem_pieces());
	}

}
