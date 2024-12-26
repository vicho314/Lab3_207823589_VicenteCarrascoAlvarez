package org.vicho314.tda;

import java.util.Stack;

public class Game {
	private Player p1;
	private Player p2;
	private Board brd;
	private int cturn;
	private Stack<History> history = new Stack<History>();

	public Game(Player p1, Player p2, Board brd, int cturn){
		this.p1 = p1;
		this.p2 = p2;
		this.brd = brd;
		this.cturn = cturn;
		this.history = new Stack<History>();
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public boolean playersNoFichas(){
		return this.p1.noFichas() && this.p2.noFichas();
	}

	public Board getBrd() {
		return brd;
	}

	public void setBrd(Board brd) {
		this.brd = brd;
	}

	public int getCturn() {
		return cturn;
	}

	public void setCturn(int cturn) {
		this.cturn = cturn;
	}

	public Stack<History> getHistory() {
		return history;
	}

	public void setHistory(Stack<History> history) {
		this.history = history;
	}

	public void history(){
		System.out.println("\nHistorial de movimientos:");
		for(History reg: this.history) {
			System.out.printf("(%d, %s)\n",reg.getCol(),reg.getColor());
		}
	}

	public boolean esEmpate(){
		return (this.brd.isFull() || this.playersNoFichas());
	}

	public Player getCurrentPlayer(){
		int cturn = this.getCturn();
		switch(cturn){
			case 0:
				return this.getP1();
			case 1:
				return this.getP2();
			default:
				return null;
		}
	}

	public void updateCurrentPlayerFichas(int fichas){
		int cturn = this.getCturn();
		switch(cturn){
			case 0:
				this.p1.updateFichas(fichas);
				break;
			case 1:
				this.p2.updateFichas(fichas);
				break;
			default:
				System.out.println("Error: updateFichas: cturn no asociado a nadie.\n");
				break;
		}
	}

	public Board boardGetState(){
		Board brd = this.getBrd();
		System.out.println(brd);
		return brd;
	}

	public void endGame(){
		this.setCturn(-1);
		Piece ganador = this.brd.entregarGanador();
		//Fixme: USAR esEmpate!!
		this.p1.updateStats(ganador);
		this.p2.updateStats(ganador);
		if(ganador != null){
			System.out.printf("Juego terminado con ganador: %s\n",ganador.getColor());
		}
		else{
			System.out.println("Juego terminado con ganador: Empate!\n");
		}
		//Fixme: print ganador!
	}

	public void flipTurn(){
		int cturn = this.getCturn();
		if(cturn == -1) {
			return;
		}
		this.setCturn((this.getCturn() + 1 ) % 2);
	}

	public void printJugadaHecha(Player pl,int x){
		if(pl != null){
			System.out.format("Jugada: %s, en columna %d",pl.getName(),x);
		}
		else{
			System.out.println("Error: Jugada de jugador nulo!\n");
		}
	}
	
	public void realizarMovimiento(Player pl, int x){
		Player CurrentPlayer = this.getCurrentPlayer();
		boolean jugada;
		if(CurrentPlayer != null){
			if(CurrentPlayer.getName() == pl.getName()){
				jugada = this.brd.jugarFicha(new Piece(CurrentPlayer.getColor()),x);
				if(jugada){
					this.printJugadaHecha(pl,x);
					this.updateCurrentPlayerFichas(-1);
					this.flipTurn();
					this.history.push(new History(x,CurrentPlayer.getColor()));
					Piece winner = this.brd.entregarGanador();
					if(winner != null || this.esEmpate()){
						this.endGame();
					}
					//fixme: añadir update stats if true
				}
			}
			else{
				System.out.printf("\nError: No es el turno de %s!\n",pl.getName());
			}
		}
	}
}
