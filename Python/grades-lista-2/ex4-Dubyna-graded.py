from random import choice
"""
General comments:
- What a random task xD
- Used spit() to split string into separate words. Stripped each words and got rid of words 
  that are longer than max_word_len 
- Used while loop to remove random words until max_word_quont 
- Joined list, capitalized first letter and added "." to make it look more like actual sentence 
- That list comprehension statemen probably looks like too much but I'm not sure.
  It's really convenient though.
"""


text = "Podział peryklinalny inicjałów wrzecionowatych kambium charakteryzuje się ścianą podziałową inicjowaną w płaszczyźnie maksymalnej."
longer_text = open("harry-potter.txt", "r").read()

"""
Takes: function string, max word length and max word quontity
Returns: string that conteins only given quontity of words of given length

"""
def simplify(text, max_word_len, max_word_quont): 
    filtered_text = [word.strip("\",:;!?.-").lower() for word in text.split() if len(word) <= max_word_len]
    while len(filtered_text) > max_word_quont:
        filtered_text.remove(choice(filtered_text))
    return " ".join(filtered_text).capitalize() + "."


print(simplify(text, 10, 5))
print(simplify(longer_text, 10, 5))
print(simplify(longer_text, 15, 50))

# Grade: 1.85

# Didactic notes: Good, except you fail to return the right mark on a simplified sentence...
# That would have been easy to fix.
