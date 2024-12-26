package org.vicho314.tda;

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

    public Piece[] getDiagDescen(int x,int y){
        LinkedList<Piece> piezas = new LinkedList<Piece>();
        for(int i = 0; i < 7;++i){
            if(this.inBounds(x+i,y+i))
                piezas.add(this.piezas[x-i][y-i]);
            else{
                break;
            }
        }
        return piezas.toArray(new Piece[0]);
    }

    public void setPiezas(Piece[][] piezas) {
        this.piezas = piezas;
    }

    public boolean inBounds(int x, int y){
        boolean fila;
        boolean columna;
        columna = (x < 7) && (x >= 0);
        fila = (y < 6) && (y >= 0);
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
        for(int j = 0; j < 6; ++j){
            if(this.piezas[x][j] == null){
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
        if(x < 0 || x > 6){
            System.out.println("Error: X fuera de límites!!\n");
            return false;
        }
        else {
            for (int i = 0; i < 6; ++i) {
                if (this.piezas[x][i] == null) {
                    this.piezas[x][i] = pieza;
                    return true;
                }
            }
        }
        return false;
    }

    public Piece checkWin(Piece[] piezas){
        int counter = 0;
        Piece currentPiece = piezas[0];
        for (Piece pieza : piezas) {
            if (currentPiece == pieza) {
                counter += 1;
            } else {
                counter = 0;
                currentPiece = pieza;
            }
            if (counter == 4) {
                return currentPiece;
            }
        }
        return null;
    }

    public Piece verticalWin(){
        Piece resultado;
        for(int i = 0; i < 7;++i){
            resultado = checkWin(this.getCol(i));
            if(resultado != null){
                return resultado;
            }
        }
        return null;
    }

    public Piece horizontalWin(){
        Piece resultado;
        for(int i = 0; i < 6;++i){
            resultado = checkWin(this.getFila(i));
            if(resultado != null){
                return resultado;
            }
        }
        return null;
    }

    public Piece diagAscenWin(){
    	int[][] puntos = {{0,0},{1,0},{2,0},{3,0},{0,1},{0,2}};
        Piece resultado;
        for(int i = 0; i < 6;++i){
            resultado = checkWin(this.getDiagAscen(puntos[i][0],puntos[i][1]));
            if(resultado != null){
                return resultado;
            }
        }
        return null;
    }

    public Piece diagDescenWin(){
    	int[][] puntos = {{0,5},{1,5},{2,5},{3,5},{0,4},{0,3}};
        Piece resultado;
        for(int i = 0; i < 6;++i){
            resultado = checkWin(this.getDiagDescen(puntos[i][0],puntos[i][1]));
            if(resultado != null){
                return resultado;
            }
        }
        return null;
    }

    public Piece diagonalWin(){
    	Piece resultado;
    	resultado = this.diagDescenWin();
    	if(resultado != null){
    		return resultado;
    	}
    	else{
    		return this.diagAscenWin();
    	}
    }

    public Piece entregarGanador(){
    	Piece resultado;
    	resultado = this.verticalWin();
    	if(resultado != null) return resultado;
    	resultado = this.horizontalWin();
    	if(resultado != null) return resultado;
    	resultado = this.diagonalWin();
    	return resultado;
    }

    public String toString(){
    	Piece[] piezas;
    	String res="[";
    	for(int i = 0; i < 6; ++i){
    	    for(Piece pz : this.getFila(i)){
    	    	res+=System.out.printf("%$2s,",pz);
    	    }
            res += "\b]\n";
    	}
    	return res;
    }    
}
