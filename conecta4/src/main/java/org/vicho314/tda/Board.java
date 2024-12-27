package org.vicho314.tda;

import java.util.LinkedList;
import org.vicho314.tda.*;
//import java.util.ArrayList;

/**
 * Representa un tablero de conecta4, 7x6.
 */
public class Board {
    /**
     * Arreglo de piezas del tablero.
     */
    private Piece[][] piezas;

    /*
    public Board(Piece[][] piezas) {
        this.piezas = piezas;
    }
    */

    /**
     * Constructor, retorna un tablero de 7x6, fichas vacías.
     */
    public Board() {
        Piece[][] piezas = new Piece[7][6];
        for(int i = 0; i < 7;++i){
            for(int j = 0; j < 6;++j){
                piezas[i][j]=new Piece(null);
            }
        }
        this.piezas = piezas;
    }
    /*
    public Piece[][] getPiezas() {
        return piezas;
    }
    */

    /**
     * Getter para columna X.
     * @param x int
     * @return Piece[] columna
     */
    public Piece[] getCol(int x){
        return this.piezas[x];
    }

    /**
     * Getter para fila Y.
     * @param y int
     * @return Piece[] fila
     */
    public Piece[] getFila(int y){
        Piece[] piezas = new Piece[7];
        for(int i = 0; i < 7;++i){
            piezas[i] = this.piezas[i][y];
        }
        return piezas;
    }

    /**
     * Retorna la diagonal ascendente del tablero, partiendo desde (X,Y).
     * @param x int
     * @param y int
     * @return Piece[] diagonal
     */
    public Piece[] getDiagAscen(int x,int y){
        LinkedList<Piece> piezas = new LinkedList<Piece>();
        for(int i = 0; i < 7;++i){
            if(this.inBounds(x+i,y+i))
                piezas.add(this.piezas[x+i][y+i]);
            else{
                break;
            }
        }
        return piezas.toArray(new Piece[0]);
    }

    /**
     * Retorna la diagonal descendente del tablero, partiendo desde (X,Y).
     * @param x int
     * @param y int
     * @return Piece[] diagonal
     */
    public Piece[] getDiagDescen(int x,int y){
        LinkedList<Piece> piezas = new LinkedList<Piece>();
        for(int i = 0; i < 7;++i){
            if(this.inBounds(x-i,y-i))
                piezas.add(this.piezas[x-i][y-i]);
            else{
                break;
            }
        }
        return piezas.toArray(new Piece[0]);
    }
    /*
    public void setPiezas(Piece[][] piezas) {
        this.piezas = piezas;
    }
    */

    /**
     * Verifica si (X,Y) está dentro de los límites.
     * @param x int
     * @param y int
     * @return boolean
     */
    public boolean inBounds(int x, int y){
        boolean fila;
        boolean columna;
        columna = (x < 7) && (x >= 0);
        fila = (y < 6) && (y >= 0);
        return (fila && columna);
    }
    /*
    public void setPiezasXY(Piece pieza, int x, int y) {
        if(this.inBounds(x, y)) {
            this.piezas[x][y] = pieza;
        }
    }
    */

    /**
     * Verifica si el tablero está no lleno, o sea, si aún se puede jugar.
     * @return boolean
     */
    public boolean canPlay(){
        for(int i = 0; i < 7; ++i){
            for(int j = 0; j < 6; ++j){
                if(this.piezas[i][j].getColor() == null){
                    return true;
                }
            }
        }
        return false;
    }
    /*
    public boolean canPlay(int x){
        for(int j = 0; j < 6; ++j){
            if(this.piezas[x][j].getColor() == null){
                    return true;
            }
        }
        return false;
    }
    */

    /**
     * Verifica si el tablero está lleno.
     * @return boolean
     */
    public boolean isFull(){
    	return !this.canPlay();
    }

    //asume que la columna tiene pos. vacías

