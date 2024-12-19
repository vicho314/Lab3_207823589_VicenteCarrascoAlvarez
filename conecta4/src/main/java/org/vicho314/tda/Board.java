package org.vicho314.tda;

import org.vicho314.tda.Piece;
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
}
