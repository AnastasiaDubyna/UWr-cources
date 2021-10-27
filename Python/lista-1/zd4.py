from random import random

"""
makes initial n tosses to fill an array. Checks if all tosses gave the same result
if not - continues to add tosses and after each checks if last n results were the same  
"""
def get_n_in_a_row(n):
    tosses = []
    for i in range(n):
        tosses.append(toss_a_coin())
    while tosses[-n:].count(tosses[-1]) != n:
        tosses.append(toss_a_coin())    
    return len(tosses)

"""
uses random method. If received number <= 0.5  - returns heads. Else returns tails. 
"""
def toss_a_coin():
    toss = random()
    return "heads" if toss <= 0.5 else "tails"
    
"""
n - how many experiments 
k - how many of the same side in a row 
returns average count of tosses needed to get k in a row
"""
def do_experiment(n, k):
    results = []
    for i in range(n):
        results.append(get_n_in_a_row(k))
    return sum(results)/len(results)


print("Average tosses count:", do_experiment(10, 3))
