package org.vicho314.tda;

import org.vicho314.tda.Board;
import org.vicho314.tda.Player;
import org.vicho314.tda.History;
import java.util.Stack;

public class Game {
	private Player p1;
	private Player p2;
	private Board brd;
	private int cturn;
	private Stack history = new Stack<History>;

	public Game(Player p1, Player p2, Board brd, int cturn){
		this.p1 = p1;
		this.p2 = p2;
		this.brd = brd;
		this.cturn = cturn;
		this.history = new Stack<History>;
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
		this.history.print();
	}

	public boolean esEmpate(){
		return (this.brd.isFull() || this.playersNoFichas());
	}
}
