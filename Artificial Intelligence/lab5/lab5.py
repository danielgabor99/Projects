# -*- coding: utf-8 -*-
"""
Created on Sat Apr  4 20:20:13 2020

@author: Daniel
"""
import random
import itertools
import numpy as np
from itertools import permutations 
class ant:
    def line(self,length):
        #creating a good line
        #creating a permutation
        p=[]
        for x in range(1,length+1):
            p.append(x)
        perm=list(permutations(p))#list of permutations
        
        s=random.randint(1,len(perm)-1)
        t=random.randint(1,len(perm)-1)
        line=[]
        for pos in range(length):
          line.append((perm[s][pos],perm[t][pos]))
        return line
    def __init__(self, n):
        self.n=n
        self.path = [self.line(n) for x in range(n)]    
    def nextMoves(self, n):
        # returneaza o lista de posibile mutari corecte de la pozitia a
        lista=[]
        a = []
        for i in range(1,n+1):
            lista.append(i)  
        a=list(itertools.permutations(lista,2))
        for i in range(1,n+1):
            a.append((i,i))
        return a.copy()

    def addMove(self,a):
        x=-1
        y=-1
        maxim=10
        netxMove=a[0]
        path=self.path.copy()
        for i in range (0,self.n):
            for j in range(0,self.n):
                
                if self.path[i][j]==(0,0):
                    x=i
                    y=j
                    break
            
            if x!=-1 and y!=-1:
                break
                
        for i in range(len(a)):
            nextMove=random.choice(a)            
            path[x][y]=nextMove
            pheromone=self.trail(nextMove,path)
            if pheromone>maxim:
                 maxim=pheromone
                 netxMove=nextMove
        self.path[x][y]=netxMove 
    def trail(self,a,path):
        x=0
        y=0
        trace=self.n*self.n
        for i in range(self.n-1,0,-1):
            for j in range(self.n-1,0,-1):
                if path[i][j]==a:
                    x=i
                    y=j     
        for i in range(0,x):
            for j in range(0,y):
                if path[i][j]==a:
                    trace=trace-1
        for i in range(0,self.n):
            if path[x][i][0]==a[0]:
                trace-=1
            if path[x][i][1]==a[1]:
                trace-=1
            if path[i][y][0]==a[0]:
                trace-=1
            if path[i][y][1]==a[1]:
                trace-=1
                    
        return trace

    def fitness(self):
        
        fitness=0
        for i in range(self.n):
            for j in range(0,self.n-1):
                for k in range(j+1,self.n):
                    if self.path[i][j]==self.path[i][k]:
                        fitness=fitness+1
                    if self.path[i][j][1]==self.path[i][k][1]:
                        fitness=fitness+1
                    if self.path[i][j][0]==self.path[i][k][0]:
                        fitness=fitness+1
        for i in range(self.n):
            for j in range(0,self.n-1):
                for k in range(i+1,self.n):
                    if self.path[i][j][1]==self.path[k][j][1]:
                        fitness=fitness+1
                    if self.path[i][j][0]==self.path[k][j][0]:
                        fitness=fitness+1
                    
        return fitness
    
    def __str__(self):
        return str(self.path)
    
def epoca(n,no):
    
    l=[ant(n) for i in range(no)]
    
    for a in l:
        for i in range(n*n):
            a.addMove(a.nextMoves(n))
            
    sortedL=[[ant,ant.fitness()]for ant in l]
    sortedL.sort(key=lambda ant:ant[1])
           
    l=[a[0] for a in sortedL]
        
    return l[0],l[0].fitness()
        
    

def val(n=4,no=100):
    fitnesses=[]
    for i in range(30):
        resultIndv,bestFitness=epoca(n,no)
        fitnesses.append(bestFitness)
    std=np.std(fitnesses)
    avg=sum(fitnesses)/len(fitnesses)
    print("Standard:" +str(std))
    print("Avg: "+ str(avg))
    
    
def main():
    n=int(input("Size: "))    
    no=int(input("Ants: "))
    print(epoca(n,no)[0].__str__())
    val()

main()