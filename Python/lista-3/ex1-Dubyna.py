import timeit

"""
Commentary:

- I didn't know if I should use some fancy-ish algorithm like sieve of eratosthenes.
  So I decided to just go for it and make some straightforward "take number and 
  divide it by all previous numbers. 
- Complexity: ((1 + n)/2 )* n = O(n^2)

"""
def primes_imperative(n):
    '''Takes integer.
       Returns list of prime numbers under n.
       Uses nested loop.
    '''
    result = []
    for i in range(1, n + 1):
        is_prime = True
        for j in range(2, i):
            if i % j == 0:
                is_prime = False
        if is_prime:
            result.append(i)
    return result


def primes_list_comprehension(n):
    '''Takes integer.
       Returns list of prime numbers under n.
       I would never write a comprehension statement like this one in a real-life project,
       but for the sake of this task I decided to shove everything in a single array.
       Uses nested list comprehension. An inner list contains true booleans of a statement "if number is divisible
       by any previous number". If this list is empty than number added to the main list. 
    '''
    primes = [num for num in range(1, n + 1) if not [num for i in range(2, num) if num % i == 0]]

    return primes


def primes_function(n):
    '''Takes integer.
       Returns list of prime numbers under n.
       Uses list() and range() to create a list of all numbers under n. 
       Than filters it with lambda function that uses all() to check if number is not divisible 
       by its previous numbers.
    '''
    primes = filter(lambda x: all(x % i != 0 for i in range(2, x)), list(range(1, n)))

    return list(primes)


def count_time(n):
    '''Takes n that describes number of repetition for timeit function.
       Returns average time for each function that returns prime numbers under N. 
    '''
    SETUP_CODE_1 = '''
from __main__ import primes_imperative, primes_list_comprehension, primes_function
N = 100
'''
    SETUP_CODE_2 = '''
from __main__ import primes_imperative, primes_list_comprehension, primes_function
N = 400
'''
    SETUP_CODE_3 = '''
from __main__ import primes_imperative, primes_list_comprehension, primes_function
N = 800
'''
    TEST_CODE_IMPERATIVE = '''
primes_imperative(N)
'''

    TEST_CODE_LIST = '''
primes_list_comprehension(N)
'''

    TEST_CODE_FUNCTION = '''
primes_function(N)
'''

    number = 1000
    imperative_time = timeit.repeat(stmt=TEST_CODE_IMPERATIVE, setup=SETUP_CODE_1, repeat=n, number=number)
    list_time = timeit.repeat(stmt=TEST_CODE_LIST, setup=SETUP_CODE_1, repeat=n, number=number)
    function_time = timeit.repeat(stmt=TEST_CODE_FUNCTION, setup=SETUP_CODE_1, repeat=n, number=number)
    print("For N = 100")
    print("Imperative:", sum(imperative_time) / n)
    print("List comprehension:", sum(list_time) / n)
    print("Function:", sum(function_time) / n)

    imperative_time = timeit.repeat(stmt=TEST_CODE_IMPERATIVE, setup=SETUP_CODE_2, repeat=n, number=number)
    list_time = timeit.repeat(stmt=TEST_CODE_LIST, setup=SETUP_CODE_2, repeat=n, number=number)
    function_time = timeit.repeat(stmt=TEST_CODE_FUNCTION, setup=SETUP_CODE_2, repeat=n, number=number)
    print("For N = 400")
    print("Imperative:", sum(imperative_time) / n)
    print("List comprehension:", sum(list_time) / n)
    print("Function:", sum(function_time) / n)
    
    imperative_time = timeit.repeat(stmt=TEST_CODE_IMPERATIVE, setup=SETUP_CODE_3, repeat=n, number=number)
    list_time = timeit.repeat(stmt=TEST_CODE_LIST, setup=SETUP_CODE_3, repeat=n, number=number)
    function_time = timeit.repeat(stmt=TEST_CODE_FUNCTION, setup=SETUP_CODE_3, repeat=n, number=number)
    print("For N = 800")
    print("Imperative:", sum(imperative_time) / n)
    print("List comprehension:", sum(list_time) / n)
    print("Function:", sum(function_time) / n)


print(primes_imperative(20))
print(primes_list_comprehension(20))
print(primes_function(20))

count_time(3)

"""
Results: 
code was tested for multiple cases with different N and numbers of function repetition 
Functional solution proved to be the fastest. Much faster then the others. List comprehension and imperative functions gave very similar results
for small numbers. However for N > 400 imperative solution gave better time. 

Output:
For N = 100
Imperative: 0.20206219999999997        
List comprehension: 0.25749566666666673
Function: 0.12913843333333333
For N = 400
Imperative: 3.343018666666667        
List comprehension: 3.750938533333333
Function: 1.434491499999999
For N = 800
Imperative: 14.337926600000003
List comprehension: 16.24463136666667
Function: 4.6757792000000125
"""







