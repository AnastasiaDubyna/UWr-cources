from abc import abstractmethod, ABC

from sympy.utilities.iterables import multiset_permutations


class Expression:
    @abstractmethod
    def calculate(self, vars):
        pass

    def __add__(self, expr2):
        return Or(self, expr2)

    def __mul__(self, expr2):
        return And(self, expr2)

    @staticmethod
    def _get_vars(formula, vars):
        """
        :param formula: Expression
        :param vars: Variable[]
        :return: Variable[] vars - list of variables in the formula
        """
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
        """
        Takes list of variables and creates all possible permutations
        of True and False based on how many variables are there.
        :param vars: Variable[]
        :return: returns list of dictionaries of said permutations
        """
        values = [False] * len(vars)
        vals_permutations = [[False] * len(vars), [True] * len(vars)]
        for i in range(len(vars) - 1):
            values[i] = True
            vals_permutations.extend(multiset_permutations(values))
        return Expression._assign_permutations_to_vars(vals_permutations, vars)

    @staticmethod
    def _assign_permutations_to_vars(vals_permutations, vars):
        """
        :param vals_permutations: list of lists with all permutations of True and False
        :param vars: Variable[]
        :return: list of dictionaries with permutations assign to variables
        """
        all_permutations = []
        for ls in vals_permutations:
            curr_permutation = {}
            for i in range(len(vars)):
                curr_permutation[vars[i]] = ls[i]
            all_permutations.append(curr_permutation)
        return all_permutations

    @staticmethod
    def is_tautology(formula):
        """
        Static method that checks if formula is a tautology
        :param formula: Expression
        :return: boolean
        """
        vars = Expression._get_vars(formula, [])
        permutations = Expression._get_all_permutations(vars)
        print([formula.calculate(args_dict) for args_dict in permutations])
        return all([formula.calculate(args_dict) for args_dict in permutations])

    def simplify(self):
        """
        :return: "simplified" version of a formula based on rules described in the task
        """
        match self:
            case Or(operand_1, operand_2):
                if isinstance(operand_1, Boolean) and operand_1.value is False:
                    return self.operand_2
                elif isinstance(operand_2, Boolean) and operand_2.value is False:
                    return self.operand_1
            case And(operand_1, operand_2):
                if isinstance(operand_1, Boolean) and operand_1.value is False or isinstance(operand_2,
                                                                                             Boolean) and operand_2.value is False:
                    return Boolean(False)
            case _:
                return self


class OneArgOperator(Expression, ABC):
    def __init__(self, operand):
        self.operand = operand


class Not(OneArgOperator):
    def calculate(self, vars):
        return not self.operand.calculate(vars)

    def __str__(self):
        return "!" + self.operand.__str__()


class TwoArgOperator(Expression, ABC):
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
        super().__init__(value)
        if not isinstance(value, bool):
            raise TypeError("Boolean value is true or false")
        self.value = value

# pydoc generated with  python -m pydoc -w ex2_sheet5
