package org.vicho314.tda;

import org.vicho314.tda.Piece;

import java.util.LinkedList;
//import java.util.ArrayList;

public class Board {
    private Piece[][] piezas;

    public Board(Piece[][] piezas) {
        this.piezas = piezas;
    }

    public Board() {
        this.piezas = new Piece[7][6];
    }

    public Piece[][] getPiezas() {
        return piezas;
    }

    public Piece[] getCol(int x){
        return this.piezas[x];
    }

    public Piece[] getFila(int y){
        Piece[] piezas = new Piece[7];
        for(int i = 0; i < 7;++i){
            piezas[i] = this.piezas[i][y];
        }
        return piezas;
    }

    public Piece[] getDiagAscen(int x,int y){
        LinkedList<Piece> piezas = new LinkedList<Piece>;
        for(int i = 0; i < 7;++i){
            if(this.inBounds(x+i,y+i))
                piezas.add(this.piezas[x+i][y+i]);
            else{
                break;
            }
        }
        return (Piece[]) piezas.toArray();
    }

    public Piece[] getDiagDescen(int x,int y){
        LinkedList<Piece> piezas = new LinkedList<Piece>;
        for(int i = 0; i < 7;++i){
            if(this.inBounds(x+i,y+i))
                piezas.add(this.piezas[x-i][y-i]);
            else{
                break;
            }
        }
        return (Piece[]) piezas.toArray();
    }

    public void setPiezas(Piece[][] piezas) {
        this.piezas = piezas;
    }

    public boolean inBounds(int x, int y){
        boolean fila;
        boolean columna;
        columna = (x < 7) && (x >= 0);
        fila = (y < 6) && (6 >= 0);
        return (fila && columna);
    }

    public void setPiezasXY(Piece pieza, int x, int y) {
        if(this.inBounds(x, y)) {
            this.piezas[x][y] = pieza;
        }
    }

    public boolean canPlay(){
        for(int i = 0; i < 7; ++i){
            for(int j = 0; j < 6; ++j){
                if(this.piezas[i][j] == null){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean canPlay(int x){
        int i = x;
        for(int j = 0; j < 6; ++j){
            if(this.piezas[i][j] == null){
                    return true;
            }
        }
        return false;
    }

    public boolean isFull(){
    	return !this.canPlay();
    }

    //asume que la columna tiene pos. vacías
    public boolean jugarFicha(Piece pieza, int x){
        for(int i = 0; i < 6; ++i){
            if(this.piezas == null){
                this.piezas[x][i] = pieza;
                return true;
            }
        }
        return false;
    }

    public Piece checkWin(Piece[] piezas){
        int counter = 0;
        Piece currentPiece = piezas[0];
        for(int i = 0; i < piezas.length(); ++i){
            if(currentPiece == piezas[i]){
                counter+=1;
            }
            else{
                counter=0;
                currentPiece = piezas[i];
            }
            if(counter == 4){
                return currentPiece;
            }
        }
        return null;
    }

    public Piece verticalWin(){
        Piece resultado = null;
        for(int i = 0; i < 7;++i){
            resultado = checkWin(this.getCol(i));
            if(resultado != null){
                return resultado;
            }
        }
        return null;
    }

    public Piece horizontalWin(){
        Piece resultado = null;
        for(int i = 0; i < 6;++i){
            resultado = checkWin(this.getFila(i));
            if(resultado != null){
                return resultado;
            }
        }
        return null;
    }

    public Piece diagAscenWin(){
    	int[6][2] puntos = {{0,0},{1,0},{2,0},{3,0},{0,1},{0,2}};
        Piece resultado = null;
        for(int i = 0; i < 6;++i){
            resultado = checkWin(this.getDiagAscen(puntos[i][0],puntos[i][1]));
            if(resultado != null){
                return resultado;
            }
        }
        return null;
    }

    public Piece diagDescenWin(){
    	int[6][2] puntos = {{0,5},{1,5},{2,5},{3,5},{0,4},{0,3}};
        Piece resultado = null;
        for(int i = 0; i < 6;++i){
            resultado = checkWin(this.getDiagDescen(puntos[i][0],puntos[i][1]));
            if(resultado != null){
                return resultado;
            }
        }
        return null;
    }

    public Piece diagonalWin(){
    	Piece resultado = null;
    	resultado = this.diagDescenWin();
    	if(resultado != null){
    		return resultado;
    	}
    	else{
    		return this.diagAscenWin();
    	}
    }

    public Piece entregarGanador(){
    	Piece resultado = null;
    	resultado = this.verticalWin();
    	if(resultado != null) return resultado;
    	resultado = this.horizontalWin();
    	if(resultado != null) return resultado;
    	resultado = this.diagonalWin();
    	if(resultado != null) return resultado;
    	return resultado;
    }

    public String toString(){
    	Piece[] piezas;
    	String res="[";
    	for(int i = 0; i < 6; ++i){
    	    for(Piece pz : this.getFila(i)){
    	    	res+=System.out.printf("%$2s,",pz);
    	    }
    	    res+="\b]\n"
    	}
    	return res;
    }    
}
