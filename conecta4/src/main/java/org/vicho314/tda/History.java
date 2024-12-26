package org.vicho314.tda;

//import org.vicho314.tda.Piece;
//import org.vicho314.tda.Player;
//import java.util.Stack;

/**
 * Representa una entrada del historial.
 */
public class History{
	/**
	 * Columna
	 */
	public int col;
	/**
	 * Color
	 */
	public String color;

	/**
	 * Constructor
	 * @param col Columna
	 * @param color Color
	 */
	public History(int col, String color){
		this.col = col;
		this.color = color;
	}

	/**
	 * Getter para col
	 * @return Columna
	 */
	public int getCol(){
		return this.col;
	}

	/**
	 * Getter para color
	 * @return String
	 */
	public String getColor(){
		return this.color;
	}
	/*
	public void setCol(int col){
		this.col = col;
	}
	*/

	/*
	public void setColor(String color){
		this.color = color;
	}
	*/
}
