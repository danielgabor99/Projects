# -*- coding: utf-8 -*-
"""
Created on Fri Mar 20 18:32:38 2020

@author: Daniel
"""
from random import randint, random
from operator import add
from math import cos, pi
from numpy import random as np
from itertools import permutations 
from qtpy import QtGui
class Problem:
    def __init__(self,length):
        self.length=length
    def getLength(self):
        return self.length
    def line(self,length):
        #creating a good line
        #creating the list of length n
        p=[]
        for x in range(1,length+1):
            p.append(x)
        perm=list(permutations(p))#list of permutations
        
        s=randint(1,len(perm)-1)
        t=randint(1,len(perm)-1)
        line=[]
        for pos in range(length):
          line.append((perm[s][pos],perm[t][pos]))
        return line
        
    def individual(self,length):
        '''
        Create a member of the population - an individual
        '''   
        return [self.line(length) for x in range(length)]
    
    def population(self,count):
        """
        Create a number of individuals (i.e. a population).
        """
        return [ self.individual(self.length) for x in range(count) ]
    
    def numberOfDuplicatePairs(self,pair,individual):
        k=0
        for i in individual:
            if pair in i:
                k=k+1
        if k>1:
            return k-1
        else:
            return 0
    def createListOfColumns(self,individual):
        listOfCols=[]
        col=[]
        for i in range(0,len(individual)):
            col=[]
            for j in range(0,len(individual)):
                col.append(individual[j][i][0])
            listOfCols.append(col)
            col=[]
            for j in range(0,len(individual)):
                col.append(individual[j][i][1])
            listOfCols.append(col)
        return listOfCols
    def numberOfBadColumns(self,column,listOfColumns):
        k=0
        for i in listOfColumns:
            if(len(set(i))!=len(i)):
                k=k+1
        if k>1:
            return k-1
        else:
            return 0
    def fitness(self,individual):
        """
        Determine the number of duplicates and nr of equal columns
        """
        contor=0
        #first we count the number of duplicates
        setl=[]
        for i in individual:
            for pair in i:
                if pair not in setl:
                    contor=contor+self.numberOfDuplicatePairs(pair, individual)
                    setl.append(pair)
        #next we count the number of bad columns
        setC=[]
        for i in self.createListOfColumns(individual):
                if i not in setC:
                    contor=contor+self.numberOfBadColumns(i, self.createListOfColumns(individual))
                    setl.append(pair)
        return contor
    
    def mutate(self,individual, pM): 
        '''
        Performs a mutation on an individual with the probability of pM.
        If the event will take place, at a random line a new value will be
        generated
        '''
        l=[]
        for x in range(1,len(individual)+1):
            l.append(x)
        perm=list(permutations(l))#list of permutations
        if pM > random():
                p = randint(0, len(individual)-1)
                line=[]
                for pos in range(len(individual)):
                    line.append((perm[p][pos],perm[p][pos]))
                individual[p] = line
        return individual
    
    def crossover(self,parent1, parent2):
        '''
        crossover between 2 parents
        '''
        child=[]
        alpha=random()
        mid=randint(1,len(parent1))
        for x in range(len(parent1)):
            if(x<=mid):
                child.append(parent1[x])
            else:
                child.append(parent2[x])
        return child
    
    def iteration(self,pop, pM):
        '''
        an iteration
        pop: the current population
        pM: the probability the mutation to occure
        vmin: the minimum possible value 
        vmax: the maximum possible value
        '''
        i1=randint(0,len(pop)-1)
        i2=randint(0,len(pop)-1)
        if (i1!=i2):
            c=self.crossover(pop[i1],pop[i2])
            c=self.mutate(c, pM)
            f1=self.fitness(pop[i1])
            f2=self.fitness(pop[i2])
    
            fc=self.fitness(c)
            if(f1>f2) and (f1>fc):
                pop[i1]=c
            if(f2>f1) and (f2>fc):
                pop[i2]=c
        return pop