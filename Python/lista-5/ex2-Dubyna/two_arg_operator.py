import expression
from one_arg_operator import Not

class TwoArgOperator(expression.Expression):

    def __init__(self, operand_1, operand_2):
        self.operand_1 = operand_1
        self.operand_2 = operand_2

class And(TwoArgOperator):
    def calculate(self, vars):
        return self.operand_1.calculate(vars) and self.operand_2.calculate(vars)

    def __str__(self):
        return self.operand_1.__str__() + " ^ " + self.operand_2.__str__()

class Or(TwoArgOperator):
    def calculate(self, vars):
        return self.operand_1.calculate(vars) or self.operand_2.calculate(vars)

    def __str__(self):
        return self.operand_1.__str__() + " v " + self.operand_2.__str__()

class Implication(TwoArgOperator):
    def calculate(self, vars):
        return Or(Not(self.operand_1), self.operand_2).calculate(vars)

    def __str__(self):
        return self.operand_1 + " => " + self.operand_2


