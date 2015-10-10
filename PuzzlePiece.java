/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc310;

/**
 *
 * @ William Briggs
 */
public class PuzzlePiece {
    //Uses enum to represent the possible Sides the PuzzlePieces could have
    public enum Side {

        HeartIN, HeartOUT, DiamondIN, DiamondOUT, SpadeIN, SpadeOUT, ClubIN, ClubOUT;
    }
    private Side top, bottom, left, right;

    //Constructor sets Sides of puzzle
    
    public PuzzlePiece(Side up, Side r, Side down, Side l) {
        top = up;
        bottom = down;
        left = l;
        right = r;

    }

    /**
     * Will rotate the piece Right once
     * original:    (top-right-bottom-left)
     * new:         (left-top-right-bottom)
     */
    public void turnPieceRight() {
        Side temp = top;
        top = left;
        left = bottom;
        bottom = right;
        right = temp;
    }

    /**
     * Simple way to rotate the Piece several times
     * Cannot add a number greater than 3
     * @param times 
     */
    public void turnPieceRight(int times) {
        for (int i = 0; i < times; i++) {
            turnPieceRight();
        }
    }

    //Will flip the piece over sideways
    public void flip() {
        Side temp = right;
        right = left;
        left = temp;
    }

    /**flipUp Method
     * Will flip the piece over upwards
     */
    public void flipUp() {
        Side temp = top;
        top = bottom;
        bottom = temp;

    }

    /**
     * Copy method
     * Will return the copy of the puzzlePiece
     * @return 
     */
    public PuzzlePiece copy() {
        return new PuzzlePiece(top, right, bottom, left);
    }

    /**
     * Will check to see if the puzzle fits 
     * Will check each slot
     * @param p
     * @return 
     */
    public boolean fitsRight(PuzzlePiece p) {
        switch (p.left) {
            case HeartIN:
                return right == Side.HeartOUT;
            case HeartOUT:
                return right == Side.HeartIN;
            case DiamondIN:
                return right == Side.DiamondOUT;
            case DiamondOUT:
                return right == Side.DiamondIN;
            case SpadeIN:
                return right == Side.SpadeOUT;
            case SpadeOUT:
                return right == Side.SpadeIN;
            case ClubIN:
                return right == Side.ClubOUT;
            case ClubOUT:
                return right == Side.ClubIN;
            default:
                return false;
        }

    }

    /**
     * Will check to see if the piece is compatible downwards
     * return boolean
     * @param p
     * @return 
     */
    public boolean fitsDown(PuzzlePiece p) {
        switch (p.top) {
            case HeartIN:
                return bottom == Side.HeartOUT;
            case HeartOUT:
                return bottom == Side.HeartIN;
            case DiamondIN:
                return bottom == Side.DiamondOUT;
            case DiamondOUT:
                return bottom == Side.DiamondIN;
            case SpadeIN:
                return bottom == Side.SpadeOUT;
            case SpadeOUT:
                return bottom == Side.SpadeIN;
            case ClubIN:
                return bottom == Side.ClubOUT;
            case ClubOUT:
                return bottom == Side.ClubIN;
            default:
                return false;

        }
    }

    /**
     * Will check to see if the puzzle is compatible at the left side
     * return boolean
     * @param p
     * @return 
     */
    public boolean fitsLeft(PuzzlePiece p) {

        switch (p.right) {
            case HeartIN:
                return left == Side.HeartOUT;
            case HeartOUT:
                return left == Side.HeartIN;
            case DiamondIN:
                return left == Side.DiamondOUT;
            case DiamondOUT:
                return left == Side.DiamondIN;
            case SpadeIN:
                return left == Side.SpadeOUT;
            case SpadeOUT:
                return left == Side.SpadeIN;
            case ClubIN:
                return left == Side.ClubOUT;
            case ClubOUT:
                return left == Side.ClubIN;
            default:
                return false;
        }
    }

    /**
     * Will return boolean
     * Checks to see if the piece is compatible on the top side
     * @param p
     * @return 
     */
    public boolean fitsUp(PuzzlePiece p) {

        switch (p.bottom) {
            case HeartIN:
                return top == Side.HeartOUT;
            case HeartOUT:
                return top == Side.HeartIN;
            case DiamondIN:
                return top == Side.DiamondOUT;
            case DiamondOUT:
                return top == Side.DiamondIN;
            case SpadeIN:
                return top == Side.SpadeOUT;
            case SpadeOUT:
                return top == Side.SpadeIN;
            case ClubIN:
                return top == Side.ClubOUT;
            case ClubOUT:
                return top == Side.ClubIN;
            default:
                return false;
        }

    }

