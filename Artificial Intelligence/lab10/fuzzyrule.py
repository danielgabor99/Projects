# -*- coding: utf-8 -*-
"""
Created on Tue May 20 23:05:07 2020

@author: Daniel
"""
class FuzzyRule:
 
    def __init__(self, inputs, out):

        self.outputs = out  
        self.inputs = inputs

    def compute(self, inputs):
        return [self.outputs, min(
            [inputs[descr_name][var_name]
             for descr_name, var_name in self.inputs.items()
             ])]
