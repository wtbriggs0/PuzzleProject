/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc310;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author William Briggs
 */
public class Puzzle {

    public static boolean verbose;

    public static void main(String[] args) {

        //If there is one argument verbose is false, Will work with more than 2 args
        //As long as the first 2 are (dfs,bfs) and second is (v)
        if (args.length != 2) {
            verbose = false;
        } else if (args[1].equalsIgnoreCase("v")) {
            verbose = true;
        }
        //Entry could not be read
        else {
            System.err.println("Entry could not be determined, Please enter bfs or dfs");
            return;
        }

        //Create array of puzzlePieces
        ArrayList<PuzzlePiece> pieces = new ArrayList();

        //1st piece
        pieces.add(new PuzzlePiece(PuzzlePiece.Side.DiamondIN,
                PuzzlePiece.Side.DiamondOUT,
                PuzzlePiece.Side.HeartOUT,
                PuzzlePiece.Side.HeartIN));

        //2nd piece
        pieces.add(new PuzzlePiece(PuzzlePiece.Side.HeartOUT,
                PuzzlePiece.Side.ClubIN,
                PuzzlePiece.Side.ClubIN,
                PuzzlePiece.Side.DiamondOUT));
        //3rd piece
        pieces.add(new PuzzlePiece(PuzzlePiece.Side.SpadeOUT,
                PuzzlePiece.Side.HeartOUT,
                PuzzlePiece.Side.ClubIN,
                PuzzlePiece.Side.SpadeIN));
        //4th piece
        pieces.add(new PuzzlePiece(PuzzlePiece.Side.HeartIN,
                PuzzlePiece.Side.SpadeIN,
                PuzzlePiece.Side.HeartOUT,
                PuzzlePiece.Side.ClubOUT));
        //5th piece
        pieces.add(new PuzzlePiece(PuzzlePiece.Side.DiamondOUT,
                PuzzlePiece.Side.DiamondIN,
                PuzzlePiece.Side.ClubIN,
                PuzzlePiece.Side.ClubOUT));
        //6th piece
        pieces.add(new PuzzlePiece(PuzzlePiece.Side.SpadeOUT,
                PuzzlePiece.Side.ClubIN,
                PuzzlePiece.Side.HeartIN,
                PuzzlePiece.Side.SpadeOUT));
        //7th piece
        pieces.add(new PuzzlePiece(PuzzlePiece.Side.SpadeOUT,
                PuzzlePiece.Side.DiamondIN,
                PuzzlePiece.Side.HeartIN,
                PuzzlePiece.Side.DiamondOUT));
        //8th piece
        pieces.add(new PuzzlePiece(PuzzlePiece.Side.SpadeOUT,
                PuzzlePiece.Side.HeartIN,
                PuzzlePiece.Side.SpadeIN,
                PuzzlePiece.Side.DiamondOUT));
        //9th piece
        pieces.add(new PuzzlePiece(PuzzlePiece.Side.HeartOUT,
                PuzzlePiece.Side.ClubOUT,
                PuzzlePiece.Side.ClubIN,
                PuzzlePiece.Side.DiamondIN));

        Board solution;

        //If there are no arguments catch
        if (args.length < 1) {
            System.err.println("No arguments found!");
            return;
        } else {
            if (args[0].equalsIgnoreCase("bfs")) {
                solution = breadthFirstSearch(pieces); //Breadth First Search
            } else if (args[0].equalsIgnoreCase("dfs")) {
                solution = depthFirstSearch(pieces); //Depth First Search
            } else {
                System.err.println("Invalid entry command");
                return;
            }
        }
        //Will print out solution
        System.out.println("Solution: ");
        System.out.println(solution);
    }

    /**
     * BreadthSearch Method The algorithm uses a queue data structure to store
     * intermediate results as it traverses the graph
     *
     * @param piece
     * @return Solution to Puzzle
     */
    public static Board breadthFirstSearch(ArrayList<PuzzlePiece> piece) {
        Queue<Board> list = new LinkedList();
        Board board = new Board(piece);
        list.add(board);
        if (verbose) {
            System.out.println("Board: ");
            System.out.println(board);
        }
        while (!list.isEmpty()) {
            board = list.poll();
            //Print statements
            if (verbose) {
                System.out.println("Checking Board from front of Queue:");
                System.out.println(board);
            }

            //If board is complete + valid
            if (board.progress == 9 && board.valid()) {
                if (verbose) {
                    System.out.println("Board finished");
                }
                return board;
            }
            //Print statements
            if (verbose) {
                System.out.println("Adding continuations to the Queue");
            }
            //return valid possible boards
            for (Board b : board.getValidContinuations()) {
                if (verbose) {
                    System.out.println(" Possible ways");
                    System.out.println(b);
                }
                list.add(b);
            }
        }
        //Print statements
        if (verbose) {
            System.out.println("No solution could be determined");//No solution
        }
        return null;
    }

    /**
     * DepthFirstSearch Method
     * graph search algorithm which extends the current path as far as possible 
     * before backtracking to the last choice point and trying the next alternative path
     * @param pieces
     * @return Solution
     */
    public static Board depthFirstSearch(ArrayList<PuzzlePiece> pieces) {
        Stack<Board> container = new Stack();
        Board board = new Board(pieces);
        if (verbose) {
            System.out.println("Make board");
            System.out.println(board);
        }
        container.add(board);
        while (!container.isEmpty()) {
            board = container.pop();
            if (verbose) {
                System.out.println("\n\nTrying possibilities:\n");
                System.out.println(board);
            }
            if (board.progress == 9 && board.valid()) {
                if (verbose) {
                    System.out.println("\nSOLUTION HAS BEEN FOUND.");
                }
                return board;
            }
            //Verbose Print statement
            if (verbose) {
                System.out.println("Adding possibilities using stack");
            }
            //Will getValidContinutations for the Board
            for (Board b : board.getValidContinuations()) {
                //Will notify user if an option was found
                if (verbose) {
                    System.out.println("\nFound option");
                    System.out.println(b);
                }
                container.push(b);
            }
        }
        //If a solution cannot be determined
        if (verbose) {
            System.out.println("no solution found");
            return null;
        }
        return null;
    }
}
