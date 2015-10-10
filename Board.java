/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc310;

import cosc310.Board;
import java.util.ArrayList;

/**
 *
 * @author William Briggs
 */
public class Board {

    PuzzlePiece[] configure;
    ArrayList<PuzzlePiece> free;
    int progress;

    //Will create board that holds 9 puzzlePieces
    public Board(ArrayList<PuzzlePiece> pieces) {
        configure = new PuzzlePiece[9];
        free = pieces;
        progress = 0;
    }

    //Will attempt to add a piece to the base
    public Board(Board base, PuzzlePiece add) {
        configure = new PuzzlePiece[9];
        for (int i = 0; i < base.progress; i++) {
            configure[i] = base.configure[i].copy();
        }
        configure[base.progress] = add;
        free = new ArrayList<PuzzlePiece>();
        for (PuzzlePiece p : base.free) {
            if (!p.equals(add)) {
                free.add(p);
            }
        }
        progress = base.progress + 1;

    }

    /**
     * GetValidContinuations method Will Only return the Board that has a valid
     * solution
     * @return
     */
    public ArrayList<Board> getValidContinuations() {
        ArrayList<Board> continuations = new ArrayList<Board>();
        for (PuzzlePiece pieces : free) {
            //Notify rotations
            if (Puzzle.verbose) {
                System.out.println("\nTesting roations of Piece: " + pieces);
            }
            //Produce combinations of puzzlepieces
            for (PuzzlePiece orientation : pieces.getCombinations()) {
                if (Puzzle.verbose) {
                    System.out.print("\nTesting orientation: " + orientation);
                }
                //If valid solution found let user know
                if (Valid(orientation)) {
                    if (Puzzle.verbose) 
                        System.out.println("Valid orientation found");
                        continuations.add(new Board(this, orientation));
                }
            }
        }
        if (Puzzle.verbose) {
            System.out.println("Continuations: " + continuations.size());
        }
        return continuations;

    }

    /**
     * Valid Method
     * Will return true if the pieces are compatible with each other
     * @param p
     * @return 
     */
    private boolean Valid(PuzzlePiece p) {
        switch (progress) {
            case 0:
                return true;
            case 1:
                return configure[0].fitsDown(p);
            case 2:
                return configure[1].fitsDown(p);
            case 3:
                return configure[0].fitsRight(p);
            case 4:
                return configure[1].fitsRight(p) && configure[3].fitsDown(p);
            case 5:
                return configure[2].fitsRight(p) && configure[4].fitsDown(p);
            case 6:
                return configure[3].fitsRight(p);
            case 7:
                return configure[4].fitsRight(p) && configure[6].fitsDown(p);
            case 8:
                return configure[5].fitsRight(p) && configure[7].fitsDown(p);
            default:
                return false;
        }
    }

    /**
     * ToString method
     * Will return the Board in a 3x3 grid
     * 
     * @return 
     */
    public String toString() {
        if (progress == 0) {
            return "no board";
        }
        String s = "A D G\nB E H\nC F I\n";
        char letter = 'A';
        for (int i = 0; i < progress; i++, letter++) {
            s += "\n" + letter + ": " + configure[i];
        }
        return s;

    }

    /**
     * Valid Method
     * Will return true if there are 6 tabs facing outwards
     * By rule there should also be 6 inward tabs if there are 6 outward tabs
     * @return 
     */
    public boolean valid() {
        //Go through sides
        //Count number of tabs
        int tabs = 0;
        PuzzlePiece.Side[] outsides = {
            configure[0].getTop(),
            configure[0].getLeft(),
            configure[1].getLeft(),
            configure[2].getLeft(),
            configure[2].getBottom(),
            configure[3].getTop(),
            configure[5].getBottom(),
            configure[6].getTop(),
            configure[6].getRight(),
            configure[7].getRight(),
            configure[8].getRight(),
            configure[8].getBottom()
        };
        //Will read if any of the Sides is an OUT tab
        for (PuzzlePiece.Side s : outsides) {
            if (s == PuzzlePiece.Side.ClubOUT || s == PuzzlePiece.Side.DiamondOUT || s == PuzzlePiece.Side.HeartOUT || s == PuzzlePiece.Side.SpadeOUT)
                tabs++;
        }
        return tabs == 6;
    }
}
