import expression

class Operand(expression.Expression):
    def __init__(self, value):
        self.value = value

    def calculate(self, vars):
        return self.value

    def __str__(self):
        return str(self.value) 


class Variable(Operand):
    def calculate(self, vars):
        return vars[self.value]

class Boolean(Operand):
    def __init__(self, value):
        if not isinstance(value, bool):
            raise TypeError("Boolean value is true or false")
        self.value = value 