package tests;

import calculations.*;
import calculations.Number;
import structures.LinkedList;
import structures.Pair;

public class Test {

    public static void main(String[] args) throws Exception {
        Expression yVar = new Variable("y", 7);
        Expression xVar = new Variable("x", 2);

        Expression expr1 = new Addition(new Number(3), new Number(5));
        Expression expr2 = new Multiplication(new SignChange(new Subtraction(new Number(2), xVar)), yVar);
        Expression expr3 =
                new Division(
                        new Subtraction(
                                new Multiplication(
                                        new Number(3),
                                        new Number(11)),
                                new Number(1)),
                        new Addition(yVar,
                                new Number(5)));
        Expression expr4 =
                new Minimum(
                        new Multiplication(
                                new Addition(
                                        xVar,
                                        new Number(13)),
                                xVar),
                        new Modulo(
                                new Subtraction(
                                        new Number(1),
                                        xVar),
                                new Number(2)));
        Expression expr5 =
                new LessThan(
                        new Addition(
                                new Exponentiation(
                                        new Number(2),
                                        new Number(5)),
                                new Multiplication(
                                        xVar,
                                        new DiscreteLogarithm(
                                                new Number(2),
                                                yVar))),
                        new Number(20));

        //Poprawność wyrażeń i toString()
        System.out.println(expr1 + " = " + expr1.calculate());
        System.out.println(expr2 + " = " + expr2.calculate());
        System.out.println(expr3 + " = " + expr3.calculate());
        System.out.println(expr4 + " = " + expr4.calculate());
        System.out.println(expr5 + " = " + expr5.calculate());

        //Poprawność LinkedList
        Pair pairOne = new Pair("one", 1);
        Pair pairTwo = new Pair("two", 2);
        Pair pairThree = new Pair("three", 3);
        LinkedList linkedL = new LinkedList();
        linkedL.insert(pairOne);
        linkedL.insert(pairTwo);
        linkedL.insert(pairThree);
        System.out.println(linkedL.count());
        linkedL.remove("three");
        System.out.println(linkedL.count());
        linkedL.removeAll();
        System.out.println(linkedL.count());

        //Poprawność equals
        Expression expr6 = new Number(5);
        Expression expr7 = new Addition(new Number(2), new Number(3));
        Expression expr8 = new Exponentiation(new Number(2), new Number(4));
        System.out.println(expr6.equals(expr7));
        System.out.println(expr6.equals(expr8));




    }
}