    /**
     * Will return all possible combinations of the puzzle piece
     * 8 Different possible orientations
     * @return 
     */
    public PuzzlePiece[] getCombinations() {
        PuzzlePiece[] combinations = new PuzzlePiece[8];
        for (int i = 0; i < 8; i++) {
            combinations[i] = this.copy();
        }

        //Will create a PuzzlePiece array that contains all possible rotations
        
        combinations[1].turnPieceRight();       //Original Piece
        combinations[2].turnPieceRight(2);      
        combinations[3].turnPieceRight(3);
        combinations[4].flip();
        combinations[5].flip();
        combinations[5].turnPieceRight();
        combinations[6].flip();
        combinations[6].turnPieceRight(2);
        combinations[7].flip();
        combinations[7].turnPieceRight(3);
        
        
        return combinations;

    }

    /**
     * Check to see if the puzzle is equal
     * returns boolean
     * @param p
     * @return 
     */
    public boolean equals(PuzzlePiece p) {
        for (PuzzlePiece orientation : p.getCombinations()) {
            if (orientation.left == left && orientation.right == right && orientation.bottom == bottom && orientation.top == top) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * toString method
     * Will print out the sides of the puzzle pieces
     * (top-right-bottom-left)
     * @return 
     */
    public String toString(){
        String out = "";
        switch(top){
            case HeartIN:
                out+="HeartIN";
                break;
            case HeartOUT:
                out+="HeartOUT";
                break;
            case ClubIN:
                out+="ClubIN";
                break;
            case ClubOUT:
                out+="ClubOUT";
                break;
            case DiamondIN:
                out+="DiamondIN";
                break;
            case DiamondOUT:
                out+="DiamondOUT";
                break;
            case SpadeIN:
                out+="SpadeIN";
                break;
            case SpadeOUT:
                out+="SpadeOUT";
                break;
            default:
                return "Error: Broken piece";
        }
        
        out+=",";
        switch(right){
            case HeartIN:
                out+="HeartIN";
                break;
            case HeartOUT:
                out+="HeartOUT";
                break;
            case ClubIN:
                out+="ClubIN";
                break;
            case ClubOUT:
                out+="ClubOUT";
                break;
            case DiamondIN:
                out+="DiamondIN";
                break;
            case DiamondOUT:
                out+="DiamondOUT";
                break;
            case SpadeIN:
                out+="SpadeIN";
                break;
            case SpadeOUT:
                out+="SpadeOUT";
                break;
            default:
                return "Error: Broken piece";   
        }
        out +=",";
        switch(bottom){
            case HeartIN:
                out+="HeartIN";
                break;
            case HeartOUT:
                out+="HeartOUT";
                break;
            case ClubIN:
                out+="ClubIN";
                break;
            case ClubOUT:
                out+="ClubOUT";
                break;
            case DiamondIN:
                out+="DiamondIN";
                break;
            case DiamondOUT:
                out+="DiamondOUT";
                break;
            case SpadeIN:
                out+="SpadeIN";
                break;
            case SpadeOUT:
                out+="SpadeOUT";
                break;
            default:
                return "Error: Broken piece";
        }
    
        out+= ",";
    
        switch(left){
            case HeartIN:
                out+="HeartIN";
                break;
            case HeartOUT:
                out+="HeartOUT";
                break;
            case ClubIN:
                out+="ClubIN";
                break;
            case ClubOUT:
                out+="ClubOUT";
                break;
            case DiamondIN:
                out+="DiamondIN";
                break;
            case DiamondOUT:
                out+="DiamondOUT";
                break;
            case SpadeIN:
                out+="SpadeIN";
                break;
            case SpadeOUT:
                out+="SpadeOUT";
                break;
            default:
                return "Error: Broken piece";
        }
    
    out +=" =[TOP,RIGHT,BOTTOM,LEFT] ";
    return out;
    
    }
    /**
     * getTop Method
     * Will return the top Side
     * @return top
     */
    public Side getTop() {
        return top;
    }

    /**
     * getBottom Method
     * Will return the Bottom Side
     * @return bottom
     */
    public Side getBottom() {
        return bottom;
    }

    /**
     * getLeft Method
     * Will return the Left Side
     * @return left
     */
    public Side getLeft() {
        return left;
    }
    /**
     * getRight Method
     * Will return the Right Side
     * @return right
     */
    public Side getRight() {
        return right;
    }
}
