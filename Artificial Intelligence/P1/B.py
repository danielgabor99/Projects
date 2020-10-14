# -*- coding: utf-8 -*-
import numpy
import matplotlib.pyplot as plt
from random import randint
print("Choose the distribution:\n1-uniform\n2-binomial")
a=input()
i1=int(input("lower limit"))
i2=int(input("big limit"))
s1=numpy.random.uniform(i1,i2,10)
i=randint(i1,i2)
s2=numpy.random.binomial(7,0.5,i)
if a=='1':
    plt.plot(s1,"ro")
    plt.xlabel("Uniform Distribution")
    plt.ylabel("Some values")
    plt.show()
else:
    plt.plot(s2,"ro")
    plt.xlabel("Binomial Distribution")
    plt.ylabel("Some values")
    plt.show()