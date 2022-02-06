import timeit
from functools import reduce

"""
Commentary: 
- Complexity: (n/2)* n = O(n^2)

"""

def perfect_num_imperative(n):
    '''Takes an integer. 
       Returns a list of perfect numbers under n.
    '''
    perfect_nums = []
    for num in range(1, n + 1):
        divisors = []
        
        for i in range(1, (num // 2) + 1):
            if num % i == 0:
                divisors.append(i)

        divisors_sum = 0
        for i in divisors:
            divisors_sum += i

        if divisors_sum == num:
            perfect_nums.append(num)

    return perfect_nums


def perfect_num_list_comprehension(n):
    '''Takes an integer. 
       Returns a list of perfect numbers under n.

       Algorithm:
       1. get a list of tuples that contain number and a list of its divisors
       2. sum divisors 
       3. check if there are tuples that contain two equal numbers
       4. return a list of first elements of these tuples
    '''
    nums_divisors = [(num, [divisor for divisor in range(1, (num // 2) + 1) if num % divisor == 0]) for num in range(1, n + 1)]
    divisors_sum = [(pair[0], sum(pair[1])) for pair in nums_divisors]
    perfect_numbers = [pair[0] for pair in divisors_sum if pair[0] == pair[1]]
    
    return perfect_numbers

def perfect_num_function(n):
    '''Takes an integer. 
       Returns a list of perfect numbers under n.

       Algorithm:
       1. get all numbers under n in one list
       2. find all divisors for each number
       3. sum divisors 
       4. compare divisor with the number 
    '''
    numbers_under_n = list(range(2, n))
    divisors = list(map(lambda x: list(filter(lambda y: x % y == 0, list(range(1, (x//2) + 1)))), numbers_under_n))
    divisors_sum = list(map(lambda divs_list: reduce(lambda a, b: a + b, divs_list), divisors))
    perfect_numbers = filter(lambda x: x == divisors_sum[x - 2], numbers_under_n)

    return list(perfect_numbers)



def count_time(n):
    '''Takes n that describes number of repetition for timeit function.
       Returns average time for each function that returns perfect numbers under N. 
    '''
    SETUP_CODE_1 = '''
from __main__ import perfect_num_imperative, perfect_num_list_comprehension, perfect_num_function
N = 1000
'''
    SETUP_CODE_2 = '''
from __main__ import perfect_num_imperative, perfect_num_list_comprehension, perfect_num_function
N = 2000
'''
    SETUP_CODE_3 = '''
from __main__ import perfect_num_imperative, perfect_num_list_comprehension, perfect_num_function
N = 5000
'''
    TEST_CODE_IMPERATIVE = '''
perfect_num_imperative(N)
'''

    TEST_CODE_LIST = '''
perfect_num_list_comprehension(N)
'''

    TEST_CODE_FUNCTION = '''
perfect_num_function(N)
'''

    number = 100
    imperative_time = timeit.repeat(stmt=TEST_CODE_IMPERATIVE, setup=SETUP_CODE_1, repeat=n, number=number)
    list_time = timeit.repeat(stmt=TEST_CODE_LIST, setup=SETUP_CODE_1, repeat=n, number=number)
    function_time = timeit.repeat(stmt=TEST_CODE_FUNCTION, setup=SETUP_CODE_1, repeat=n, number=number)
    print("For N = 1000")
    print("Imperative:", sum(imperative_time) / n)
    print("List comprehension:", sum(list_time) / n)
    print("Function:", sum(function_time) / n)

    imperative_time = timeit.repeat(stmt=TEST_CODE_IMPERATIVE, setup=SETUP_CODE_2, repeat=n, number=number)
    list_time = timeit.repeat(stmt=TEST_CODE_LIST, setup=SETUP_CODE_2, repeat=n, number=number)
    function_time = timeit.repeat(stmt=TEST_CODE_FUNCTION, setup=SETUP_CODE_2, repeat=n, number=number)
    print("For N = 2000")
    print("Imperative:", sum(imperative_time) / n)
    print("List comprehension:", sum(list_time) / n)
    print("Function:", sum(function_time) / n)
    
    imperative_time = timeit.repeat(stmt=TEST_CODE_IMPERATIVE, setup=SETUP_CODE_3, repeat=n, number=number)
    list_time = timeit.repeat(stmt=TEST_CODE_LIST, setup=SETUP_CODE_3, repeat=n, number=number)
    function_time = timeit.repeat(stmt=TEST_CODE_FUNCTION, setup=SETUP_CODE_3, repeat=n, number=number)
    print("For N = 5000")
    print("Imperative:", sum(imperative_time) / n)
    print("List comprehension:", sum(list_time) / n)
    print("Function:", sum(function_time) / n)


print(perfect_num_imperative(10000))
print(perfect_num_list_comprehension(10000))
print(perfect_num_function(10000))

count_time(1)

'''
In this exercise functional implementation was much slower in opposite to the first exercise. Probably because I overload it 
with lots of list() and map()
Output for timeit 100 x 1 repetition (for bigger numbers it takes too long):
For N = 1000
Imperative: 1.2841170000000002        
List comprehension: 1.2982077999999992
Function: 2.9617991000000004
For N = 2000
Imperative: 5.4240493999999995
List comprehension: 5.503191899999997
Function: 11.872598099999998
For N = 5000
Imperative: 34.9911457
List comprehension: 35.937472799999995
Function: 79.1838162
'''

# Grade: 4.0

# Didactic notes: it seems to crash for me, but overall it seems like job well done, I like the discussion and
# everything.
