import unittest
from ex2_sheet5 import Expression, And, Or, Not, Variable, Boolean, Implication


class MyTestCase(unittest.TestCase):

    def test_general_functionality(self):
        expr_1 = Or(Not(Variable("x")), And(Variable("y"), Boolean(True)))
        expr_2 = And(Variable("y"), Variable("z"))
        self.assertTrue(expr_1.calculate({"x": True, "y": True}))
        self.assertFalse(expr_1.calculate({"x": True, "y": False}))
        self.assertTrue(expr_1.calculate({"x": False, "y": False}))
        self.assertTrue(expr_2.calculate({"y": True, "z": True}))
        self.assertFalse(expr_2.calculate({"y": True, "z": False}))
        self.assertFalse(expr_2.calculate({"y": False, "z": False}))

    def test_tautology(self):
        self.assertFalse(Expression.is_tautology(
            And(Implication(Variable("p"), Variable("q")), Implication(Variable("p"), Variable("q")))))
        self.assertTrue(
            Expression.is_tautology(Implication(Implication(Or(Variable("p"), Variable("q")), Variable("r")),
                                                Implication(Variable("p"), Variable("r")))))
        self.assertFalse(Expression.is_tautology(Or(Not(Variable("x")), And(Variable("y"), Boolean(True)))))
        self.assertFalse(
            Expression.is_tautology(Implication(Implication(Implication(Variable("p"), Variable("q")), Variable("r")),
                                                Implication(Variable("p"), Variable("r")))))

    def test_addition(self):
        self.assertIsInstance(Variable("y") + Variable("x"), Or)

    def test_multiplication(self):
        self.assertIsInstance(Variable("y") * Variable("x"), And)

    def test_simplification(self):
        self.assertIsInstance(And(Variable("p"), Boolean(False)).simplify(), Boolean)
        self.assertIsInstance(Or(Variable("p"), Boolean(False)).simplify(), Variable)


if __name__ == '__main__':
    unittest.main()
