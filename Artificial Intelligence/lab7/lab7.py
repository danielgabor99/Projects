# -*- coding: utf-8 -*-
import numpy as np
import pandas as pd
def computeCost(X,Y,theta):
    np.seterr(all='ignore')
    inner = np.power(((X * theta.T) - Y), 2)
    number = np.sum(inner) / (2 * len(X))
    return number*100000000


def gradientDescent(X, y, theta, alpha, iters):
    temp = np.matrix(np.zeros(theta.shape))
    parameters = int(theta.ravel().shape[1])
    cost = np.zeros(iters)
   
    for i in range(iters):
        error = (X * theta.T) - y
        for j in range(parameters):
            term = np.multiply(error, X[:,j])
            temp[0,j] = theta[0,j] - ((alpha / len(X)) * np.sum(term))
          
        theta = temp
        cost[i] = computeCost(X, y, theta)
       
    return theta, cost


def main():
    data = pd.read_csv('file.txt',"\t", names=['1', '2', '3','4','5','6'])
    data.head()

    data= (data - data.mean()) / data.std()
    data.head()
    data.insert(0, 'Ones', 1)    
    cols = data.shape[1]
    X2 = data.iloc[:,1:cols-1]
    
    y2 = data.iloc[:,cols-1:cols]
    

    X2 = np.matrix(X2.values)
    y2 = np.matrix(y2.values)    
    theta= np.matrix(np.array([0,0,0,0,0])) 
    alpha=float(input("alpha: "))
    iters=int(input("iterations: "))
    g2, cost2 = gradientDescent(X2, y2, theta, alpha, iters)
    finalCost=computeCost(X2, y2, g2)
    print("Theta: "+str(g2))
    print("Cost: "+str(finalCost))
    
main()