    /**
     * Realiza una jugada en la columna X, reemplazando la primera ficha vacía por la pieza.
     * @param pieza Piece
     * @param x int
     * @return true si se hizo, false en caso contrario
     */
    public boolean jugarFicha(Piece pieza, int x){
        if(x < 0 || x > 6){
            System.out.println("Error: X fuera de límites!!\n");
            return false;
        }
        else {
            for (int i = 0; i < 6; ++i) {
                if (this.piezas[x][i].getColor() == null) {
                    this.piezas[x][i] = pieza;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Wrapper, verifica si hay 4 fichas consecutivas iguales en una lista de piezas.
     * @param piezas Piece[]
     * @return Piece ganadora, vacía != null
     */
    public Piece checkWin(Piece[] piezas){
        int counter = 0;
        Piece currentPiece = piezas[0];
        for (Piece pieza : piezas) {
            if (pieza.equals(currentPiece)) {
                counter += 1;
            } else {
                counter = 0;
                currentPiece = pieza;
            }
            if (counter == 4) {
                return currentPiece;
            }
        }
        return new Piece(null);
    }

    /**
     * Verifica si hay un ganador vertical, usando checkWin().
     * @return Piece ganadora
     */
    public Piece verticalWin(){
        Piece resultado;
        for(int i = 0; i < 7;++i){
            resultado = checkWin(this.getCol(i));
            if(resultado.getColor() != null){
                return resultado;
            }
        }
        return new Piece(null);
    }
    /**
     * Verifica si hay un ganador horizontal, usando checkWin().
     * @return Piece ganadora
     */
    public Piece horizontalWin(){
        Piece resultado;
        for(int i = 0; i < 6;++i){
            resultado = checkWin(this.getFila(i));
            if(resultado.getColor() != null){
                return resultado;
            }
        }
        return new Piece(null);
    }
    /**
     * Verifica si hay un ganador diagonal ascendiente, usando checkWin().
     * @return Piece ganadora
     */
    public Piece diagAscenWin(){
    	int[][] puntos = {{0,0},{1,0},{2,0},{3,0},{0,1},{0,2}};
        Piece resultado;
        for(int i = 0; i < 6;++i){
            resultado = checkWin(this.getDiagAscen(puntos[i][0],puntos[i][1]));
            if(resultado.getColor() != null){
                return resultado;
            }
        }
        return new Piece(null);
    }
    /**
     * Verifica si hay un ganador diagonal descendiente, usando checkWin().
     * @return Piece ganadora
     */
    public Piece diagDescenWin(){
    	int[][] puntos = {{0,5},{1,5},{2,5},{3,5},{0,4},{0,3}};
        Piece resultado;
        for(int i = 0; i < 6;++i){
            resultado = checkWin(this.getDiagDescen(puntos[i][0],puntos[i][1]));
            if(resultado.getColor() != null){
                return resultado;
            }
        }
        return new Piece(null);
    }
    /**
     * Verifica si hay un ganador diagonal, usando checkWin().
     * @return Piece ganadora
     */
    public Piece diagonalWin(){
    	Piece resultado;
    	resultado = this.diagDescenWin();
    	if(resultado.getColor() != null){
    		return resultado;
    	}
    	else{
    		return this.diagAscenWin();
    	}
    }

    /**
     * Verifica si hay un ganador, en todas las posibles direcciones.
     * @return Piece ganadora
     */
    public Piece entregarGanador(){
    	Piece resultado = new Piece(null);
    	resultado = this.verticalWin();
    	if(resultado.getColor() != null) return resultado;
    	resultado = this.horizontalWin();
    	if(resultado.getColor() != null) return resultado;
    	resultado = this.diagonalWin();
    	return resultado;
    }

    /**
     * Define la representación en String del tablero, leyendo todas las piezas en él.
     * @return String
     */
    public String toString(){
    	//Piece[] piezas;
    	String res="[";
    	for(int i = 0; i < 6; ++i){
    	    for(Piece pz : this.getFila(i)){
    	    	res+=String.format(" %s,",pz);
    	    }
            res += "\b]\n";
    	}
    	return res;
    }    
}
