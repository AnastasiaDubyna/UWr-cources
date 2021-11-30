from expression import Expression

class OneArgOperator(Expression):
    def __init__(self, operand):
        self.operand = operand



class Not(OneArgOperator):
    def calculate(self, vars):
        return not self.operand.calculate(vars)
    
    def __str__(self):
        return "!" + self.operand.__str__()
