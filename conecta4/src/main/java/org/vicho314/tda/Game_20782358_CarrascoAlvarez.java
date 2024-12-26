package org.vicho314.tda;

import java.util.Scanner;
import java.util.Stack;

/**
 * Representa una sesión de juego de Conecta4, haciendo uso de los otros TDAs.
 */
public class Game {
    /**
     * Jugador 1.
     */
	private Player p1;
    /**
     * Jugador 2.
     */
	private Player p2;
    /**
     * Tablero de piezas.
     */
	private Board brd;
    /**
     * Turno actual, -1 si ya terminó el juego.
     */
	private int cturn;
    /**
     * Historial de movimientos.
     */
	private Stack<History> history = new Stack<History>();

    /**
     * Constructor
     * @param p1 Player1
     * @param p2 Player2
     * @param brd Tablero
     * @param cturn Turno actual
     */
	public Game(Player p1, Player p2, Board brd, int cturn){
		this.p1 = p1;
		this.p2 = p2;
		this.brd = brd;
		this.cturn = cturn;
		this.history = new Stack<History>();
	}


    /*
	public Game(){
		this.p1 = null;
		this.p2 = null;
		this.brd = null;
		this.cturn = 0;
		this.history = new Stack<History>();
	}
	*/

    /**
     * getter para Player1
     * @return Player
     */
	public Player getP1() {
		return p1;
	}

    /**
     * Setter para Player1
     * @param p1 Player
     */
	public void setP1(Player p1) {
		this.p1 = p1;
	}

    /**
     * Getter para Player2
     * @return Player
     */
	public Player getP2() {
		return p2;
	}

    /**
     * Setter para Player2
     * @param p2 Player
     */
	public void setP2(Player p2) {
		this.p2 = p2;
	}

    /**
     * Verifica si ambos jugadores no tienen fichas restantes.
     * @return boolean
     */
	public boolean playersNoFichas(){
		return this.p1.noFichas() && this.p2.noFichas();
	}

    /**
     * Getter para Board
     * @return Board
     */
	public Board getBrd() {
		return brd;
	}

    /**
     * Setter para Board
     * @param brd Board
     */
	public void setBrd(Board brd) {
		this.brd = brd;
	}

    /**
     * Getter para turno actual
     * @return cturn
     */
	public int getCturn() {
		return cturn;
	}

    /**
     * Setter para turno actual
     * @param cturn int
     */
	public void setCturn(int cturn) {
		this.cturn = cturn;
	}
/*
	public Stack<History> getHistory() {
		return history;
	}
*/

    /**
     * Setter para history
     * @param history Stack
     */
	public void setHistory(Stack<History> history) {
		this.history = history;
	}

    /**
     * Muestra en pantalla el historial de movimientos.
     */
	public void history(){
		System.out.println("\nHistorial de movimientos:");
		for(History reg: this.history) {
			System.out.printf("(%d, %s)\n",reg.getCol(),reg.getColor());
		}
	}

    /**
     * Verifica si el juego actual es empate.
     * @return boolean
     */
	public boolean esEmpate(){
		return (this.brd.isFull() || this.playersNoFichas());
	}

    /**
     * Retorna el jugador del turno actual.
     * @return Player
     */
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

    /**
     * Actualiza las fichas del jugador actual.
     * @param fichas int
     */
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

    /**
     * Retorna y hace pretty-print del tablero actual
     * @return Board
     */
	public Board boardGetState(){
		Board brd = this.getBrd();
		System.out.println(brd);
		return brd;
	}

    /**
     * Finaliza un juego, seteando cturn=-1,y actualiza estadísticas.
     */
	public void endGame(){
		this.setCturn(-1);
		Piece ganador = this.brd.entregarGanador();
		//Fixme: USAR esEmpate!!
		this.p1.updateStats(ganador);
		this.p2.updateStats(ganador);
		if(ganador.getColor() != null){
			System.out.printf("\nJuego terminado con ganador: %s\n",ganador.getColor());
		}
		else{
			System.out.println("\nJuego terminado con ganador: Empate!\n");
		}
		//Fixme: print ganador!
	}

    /**
     * Cambia (avanza) los turnos en el juego actual.
     */
	public void flipTurn(){
		int cturn = this.getCturn();
		if(cturn == -1) {
			return;
		}
		this.setCturn((this.getCturn() + 1 ) % 2);
	}

    /**
     * Muestra la información de una jugada
     * @param pl Player
     * @param x int
     */
	public void printJugadaHecha(Player pl,int x){
		if(pl != null){
			System.out.format("Jugada: %s, en columna %d",pl.getName(),x);
		}
		else{
			System.out.println("Error: Jugada de jugador nulo!\n");
		}
	}

    /**
     * Realiza una jugada. Controla todo el estado del juego, es decir, finaliza si es necesario.
     * @param pl Player
     * @param x columna
     */
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
					System.out.printf("\nGanador: %s",winner);
					if(winner.getColor() != null || this.esEmpate()){
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

    /**
     * Wrapper para leer la entrada del usuario y usarla en la función original.
     */
	public void realizarMovimientoWrapper(){
		Scanner scan = new Scanner(System.in);
		System.out.printf("\nEscoge una columna donde jugar [0,6]: ");
		int col = scan.nextInt();
		if(col < 0 || col > 6){
			System.out.printf("\nError: columna llena o inexistente!");
			return;
		}
		this.realizarMovimiento(this.getCurrentPlayer(),col);
	}

    /**
     * Lee los datos de un jugador mediante la entrada del usuario.
     * @param num Id asociada al jugador
     * @return Player
     */
	public Player leerJugador(int num){
		Scanner scan = new Scanner(System.in);
		System.out.printf("\nNombre del jugador %d:",num);
		String nombre = scan.nextLine();
		System.out.printf("Nombre del color %d:",num);
		String color = scan.nextLine();
		return new Player(num, nombre, color, 0, 0, 0, 21);
	}

    /**
     * Muestra las estadísticas de los usuarios.
     */
	public void printStats(){
		this.getP1().printStats();
		this.getP2().printStats();
	}

    /**
     * Crea una nueva partida, usando los (mismos) jugadores
     * @param p1 Player1
     * @param p2 Player2
     * @return Game
     */
	public Game newGame(Player p1, Player p2){
		Player pl1 = p1;
		Player pl2 = p2;
		if(p1 == null){
			pl1 = this.leerJugador(0);
		}
		if(p2 == null){
			pl2 = this.leerJugador(1);
		}
		while (pl1.getColor() == pl2.getColor()) {
			System.out.println("\nError: player2 debe tener distinto color a player1.\n");
			pl2 = this.leerJugador(1);
		}
		this.setBrd(new Board());
		this.setP1(pl1);
		this.setP2(pl2);
		this.setCturn(0);
        this.setHistory(new Stack<History>());
		return this;
	}

    /**
     * Muestra las opciones actuales del juego, y el turno actual.
     */
	public void printOpciones(){
		Player pl = this.getCurrentPlayer();
		String name = null;
		if(pl != null){
			name = pl.getName() + "," + pl.getColor();
		}
		System.out.printf("\n# Opciones disponibles: Turno actual (%s)",name);
		System.out.println("\n  1. Crear nuevo juego.");
		System.out.println("\n  2. Mostrar tablero.");
		System.out.println("\n  3. Realizar movimiento.");
		System.out.println("\n  4. Mostrar estadísticas de jugadores.");
		System.out.println("\n  5. Mostrar historial de movimientos.");
		System.out.println("\n  6. Salir.");
	}
}
