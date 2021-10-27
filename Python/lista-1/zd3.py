"""
Also works for x2 < x1 and/or y2 < y1
"""


"""
does multiplication of provided xs and ys => returns array of arrays 
"""
def do_multiplication(xs, ys):
    result = []
    for y in ys:
        result.append([x * y for x in xs])
    return result


"""
takes an array of numbers and prints it with calculated spaces 
"""
def print_line(line, space):
    print(''.join([('{:%s}' %(space)).format(item) for item in line]))


"""
takes the result of multiplication => returns length of the biggest value + 2
"""
def calculate_space(results):
    biggest_value = max([max(line) for line in results])
    return len(str(biggest_value)) + 2


"""
takes all xs, ys and the result of their multiplication => returns nothing
calls print_line function for each line of the result
"""
def make_table(xs, ys, results):
    space = calculate_space(results)
    xs.insert(0, " ")
    print_line(xs, space)
    for i in range(len(ys)):
        line = results[i]
        line.insert(0, ys[i])
        print_line(line, space)


"""
takes a beginning and an end of multiplication range => returns nothing 
gets all values of x and y in given ranges, calls multiplication function, calls function that creates a table
"""
def tabliczka(x1, x2, y1, y2):
    step_x = 1 if x2 >= x1 else -1
    step_y = 1 if y2 >= y1 else -1

    all_xs = [x for x in range(x1, x2 + 1, step_x)]
    all_ys = [y for y in range(y1, y2 + 1, step_y)]
    
    results = do_multiplication(all_xs, all_ys)
    make_table(all_xs, all_ys, results)
    

tabliczka(1000, 1004, 1002, 1005)
# tabliczka(1005, 1000, 1010, 1005)

