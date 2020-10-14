# -*- coding: utf-8 -*-
"""
Created on Mon Mar  9 00:03:46 2020

@author: Daniel
"""
import State
import Problem
class Controller:
    def __init__(self):
        self.listOfInstances=[]
        
    def createInstances(self):
        for n in range(1,5):
            p=Problem(n,[0][0],[0][0])
            for i in range (1, 5):
                for j in range (i+2, 5):
                    s=State(4)
                    s.matrix[i][j]=1
                    p.expand(p)
        self.listOfInstances.append(p)
    def orderStates(self):
        pass
    def Greedy(self):
        pass
    def dfs(self,elem):
        found=False
        visited=[]
        toVisit=[self.listOfInstances]
        while toVisit!=[] and found:
            if toVisit==[]:
                return False
            node=toVisit.pop()
            visited=visited.append(node)
            if node==elem:
                found=True
            else:
                aux=[]
            for i in node:
                if i not in visited:
                    aux.append(i)
            toVisit=aux.append(toVisit)        