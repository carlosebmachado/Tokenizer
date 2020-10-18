package me.carlosmachado.tokenizer.back;

public class Language {

    private int[][] transitionTable;
    private int[] finalStates;
    private int initState;
    private char[] symbols;
    private char[] arithOperators;

    public Language(int[][] transitionTable, int[] finalStates, int initState,
            char[] symbols, char[] arithOperators) {
        this.transitionTable = transitionTable;
        this.finalStates = finalStates;
        this.initState = initState;
        this.symbols = symbols;
        this.arithOperators = arithOperators;
    }

    public int[][] getTransitionTable() {
        return transitionTable;
    }

    public void setTransitionTable(int[][] transitionTable) {
        this.transitionTable = transitionTable;
    }

    public int[] getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(int[] finalStates) {
        this.finalStates = finalStates;
    }

    public int getInitState() {
        return initState;
    }

    public void setInitState(int initState) {
        this.initState = initState;
    }

    public char[] getSymbols() {
        return symbols;
    }

    public void setSymbols(char[] symbols) {
        this.symbols = symbols;
    }

    public char[] getArithOperators() {
        return arithOperators;
    }

    public void setArithOperators(char[] arithOperators) {
        this.arithOperators = arithOperators;
    }

}
