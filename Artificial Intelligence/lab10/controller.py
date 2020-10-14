# -*- coding: utf-8 -*-
"""
Created on Tue May 20 22:02:02 2020

@author: Daniel
"""

from fuzzysystem import FuzzySystem


class Controller:
    def __init__(self, temperature, humidity, time, rules):
        self.system = FuzzySystem(rules)
        self.system.add_description('temperature', temperature)
        self.system.add_description('humidity', humidity)
        self.system.add_description('time', time, out=True)

    def result(self, inputs):
        return "humidity: " + str(inputs['humidity']) + \
               " and temperature: " + str(inputs['temperature']) + \
               " ==>>> " + str(self.system.compute(inputs))