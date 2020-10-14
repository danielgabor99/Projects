# -*- coding: utf-8 -*-
"""
Created on Fri Mar 20 18:35:04 2020

@author: Daniel
"""
from PSO import *
from Controller import *
from PyQt5 import QtWidgets, QtGui, QtCore
from PyQt5.QtWidgets import QMainWindow, QApplication,QLabel,QTableWidgetItem,QVBoxLayout,QLineEdit,QInputDialog,QPushButton,QTextEdit
import sys
import time
class MainWindow(QMainWindow): 
    def __init__(self):
        QMainWindow.__init__(self)
        
        self.matrix=[]
        self.setGeometry(400,300,1000,600)
        self.setWindowTitle("Euler Square")
     
        self.length=QLineEdit(self)
        self.length.setGeometry(0,400,100,40)
        self.length.setPlaceholderText("Set length")
        self.pop=QLineEdit(self)
        self.pop.setGeometry(100,400,100,40)
        self.pop.setPlaceholderText("Set Population")
        self.pM=QLineEdit(self)
        self.pM.setGeometry(200,400,100,40)
        self.pM.setPlaceholderText("Set pM")
        button=QPushButton("Evolutionary Algorithm",self)
        button.setGeometry(0,450,150,50)
        buttonHc=QPushButton("HC Algorithm",self)
        buttonHc.setGeometry(270,450,150,50)
        buttonPSO=QPushButton("PSO",self)
        buttonPSO.setGeometry(400,450,150,50)
        self.StopButton=QPushButton("STOP",self)
        self.StopButton.setGeometry(140,450,150,50)
        self.labelnr=QLabel(self)
        self.labelnr.setGeometry(5,250,500,50)
        self.labelnr.setText("Number of individuals left")
        newfont = QtGui.QFont("Times", 14, QtGui.QFont.Normal) 
        self.labelnr.setFont(newfont)
        self.labelFitness=QLabel(self)
        self.labelFitness.setGeometry(500,5,500,50)
        self.labelFitness.setText("Fitness")
        newfont = QtGui.QFont("Times", 17, QtGui.QFont.Normal) 
        self.labelFitness.setFont(newfont)
        self.labelBFitness=QLabel(self)
        self.labelBFitness.setGeometry(500,50,500,50)
        self.labelBFitness.setText("Best Fitness found")
        self.labelBFitness.setFont(newfont)
        self.table=QtWidgets.QTableWidget(self)
        self.table.setGeometry(0,0,500,250)
        self.table.setFont(newfont)        
        self.killLoopFlag_ = True
        self.StopButton.clicked.connect(self.killLoop)
        button.clicked.connect(self.ea)
        buttonHc.clicked.connect(self.hc)
        buttonPSO.clicked.connect(self.pso)
        #pentru ea
        
    def killLoop(self):
        self.killLoopFlag_ = True
    def hc(self):
        problem=Problem(int(self.length.text()))
        ctrl=Controller(problem)
        population=ctrl.problem.population(int(self.pop.text()))
        self.table.setRowCount(ctrl.problem.getLength())
        self.table.setColumnCount(ctrl.problem.getLength())
        random=randint(0, ctrl.problem.getLength())
        
        solfit=ctrl.problem.fitness(population[random])
        L=random-1
        R=random+1
        fitneighL=ctrl.problem.fitness(population[L])
        fitneighR=ctrl.problem.fitness(population[R])
        aux=population[random]
        while solfit<=fitneighL or solfit<=fitneighR:
            QApplication.processEvents()
            QtCore.QThread.msleep(1000)
            if(self.killLoopFlag_):
                break  
            if solfit<=fitneighL:
                solfit=fitneighL
                L=L-1
                R=L+1
                aux=population[L]
                fitneighL=ctrl.problem.fitness(population[L])
                fitneighR=ctrl.problem.fitness(population[R])
                self.change(aux,ctrl.problem.getLength())
            else:
                if solfit<=fitneighR:
                    solfit=fitneighR
                    R=R+1
                    L=R-1
                    aux=population[R]
                    fitneighL=ctrl.problem.fitness(population[L])
                    fitneighR=ctrl.problem.fitness(population[R])
                    self.change(aux,ctrl.problem.getLength())
        self.showBestSol(aux,solfit,ctrl.problem.getLength())        
        
    def ea(self):   
        problem=Problem(int(self.length.text()))
        ctrl=Controller(problem)
        
        self.table.setRowCount(ctrl.problem.getLength())
        self.table.setColumnCount(ctrl.problem.getLength())
        
        initialpop=ctrl.problem.population(int(self.pop.text()))
        m=ctrl.problem.iteration(initialpop, float(self.pM.text()))    
        bestFitness=999
        self.killLoopFlag_ = False
        for i in range(0,len(m)):
            fitn=ctrl.problem.fitness(m[i])
            if(fitn<bestFitness):
                bestFitness=fitn
                bestSol=m[i]
            self.labelnr.setText("Number of individuals left: "+str(len(m)-i))
            self.labelFitness.setText("Fitness for this individual: "+str(fitn))
            self.labelBFitness.setText("Best Fitness found: "+str(bestFitness))
            if(self.killLoopFlag_):
                break  
            QApplication.processEvents()
            QtCore.QThread.msleep(100)
            self.change(m[i],ctrl.problem.getLength())
        self.showBestSol(bestSol,bestFitness,ctrl.problem.getLength())

    def pso(self):
        pso=particle(int(self.length.text()))
        self.table.setRowCount(int(self.length.text()))
        self.table.setColumnCount(int(self.length.text()))
        initialpop=pso.population(int(self.length.text()),int(self.pop.text()))
        m=ctrl.problem.iteration(initialpop, float(self.pM.text())) 
        bestFitness=999
        for i in range(0,len(m)):
            fit=m[i].evaluate()
            neigh=m[i].selectNeighbors()
            if(fit<bestFitness):
                bestFitness=fitn
                bestSol=m[i]
            else:
                fit=neigh.evaluate()
        self.showBestSol(bestSol,bestFitness,self.length.text())
                     
    def showBestSol(self,m,fit,length):
        self.change(m,length)
        self.labelnr.setText("This is the best solution found!!!")
        self.labelFitness.setText("Fitness for this individual: "+str(fit))
        self.labelBFitness.setText("Best Fitness found: "+str(fit))
            

    def change(self,m,length):
        for j in range(0,length):
            for i in range(0,length):
                l=m[i][j]
                self.table.setItem(i,j,QTableWidgetItem(l.__repr__())) 
      
    def validationTests(self):
        import matplotlib.pyplot as plt
        problem=Problem(3)
        ctrl=Controller(problem)
        pop=ctrl.problem.population(40)
        assert(len(pop)==40)
        evaluations=[]
        for i in range(0,30):
            P=ctrl.problem.iteration(pop,0.1)
            evaluations.append(P)  
        graded = [ (ctrl.problem.fitness(x), x) for i in range(0,30)for x in evaluations[i]]
        graded =  sorted(graded,key=lambda tup:tup[0])
        plt.plot(list(range(1,len(graded)+1)),list(graded[x][0]for x in range(len(graded))))
        plt.ylabel('Fitness')
        plt.xlabel('Evaluations')
        plt.show()
        
        
 

    
app = QtWidgets.QApplication(sys.argv)
mainWin = MainWindow()
mainWin.show()
sys.exit( app.exec_() )

