from math import sqrt
from decimal import Decimal

a = Decimal(0.001)

# result = Decimal(Decimal(1) / Decimal(sqrt(Decimal(pow(Decimal(0.001), Decimal(13))) + Decimal(1))))
result = Decimal(sqrt(1.0000000000000001))
print(result)