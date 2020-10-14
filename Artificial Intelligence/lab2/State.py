# -*- coding: utf-8 -*-
"""
Created on Fri Mar  6 15:49:17 2020

@author: Daniel
"""



import numpy as np
class State:
    def __init__(self,n):
        self.n=n
        self.matrix=[[0]*n]*n
    def getState(self):
        return self.matrix
    