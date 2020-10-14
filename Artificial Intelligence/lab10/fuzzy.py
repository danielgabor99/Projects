# -*- coding: utf-8 -*-
"""
Created on Tue May 20 22:03:47 2020

@author: Daniel
"""

class Descriptions:
    
    def __init__(self):
        self.regions = {}
        self.inverse = {}


    def Fuzzify(self, value):

        return {name: member(value)
                for name, member in self.regions.items()
                }

    def Defuzzify(self, name, value):
        return self.inverse[name](value)
    
    
    def add_region(self, name, member, inverse=None):

        self.regions[name] = member
        self.inverse[name] = inverse