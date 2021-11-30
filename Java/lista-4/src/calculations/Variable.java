package calculations;

import structures.LinkedList;
import structures.Pair;

public class Variable extends Expression{
    public static final LinkedList listOfVariables = new LinkedList();
    private final String varName;

    public Variable(String name, int value) {
        this.varName = name;
        listOfVariables.insert(new Pair(name, value));
    }

    @Override
    public int calculate() {
        Pair keyValPair = listOfVariables.search(varName);
        return keyValPair.getValue();
    }

    @Override
    public String toString() {
        return varName;
    }
}
