package me.carlosmachado.tokenizer.back;

import java.util.List;
import java.util.ArrayList;

public class Automata {

    public static final String VALID_SENTENCE = "Sentença válida";
    public static final String ARITHMETIC_OPERATOR = "Operador aritimético";
    public static final String ERR_INVALID_SENTENCE = "ERRO: Sentença inválida";
    public static final String ERR_INVALID_SYMBOLS = "ERRO: Símbolo(s) inválido(s)";

    private Language language;

    public Automata(Language lang) {
        this.language = lang;
    }

    public List<Response> parse(String inputText) {
        List<Response> tokens = new ArrayList<>();
        int curIndex = 0;
        // primeiro while para quando o index chega ao final
        while (curIndex < inputText.length()) {
            // seta o estado inicial e inicializa a variavel que guardara o
            // token atual
            int state = language.getInitState();
            String curWord = "";
            /* enquanto: 
             *     - estiver no limite do index
             *     - não for espaço
             *     - não for tab
             *     - não for quebra de linha
             *     - não for carriege return
             *     - e finalmente não for operador aritmético
             */
            while (curIndex < inputText.length()
                    && inputText.charAt(curIndex) != ' '
                    && inputText.charAt(curIndex) != '\t'
                    && inputText.charAt(curIndex) != '\r'
                    && inputText.charAt(curIndex) != '\n'
                    && !isArithOperator(inputText.charAt(curIndex))) {
                // add o novo char para o token atual
                curWord += inputText.charAt(curIndex);
                // atualiza o estado
                state = language.getTransitionTable()[state][getCharCode(inputText.charAt(curIndex))];
                // vai para o próximo index
                curIndex++;
            }
            // verifica se o token não é vazio
            // (acontece em caracteres especiais seguidos)
            if (!curWord.equals("")) {
                // verifica se o estado é final
                if (isFinalState(state)) {
                    // se for add como sentença válida
                    tokens.add(new Response(curWord, VALID_SENTENCE));
                } else {
                    // senão, verifica se o primeiro é um símbolo válido
                    if (isSymbol(curWord.charAt(0))) {
                        // se for add como uma sentença inválida
                        tokens.add(new Response(curWord, ERR_INVALID_SENTENCE));
                    } else {
                        // senão, add como um símbolo inválido
                        tokens.add(new Response(curWord, ERR_INVALID_SYMBOLS));
                    }
                }
            }
            // finalmente reconhece se o caractere atual é um operador
            if (curIndex < inputText.length()
                    && isArithOperator(inputText.charAt(curIndex))) {
                tokens.add(new Response(Character.toString(inputText.charAt(curIndex)), ARITHMETIC_OPERATOR));
            }
            curIndex++;
        }

        return tokens;
    }

    private int getCharCode(char t) {
        int i = 1;
        for (char c : language.getSymbols()) {
            if (c == t) {
                return i;
            }
            i++;
        }
        return i;
    }

    private boolean isArithOperator(char t) {
        for (char c : language.getArithOperators()) {
            if (c == t) {
                return true;
            }
        }
        return false;
    }

    private boolean isSymbol(char t) {
        for (char c : language.getSymbols()) {
            if (c == t) {
                return true;
            }
        }
        return false;
    }

    private boolean isFinalState(int s) {
        return language.getFinalStates()[s] == 1;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

}
