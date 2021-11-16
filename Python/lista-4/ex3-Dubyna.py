"""
Commentary: 
- for this task I used backtracking algorithm
- Complexity: O(9^(n*n))
"""

def find_empty_box(board):
    """
    Takes 2D list as an argument. Returns first found box that contains 0
    Returns empty list if there is no such box
    """
    for i in range(9):
        for j in range(9):
            if board[i][j] == 0:
                return[i, j]
    return []

def check_row(board, row_index, num):
    """
    Takes 3 arguments: 2D list, row index, number. 
    Looks for the same number in the row. 
    Returns boolean.
    """
    return all(box != num for box in board[row_index])

def check_column(board, column_index, num):
    """
    Like check_row but for a column. 
    Returns boolean.
    """
    return all(row[column_index] != num for row in board)

def check_inner_board(board, box_index, num):
    """
    Takes 3 arguments: 2D list, list with row and column index, number. 
    Looks for the same number in the inner 3x3 matrix. 
    Returns boolean.
    """
    start_row = box_index[0] - box_index[0] % 3
    start_col = box_index[1] - box_index[1] % 3

    for i in range(3):
        for j in range(3):
            if board[start_row + i][start_col + j] == num:
                return False
    return True


def is_board_valid(board, new_num_index, num):
    """
    Takes 3 arguments: 2D list, list with row and column index, number. 
    Checks if number is already in row/column/3x3 matrix. 
    Also check if all numbers are in range 1-9
    Return boolean 
    """
    for i in range(9):
        for j in range(9):
            if  board[i][j] < 0 or board[i][j] > 9:
                return False
    return check_row(board, new_num_index[0], num) and check_column(board, new_num_index[1], num) and check_inner_board(board, new_num_index, num)

def solve_sudoku(board):
    """
    Takes 2D list that represents sudoku board. Solves sudoku using backtracking algorithm.
    Returns boolean that answers a question "is sudoku solved"
    """

    empty_box_index = find_empty_box(board)
    if not empty_box_index:
        return True

    for i in range(1, 10):
        if is_board_valid(board, empty_box_index, i):
            row = empty_box_index[0]
            column = empty_box_index[1]

            board[row][column] = i
            if solve_sudoku(board):
                return True

            board[row][column] = 0

    return False
    

def print_sudoku(board):
    """
    Takes 2D list with sudoku board and prints it
    """
    print("=" * 37)
    for i, row in enumerate(board):
        print(("|" + " {}   {}   {} |"*3).format(*[x if x != 0 else " " for x in row]))
        if i == 8:
            print("=" * 37)
        elif i % 3 == 2:
            print("|" + "====" * 8 + "===|")
        else:
            print("|" + "----" * 8 + "---|")


def run():
    sudoku_board_1 = [
                    [3, 0, 6, 5, 0, 8, 4, 0, 0],
                    [5, 2, 0, 0, 0, 0, 0, 0, 0],
                    [0, 8, 7, 0, 0, 0, 0, 3, 1],
                    [0, 0, 3, 0, 1, 0, 0, 8, 0],
                    [9, 0, 0, 8, 6, 3, 0, 0, 5],
                    [0, 5, 0, 0, 9, 0, 6, 0, 0],
                    [1, 3, 0, 0, 0, 0, 2, 5, 0],
                    [0, 0, 0, 0, 0, 0, 0, 7, 4],
                    [0, 0, 5, 2, 0, 6, 3, 0, 0]]

    sudoku_board_1_solved = [
                            [3, 1, 6, 5, 7, 8, 4, 9, 2],
                            [5, 2, 9, 1, 3, 4, 7, 6, 8], 
                            [4, 8, 7, 6, 2, 9, 5, 3, 1],
                            [2, 6, 3, 4, 1, 5, 9, 8, 7], 
                            [9, 7, 4, 8, 6, 3, 1, 2, 5], 
                            [8, 5, 1, 7, 9, 2, 6, 4, 3], 
                            [1, 3, 8, 9, 4, 7, 2, 5, 6],
                            [6, 9, 2, 3, 5, 1, 8, 7, 4],
                            [7, 4, 5, 2, 8, 6, 3, 1, 9]] 
    sudoku_board_2 = [
                    [0, 4, 0, 0, 0, 7, 6, 0, 0],
                    [8, 0, 6, 0, 4, 0, 0, 3, 0],
                    [0, 2, 7, 0, 0, 0, 0, 0, 8],
                    [0, 0, 0, 4, 8, 0, 0, 0, 3],
                    [0, 9, 0, 7, 0, 6, 0, 4, 0],
                    [2, 0, 0, 0, 1, 3, 0, 0, 0],
                    [4, 0, 0, 0, 0, 0, 8, 1, 0],
                    [0, 5, 0, 0, 3, 0, 2, 0, 4],
                    [0, 0, 1, 5, 0, 0, 0, 6, 0]]

    sudoku_board_3 = [
                    [0, 4, 0, 0, 0, 7, 6, 0, 0],
                    [8, 0, 6, 0, 4, 0, 0, 3, 0],
                    [0, 2, 7, 0, 0, 0, 0, 0, 8],
                    [0, 0, 0, 4, -1, 0, 0, 0, 3],
                    [0, 9, 0, 7, 0, 6, 0, 4, 0],
                    [2, 0, 0, 0, 1, 3, 0, 0, 0],
                    [4, 0, 0, 0, 0, 0, 8, 1, 0],
                    [0, 5, 0, 0, 3, 0, 2, 0, 4],
                    [0, 0, 1, 5, 0, 0, 0, 6, 0]]

    boards = [sudoku_board_1, sudoku_board_2, sudoku_board_3]

    for board in boards:
        if solve_sudoku(board):
            print_sudoku(board)
        else:
            print("unsolvable")

run()