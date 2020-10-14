# -*- coding: utf-8 -*-

import numpy as np

class NeuronalNetwork:

    def __init__(self, x, y, hidden):
        self.input = x
        self.W = np.random.rand(self.input.shape[1], hidden)
        self.W2 = np.random.rand(hidden, 1)
        self.y = y
        self.output = np.zeros(self.y.shape)
        self.loss = []

    def linearFunction(self, x):
        return x

    def linearDerivative(self, x):
        return 1

    def feedforward(self):

        self.layer = self.linearFunction(np.dot(self.input, self.W))
        self.output = self.linearFunction(np.dot(self.layer, self.W2))

 
    def backprop(self, l_rate):

        d_W2 = np.dot(self.layer.T, (2 * (self.y - self.output) *self.linearDerivative(self.output)))

        d_W = np.dot(self.input.T,  (np.dot(2*(self.y - self.output) *self.linearDerivative(self.output), self.W2.T) * self.linearDerivative(self.layer)))
        # update the weights with the derivative of the loss function

        self.W += l_rate * d_W
        self.W2 += l_rate * d_W2
        
        self.loss.append(sum((self.y - self.output) ** 2))
