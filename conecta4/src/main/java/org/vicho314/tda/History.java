package org.vicho314.tda;

//import org.vicho314.tda.Piece;
//import org.vicho314.tda.Player;
//import java.util.Stack;

public class History{
	public int col;
	public String color;

	public History(int col, String color){
		this.col = col;
		this.color = color;
	}

	public int getCol(){
		return this.col;
	}

	public String getColor(){
		return this.color;
	}

	public void setCol(int col){
		this.col = col;
	}

	public void setColor(String color){
		this.color = color;
	}
}
