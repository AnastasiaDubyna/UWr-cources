from abc import abstractmethod
from sympy.utilities.iterables import multiset_permutations

"""
Commentary:
- required Python version: 3.10.0 (for pattern matching)
- I wanted to split it into separate modules but I have problems with 
  circular imports. I need to learn more about dependency inversion or 
  dependency injection before I could do that, but I already run out of time. 
"""

class Expression():
    @abstractmethod
    def calculate(self, vars):
        pass

    def __add__(self, expr2):
        return Or(self, expr2)

    def __mul__(self, expr2):
        return And(self, expr2)
    
    @staticmethod
    def _get_vars(formula, vars):
        '''
        Static private method that extracts variable's names from formula via pattern matching.
        Takes two arguments: Expression formula and list of strings
        Returns list of names. 
        '''
        match formula:
            case Variable():
                if formula.value not in vars:
                    vars.append(formula.value)
            case TwoArgOperator():
                Expression._get_vars(formula.operand_1, vars)
                Expression._get_vars(formula.operand_2, vars)
            case OneArgOperator():
                Expression._get_vars(formula.operand, vars)
            case Boolean():
                pass
        return vars   

    @staticmethod
    def _get_all_permutations(vars):
        '''
        Takes list of variables and creates all possible permutations
        of True and False based on how many variables are there 
        Returns list of dictionaries of said permutations
        '''
        values = [False] * len(vars)
        vals_permutations = [[False] * len(vars), [True] * len(vars)]  
        for i in range(len(vars) - 1):
            values[i] = True
            vals_permutations.extend(multiset_permutations(values))
        return Expression._assign_permutations_to_vars(vals_permutations, vars)

    @staticmethod
    def _assign_permutations_to_vars(vals_permutations, vars):
        '''
        Takes list of lists with all permutations of True and False
        Returns list of dictionaries with permutations assign to variables
        '''
        all_permutations = []
        for ls in vals_permutations:
            curr_permutation = {}
            for i in range(len(vars)):
                curr_permutation[vars[i]] = ls[i]
            all_permutations.append(curr_permutation)
        return all_permutations
        
    @staticmethod
    def is_tautology(formula):
        '''
        Static method that checks if formula is a tautology
        Takes one argument: Expression object
        Returns boolean 
        '''
        vars = Expression._get_vars(formula, [])
        permutations = Expression._get_all_permutations(vars)
        print([formula.calculate(args_dict) for args_dict in permutations])
        return all([formula.calculate(args_dict) for args_dict in permutations])

    def simplify(self):
        '''
        Takse formula and returns "simplified" version based on rules described in the task
        '''
        match(self):
            case Or(operand_1, operand_2):
                if isinstance(operand_1, Boolean) and operand_1.value == False:
                    return self.operand_2
                elif isinstance(operand_2, Boolean) and operand_2.value == False:
                    return self.operand_1
            case And(operand_1, operand_2):
                if isinstance(operand_1, Boolean) and operand_1.value == False or isinstance(operand_2, Boolean) and operand_2.value == False:
                    return Boolean(False)
            case _:
                return self

class OneArgOperator(Expression):
    def __init__(self, operand):
        self.operand = operand



class Not(OneArgOperator):
    def calculate(self, vars):
        return not self.operand.calculate(vars)
    
    def __str__(self):
        return "!" + self.operand.__str__()

class TwoArgOperator(Expression):

    __match_args__ = ("operand_1", "operand_2")

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

class Operand(Expression):
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

def run():
    print("====================")
    print("Tests for general functionality and __str__")
    expr_1 = Or(Not(Variable("x")), And(Variable("y"), Boolean(True)))
    # y ^ z
    expr_2 = And(Variable("y"), Variable("z"))
    print(expr_1, " == ", expr_1.calculate({"x": True, "y": True}), " for x = True, y = True")
    print(expr_1, " == ", expr_1.calculate({"x": True, "y": False}), " for x = True, y = False")
    print(expr_1, " == ", expr_1.calculate({"x": False, "y": False}), " for x = False, y = False")

    print("====================")
    print("Tests for tautology") 
    # (p => q) ^ p => q
    tautology_1 = And(Implication(Variable("p"), Variable("q")), Implication(Variable("p"), Variable("q")))
    print("Tautology 1:", Expression.is_tautology(tautology_1))
    #(p v q => r) => p => r
    tautology_2 = Implication(Implication(Or(Variable("p"), Variable("q")), Variable("r")), Implication(Variable("p"), Variable("r")))
    print("Tautology 2:", Expression.is_tautology(tautology_2))
    
    # !x v (y ^ true)
    not_a_tautology_1 = Or(Not(Variable("x")), And(Variable("y"), Boolean(True)))
    print("Not a tautology 1:", Expression.is_tautology(not_a_tautology_1))
    # (p => q => r) => p => r
    not_a_tautology_2 = Implication(Implication(Implication(Variable("p"), Variable("q")), Variable("r")), Implication(Variable("p"), Variable("r")))
    print("Not a tautology 2:", Expression.is_tautology(not_a_tautology_2))

    print("====================")
    print("Tests for __add__ and __mul__")
    print(Variable("y") + Variable("x"))
    print(Variable("y") * Variable("x"))

    print("=====================")
    print("Tests for simplify")
    expr_3 = And(Variable("p"), Boolean(False))
    expr_4 = Or(Variable("p"), Boolean(False))
    print(expr_3.simplify())
    print(expr_4.simplify())
run()

      

