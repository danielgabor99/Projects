# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""
import numpy
import matplotlib.pyplot as plt
s2=numpy.random.binomial(7,0.5,20)
s=numpy.random.geometric(0.2,10)

plt.plot(s2,"ro")
plt.ylabel("Some values")
plt.show()
