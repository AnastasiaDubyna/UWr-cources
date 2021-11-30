from decimal import Decimal

zakupy = [0.2, 0.5, 4.65, 3.78, 3.4]
zakupy_decimal = [Decimal(num) for num in zakupy]

def vat_faktura(zakupy_netto, tax):
    return sum(zakupy_netto) * tax

def vat_paragon(zakupy_netto, tax):
    return sum([num * tax for num in zakupy_netto])

def run():
    print(vat_faktura(zakupy, 0.23))
    print(vat_paragon(zakupy, 0.23))
    print((vat_faktura(zakupy, 0.23)) == (vat_paragon(zakupy, 0.23)))

    print("\nDecimal:")
    print(vat_faktura(zakupy_decimal, Decimal(0.23)))
    print(vat_paragon(zakupy_decimal, Decimal(0.23)))
    print((vat_faktura(zakupy_decimal, Decimal(0.23))) == (vat_paragon(zakupy_decimal, Decimal(0.23))))

run()

"""
Decimals are obviously more precise since they can take up to 28 places. 
Build-in python floats use only 18. 

It looks like floats loose some precision in vat_paragon for the reason that I'll probably learn 
further in my Analiza numeryczna course.
"""
# Grade: 0.5

# Didactic notes:

# Unfortunately you should have done some more reading about Decimal and its usage. The problem is,
# the class Decimal is able to store short decimal numbers correctly. In fact your argument about more precision
# is wrong, both Decimal and float are internally I think 64-bit numbers, in fact Decimal has slightly less precision
# due to the way the decimal numbers are stored.

# You should have done more investigation why such ugly number such as
# 2.881900000000000144095846366
# appear, then you would learn that Decimal requires you to insert into the class as strings, or it produces incorrect
# values immediately. The right use is x = Decimal("0.23")

# I'm happy you have tried to do the reasoning, too bad it really needed more research. Also, please next time,
# do a few more tests of the input.
