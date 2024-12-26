package org.vicho314.tda;

/**
 * Representa una pieza del tablero.
 */
public class Piece {
    /**
     * Color de la pieza.
     */
    private String color;

    /**
     * Constructor
     * @param color String
     */
    public Piece(String color) {
        this.color = color;
    }

    /**
     * getter para campo color
     * @return String color
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
     * Define la representación en String
     * @return color
     */
    public String toString(){
    	return this.color;
    }

    /**
     * Define cómo comparar igualdad en objetos de tipo Piece
     * @param pz Piece
     * @return boolean
     */
    public boolean equals(Piece pz){
        if(pz == null){
            return false;
        }
        else return (pz.getColor() == this.getColor());
    }
}
