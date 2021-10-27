"""
General commnets
- task didn't specify if all words should be in one list or each
  word should be in its own list. So I decided to add all tuples to one
  list + add (" ", 1) after each word to represent spaces
"""


"""
Takes: string with single word
Returns: array of tuples that represent comresed word
"""
def compres(word):
    compresed_word = []
    i = 0
    while i < len(word):
        char = word[i]
        char_quont = 1
        for j in range(i + 1, len(word)):
            if char == word[j]:
                char_quont += 1
            else: break
        compresed_word.append((char_quont, char))
        i += char_quont 
    compresed_word.append((1, " "))
    return compresed_word


"""
Takes: array of arrays of tuples
Returns: array of tuples
"""
def flatten(tuple_list):
    return [tuple for sublist in tuple_list for tuple in sublist]

"""
Takes: string of words
Returns: array of tuples that represent compresed sentence/word 
"""
def do_compresion(text):
    splited_text = [compres(word) for word in text.split()]
    return flatten(splited_text)

"""
Takes: array of tuples that was compresed with do_compresion function
Returns: decompresed string of words/word
"""
def do_decompresion(compresed_text):
    decompresed_text = [tup[1] * tup[0] for tup in compresed_text]
    return "".join(decompresed_text)


text = "Pppooodział perrrryklinaalny inicjjjałów!"
print(do_compresion(text))
print(do_decompresion(do_compresion(text)))

text_2 = "Mr. and Mrs. Dursley, of number four, Privet Drive, were proud to say that they were perfectly normal"
print(do_compresion(text_2))
print(do_decompresion(do_compresion(text_2)))