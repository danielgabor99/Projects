# -*- coding: utf-8 -*-
from neuronalNetwork import NeuronalNetwork
from repository import Repository
import matplotlib.pyplot as mpl
import numpy as np

class Controller:
    
    def __init__(self, repository : Repository):
        self.repository = repository

    def plotL(self, iterations,loss):
        mpl.plot(iterations, loss, label='loss value vs iteration')
        mpl.legend()
        mpl.show()
        
    def run(self):
        data = self.repository.data
        xL = []
        yL = []
        for elem in data:
            xL.append(elem[:-1])
            yL.append([elem[-1]])
        x = np.array(xL)
        y = np.array(yL)
        nN = NeuronalNetwork(x, y, 2)
        nN.loss = []
        iterations = []
        for i in range(1000):
            nN.feedforward()
            nN.backprop(0.00000001)
            iterations.append(i)
            
        print("Minimum loss:")
        print(min(nN.loss))
        
        self.plotL(iterations, nN.loss)


