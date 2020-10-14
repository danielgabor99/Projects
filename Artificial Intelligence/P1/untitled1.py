# -*- coding: utf-8 -*-
"""
Created on Tue Feb 25 15:22:31 2020

@author: Daniel
"""
import random
from copy import copy, deepcopy
def sudoku():
    print("the range:")
    n=int(input())
    print("nr of attempts:")
    nr=int(input())
    A=[]
    for i in range(0,n):
        r=[]
        for j in range(0,n):
            r.append(int(input()))
        A.append(r)

    k=1
    while(k<=nr):
        copy=deepcopy(A)
        for i in range(0,n):
            for j in range(0,n):
                if copy[i][j]==0:
                    copy[i][j]=random.randrange(1,n+1);

        verifyline=0
        for line in range(0,n):
            for i in range(0,n):
                for j in range(i+1,n):
                    if copy[line][i]==copy[line][j]:
                        verifyline=1
        verifycol=0
        for col in range(0,n):
            for i in range(0,n):
                for j in range(i+1,n):
                    if copy[i][col]==copy[j][col]:
                        verifycol=1                        
        if verifyline==0 and verifycol==0:
            print("The good solution on attempt",k,"is:")
            for row in copy:
                print(row)
            break
        else:
            print("nr attempt",k,"is:")
            for row in copy:
                print(row)
        k=k+1
        
def insertPiece(g,p):
    x=random.randrange(0,4)
    y=random.randrange(0,3)
    if(x+2<=5 and y+4<=6):
        for i in range(0,2):
            for j in range(0,4):
                g[x+i][j+y]=g[x+i][j+y]+p[i][j]
    return g
def verifyIfCorrect(g):
    for i in range(0,5):
        for j in range(0,6):
            if g[i][j]!=0 and g[i][j]!=1:
                return False
    return True
def geometricforms():
    print("nr of attempts:")
    nr=int(input())
    g=[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0]
    for row in g:
        print(row)
    p1=[1,1,1,1],[0,0,0,0]
    p2=[1,0,1,0],[1,1,1,0]
    p3=[1,0,0,0],[1,1,1,0]
    p4=[1,1,1,0],[0,0,1,0]
    p5=[0,1,0,0],[1,1,1,0]
    
    print("\n\n")
    k=1
    while k<=nr:
        g2=deepcopy(g)
        g2=insertPiece(g2,p1);
        g2=insertPiece(g2,p2);
        g2=insertPiece(g2,p3);
        g2=insertPiece(g2,p4);
        g2=insertPiece(g2,p5);
        if verifyIfCorrect(g2)==True:
            print("the correct sol is:")
            for row in g2:
                print(row)
            break
        print("nr attempt",k,"is:")
        for row in g2:
            print(row)
        k=k+1
        
        
        
        
def main():
    print("Choose a game:\n1-sudoku\n2-\n3-geometric forms")
    i=int(input())
    if i==1:
        sudoku()
    else:
        if i==3:
            geometricforms()
    
main()




