def string_to_array(text):
    return [char.lower() for char in text if char.strip()]

def clear_array(list):
    invalid_chars = [".", ",", ";", ":", "-", "/", "!", "?"]
    return [char for char in list if char not in invalid_chars]

def is_palindrome(text):
    char_array = clear_array(string_to_array(text))
    length = len(char_array)
    for i in range(length//2):
        if char_array[i] != char_array[length - 1 - i]:
            return False
    return True

def test():
    test_lines = {
        "Kobyla ma maly! bok.": True,
        "Kobyła ma mały bok": True,
        "Kobyła ma maly bok": False,
        "Eine güldne, gute Tugend: Lüge nie!": True,
        "Eine güldne/, gute? Tugend: Lüge nie!": True,
        "Точно не палиндром": False,
        "а роза упала на лапу Азора": True
    }
    line_index = 1
    for line in test_lines:
        if is_palindrome(line) != test_lines[line]:
            print("Doesn't work for line #", line_index)
        line_index += 1
    print("Done testing")

test()
