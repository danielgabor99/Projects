# -*- coding: utf-8 -*-
"""
Created on Mon Mar 23 19:58:57 2020

@author: Daniel
"""
from itertools import permutations 
from numpy import random as np
from random import randint, random
class particle:
    """ The class that implements a particle """
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
    def __init__(self, l):
        """ constructor
        input--
          l: the number of components
          vmin: the minimum possible value 
          vmax: the maximum possible value
        """
        self._position = [self.line(l) for x in range(l)]
        self.velocity = [ 0 for i in range(l)]
        
        #the memory of that particle
        self._bestPosition=self._position.copy()
        #self._bestFitness=self._fitness
        
        #for the fitness
    def numberOfDuplicatePairs(self,pair,position):
        k=0
        for i in position:
            if pair in i:
                k=k+1
        if k>1:
            return k-1
        else:
            return 0
        #for the fitness
    def createListOfColumns(self,position):
        listOfCols=[]
        col=[]
        for i in range(0,len(position)):
            col=[]
            for j in range(0,len(position)):
                col.append(position[j][i][0])
            listOfCols.append(col)
            col=[]
            for j in range(0,len(position)):
                col.append(position[j][i][1])
            listOfCols.append(col)
        return listOfCols
        #for the fitness
    def numberOfBadColumns(self,column,listOfColumns):
        k=0
        for i in listOfColumns:
            if(len(set(i))!=len(i)):
                k=k+1
        if k>1:
            return k-1
        else:
            return 0    
    
    def fit(self,position):
        """
        Determine the fitness of a particle. Lower is better.(min problem)
        For this problem we have the Schaffer's function

        input --
            pozition: the position of the particle we wish to evaluate
        """
        contor=0
        #first we count the number of duplicates
        setl=[]
        for i in position:
            for pair in i:
                if pair not in setl:
                    contor=contor+self.numberOfDuplicatePairs(pair, position)
                    setl.append(pair)
        #next we count the number of bad columns
        setC=[]
        for i in self.createListOfColumns(position):
                if i not in setC:
                    contor=contor+self.numberOfBadColumns(i, self.createListOfColumns(position))
                    setl.append(pair)
        return contor
    def evaluate(self):
        """ evaluates the particle """
        self._fitness = self.fit(self._position)

    def getposition(self,length):
        """ getter for position """
        return [self.line(length) for x in range(length)]

    @property
    def fitness(self):
        """ getter for fitness """
        return self._fitness

    @property
    def bestPosition(self):
        """ getter for best position """
        return self._bestPosition

    @property
    def bestFitness(self):
        """getter for best fitness """
        return self._bestFitness
    

    def position(self, newPosition):
        #self._position=newPosition.copy()
        # automatic evaluation of particle's fitness

        # automatic update of particle's memory
        if (self._fitness<self._bestFitness):
            self._bestPosition = self._position
            self._bestFitness  = self._fitness
    def population(self,count, l):
        """
        Create a number of particles (i.e. a population).
    
        input --
           count: the number of individuals in the population
           l: the number of values in the pozition of a particle
           vmin: the minimum possible value 
           vmax: the maximum possible value
    
        output --
           the random created population of count particles
        """
        return [ self.getposition(l) for x in range(count) ]


    def selectNeighbors(self,pop, nSize):
        neighbors=[]
        for i in range(len(pop)):
            localNeighbor=[]
            for j in range(nSize):
                x=randint(0, len(pop)-1)
                while (x in localNeighbor):
                    x=randint(0, len(pop)-1)
                localNeighbor.append(x)
            neighbors.append(localNeighbor.copy())
        return neighbors
            
    def iteration(self,pop, neighbors, c1, c2, w ):
    
        bestNeighbors=[]
        #determine the best neighbor for each particle
        for i in range(len(pop)):
            bestNeighbors.append(neighbors[i][0])
            for j in range(1,len(neighbors[i])):
                if (pop[bestNeighbors[i]].fitness>pop[neighbors[i][j]].fitness):
                    bestNeighbors[i]=neighbors[i][j]
                    
        #update the velocity for each particle
        for i in range(len(pop)):
            for j in range(len(pop[0].velocity)):
                newVelocity = w * pop[i].velocity[j]
                newVelocity = newVelocity + c1*random()*(pop[bestNeighbors[i]].pozition[j]-pop[i].pozition[j])    
                newVelocity = newVelocity + c2*random()*(pop[i].bestPozition[j]-pop[i].pozition[j])
                pop[i].velocity[j]=newVelocity
        
        #update the pozition for each particle
        for i in range(len(pop)):
            newPozition=[]
            for j in range(len(pop[0].velocity)):
                newPozition.append(pop[i].pozition[j]+pop[i].velocity[j])
            pop[i].pozition=newPozition
        return pop