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
