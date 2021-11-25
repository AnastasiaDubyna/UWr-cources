from abc import abstractmethod
from sympy.utilities.iterables import multiset_permutations
from two_arg_operator import Or, And, Implication, TwoArgOperator
from one_arg_operator import Not, OneArgOperator
from operand import Variable, Boolean

"""
Commentary:
- required Python version: 3.10.0 (for pattern matching)
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
            case Or(operand_1 = False):
                return self.operand_2
            case Or(operand_2 = False):
                return self.operand_1
            case And(operand_1 = False) | And(operand_2 = False):
                return Boolean(False)
            case _:
                return self


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
    #(p => q) ^ p => q
    # tautology_1 = And(Implication(Variable("p"), Variable("q")), Implication(Variable("p"), Variable("q")))
    # print("Tautology 1:", Expression.is_tautology(tautology_1))
    # #(p v q => r) => p => r
    # tautology_2 = Implication(Implication(Or(Variable("p"), Variable("q")), Variable("r")), Implication(Variable("p"), Variable("r")))
    # print("Tautology 2:", Expression.is_tautology(tautology_2))
    
    #!x v (y ^ true)
    # not_a_tautology_1 = Or(Not(Variable("x")), And(Variable("y"), Boolean(True)))
    # print("Not a tautology 1:", Expression.is_tautology(not_a_tautology_1))
    #(p => q => r) => p => r
    # not_a_tautology_2 = Implication(Implication(Implication(Variable("p"), Variable("q")), Variable("r")), Implication(Variable("p"), Variable("r")))
    # print("Not a tautology 2:", Expression.is_tautology(not_a_tautology_2))

    print("====================")
    print("Tests for __add__ and __mul__")
    print(Variable("y") + Variable("x"))
    print(Variable("y") * Variable("x"))

    print("=====================")
    print("Tests for simplify")
    expr_3 = And(Variable("p"), Boolean(False))
    print(expr_3.simplify())
run()

      

