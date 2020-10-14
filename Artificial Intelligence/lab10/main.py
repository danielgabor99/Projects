# -*- coding: utf-8 -*-
"""
Created on Tue May 20 21:59:18 2020

@author: Daniel
"""

from controller import Controller
from fuzzy import Descriptions
from fuzzyrule import FuzzyRule

def inverse_line(a, b):
    return lambda val: val * (b - a) + a


def inverse_tri(a, b, c):
    return lambda val: (inverse_line(a, b)(val) + inverse_line(c, b)(val)) / 2

def trapezoidal_region(a, b, c, d):
    """
        trapezoidal fuzzy region
    """
    return lambda x: max(0, min((x - a) / (b - a), 1, (d - x) / (d - c)))


def triangular_region(a, b, c):
    """
     triangular fuzzy region
    """
    return trapezoidal_region(a, b, b, c)





if __name__ == '__main__':
    temperature = Descriptions()
    humidity = Descriptions()
    time = Descriptions()
    rules = []
    
    
    
    humidity.add_region('dry', triangular_region(-1000, 0, 50))
    humidity.add_region('normal', triangular_region(0, 50, 100))
    humidity.add_region('wet', triangular_region(50, 100, 1000))

    time.add_region('short', triangular_region(-1000, 0, 50), inverse_line(50, 0))
    time.add_region('medium', triangular_region(0, 50, 100), inverse_tri(0, 50, 100))
    time.add_region('long', triangular_region(50, 100, 1000), inverse_line(50, 100))

    temperature.add_region('very cold', trapezoidal_region(-1000, -30, -20, 5))
    temperature.add_region('cold', triangular_region(-5, 0, 10))
    temperature.add_region('normal', trapezoidal_region(5, 10, 15, 20))
    temperature.add_region('warm', triangular_region(15, 20, 25))

    

    rules.append(FuzzyRule({'temperature': 'very cold', 'humidity': 'wet'},
                           {'time': 'short'}))
    rules.append(FuzzyRule({'temperature': 'cold', 'humidity': 'wet'},
                           {'time': 'short'}))
    rules.append(FuzzyRule({'temperature': 'normal', 'humidity': 'wet'},
                           {'time': 'short'}))
    rules.append(FuzzyRule({'temperature': 'warm', 'humidity': 'wet'},
                           {'time': 'short'}))


    rules.append(FuzzyRule({'temperature': 'very cold', 'humidity': 'normal'},
                           {'time': 'short'}))
    rules.append(FuzzyRule({'temperature': 'cold', 'humidity': 'normal'},
                           {'time': 'medium'}))
    rules.append(FuzzyRule({'temperature': 'normal', 'humidity': 'normal'},
                           {'time': 'medium'}))
    rules.append(FuzzyRule({'temperature': 'warm', 'humidity': 'normal'},
                           {'time': 'medium'}))

    rules.append(FuzzyRule({'temperature': 'very cold', 'humidity': 'dry'},
                           {'time': 'medium'}))
    rules.append(FuzzyRule({'temperature': 'cold', 'humidity': 'dry'},
                           {'time': 'long'}))
    rules.append(FuzzyRule({'temperature': 'normal', 'humidity': 'dry'},
                           {'time': 'long'}))
    rules.append(FuzzyRule({'temperature': 'warm', 'humidity': 'dry'},
                           {'time': 'long'}))


    controller = Controller(temperature, humidity, time, rules)

    print(controller.result({'humidity': 75, 'temperature': 20}))
 