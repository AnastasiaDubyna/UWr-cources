def compress(word):
    """
    :param word: string with a single word
    :return: array of tuples that represent compressed word
    """
    compressed_word = []
    i = 0
    while i < len(word):
        char = word[i]
        char_quont = 1
        for j in range(i + 1, len(word)):
            if char == word[j]:
                char_quont += 1
            else:
                break
        compressed_word.append((char_quont, char))
        i += char_quont
    compressed_word.append((1, " "))
    return compressed_word


def flatten(tuple_list):
    """
    :param tuple_list: array of arrays of tuples
    :return: array of tuples
    """
    return [pair for sublist in tuple_list for pair in sublist][:-1]


def do_compression(text_str):
    """
    :param text_str: string of words
    :return: array of tuples that represent compressed sentence/word
    """
    splited_text = [compress(word) for word in text_str.split()]
    return flatten(splited_text)


def do_decompression(compressed_text):
    """
    :param compressed_text: array of tuples that was compressed with do_compression function
    :return: decompressed string of words/word
    """
    decompressed_text = [tup[1] * tup[0] for tup in compressed_text]
    return "".join(decompressed_text)

# pydoc generated with  python -m pydoc -w ex4_sheet2
