# -*- coding: utf-8 -*-
"""
Created on Tue Mar 10 08:48:11 2020

@author: Daniel
"""
from time import time


class Configuration:
    '''
    holds a configuration of a matrix -
    a list of n elements representing the number of the row on which there is
    a queen (0 if none), for each column (the matrix is filled with 0 on the
    remaining positions)
    '''

    def __init__(self, positions):
        self.__size = len(positions)
        self.__values = positions[:]

    def getSize(self):
        return self.__size

    def getValues(self):
        return self.__values[:]

    def nextConfig(self):
        '''
        places a queen on the first emtpy column on the proper position(s)
        in: -
        out: the list of the next correct configurations obtained
        '''

        column = 99
        for j in range(self.__size):
            if self.__values[j] == 0:
                column = j
                break

        if column == -99:
            return []  # there are no empty columns left

        nextC = []
        for i in range(self.__values[column] + 1, self.__size + 1):
            if i in self.__values:  # there is a queen on the same row
                continue
            ok = True
            for el in self.__values[:column]:
                if abs(self.__values.index(el) - column) == abs(el - i):
                    # there is a queen  on the same diagonal
                    ok = False
            if ok:
                nextC.append(Configuration(self.__values[:column] + [i] + \
                                           self.__values[column + 1:]))
        return nextC

    def __eq__(self, other):
        if not isinstance(other, Configuration):
            return False
        if self.__size != other.getSize():
            return False
        for i in range(self.__size):
            if self.__values[i] != other.getValues()[i]:
                return False
        return True

    def __str__(self):
        return str(self.__values)

    def __repr__(self):
        return str(self.__values)


class State:
    """
    holds a PATH of configurations
    """

    def __init__(self):
        self.__values = []

    def setValues(self, values):
        self.__values = values[:]

    def getValues(self):
        return self.__values[:]

    def __str__(self):
        s = '\n [\n'
        for x in self.__values:
            s +=str(x) + ",\n"
        return s + " ]\n"

    def __repr__(self):
        s = '\n [\n'
        for x in self.__values:
            s += "   " + str(x) + ",\n"
        return s + " ]\n"

    def __add__(self, something):
        aux = State()
        if isinstance(something, State):
            aux.setValues(self.__values + something.getValues())
        elif isinstance(something, Configuration):
            aux.setValues(self.__values + [something])
        else:
            aux.setValues(self.__values)
        return aux


class Problem:
    def __init__(self, initial):
        self.__initialConfig = initial
        self.__initialState = State()
        self.__initialState.setValues([self.__initialConfig])
        self.__matrixSize = len(initial.getValues())

    def isSol(self, config):
        for x in config.getValues():
            if x == 0:
                return False
        return True

    def expand(self, currentState):
        myList = []
        currentConfig = currentState.getValues()[-1]
        for x in currentConfig.nextConfig():
            myList.append(currentState + x)
        return myList

    def getRoot(self):
        return self.__initialState

    def getMatrixSize(self):
        return self.__matrixSize

    def heuristics(self, state):
        nrfreepos = 0
        positions = state.getValues()[-1].getValues()
        for j in range(self.__matrixSize):
            if positions[j] == 0:
                for i in range(1, self.__matrixSize + 1):
                    if i not in positions:  # no other queen on the same line
                        ok = 1
                        for j1 in range(j):
                            if abs(j1 - j) == abs(positions[j1] - positions[j]):
                                ok = 0  # there is a queen on the same diagonal
                        nrfreepos += ok
        return nrfreepos

class Controller:
    def __init__(self, problem):
        self.__instance = problem

    def DFS(self, root):
        q = [root]
        while len(q) > 0:
            currentState = q.pop(0)
            currentCfg = currentState.getValues()[-1]
            if self.__instance.isSol(currentCfg):
                return currentCfg.getValues()
            q = self.__instance.expand(currentState) + q
        return []

    def greedy(self, root):
        toVisit = [root]
        visited = []
        while len(toVisit) > 0:
            node = toVisit.pop(0)
            visited = visited + [node]
            currentCfg = node.getValues()[-1]
            if self.__instance.isSol(currentCfg):
                return currentCfg
            aux = []
            for x in self.__instance.expand(node):
                if x not in visited:
                    aux.append(x)
                    break

            aux = [[x, self.__instance.heuristics(x)] for x in aux]
            aux.sort(key=lambda el: el[1])
            aux = [x[0] for x in aux]

            toVisit = aux[:] + toVisit


class UI:
    def __init__(self):
        self.__iniC = Configuration([0, 0, 0, 0])
        self.__p = Problem(self.__iniC)
        self.__contr = Controller(self.__p)

    def printMainMenu(self):
        s = ''
        s += "1 - SIZE OF THE MATRIX\n"
        s += "2 - DFS\n"
        s += "3 - Greedy \n"
        s += "0 - exit \n"
        print(s)

    def readConfigSubMenu(self):
        prev_n = self.__p.getMatrixSize()
        try:
            print("Input the size of the matrix (now the n=" + str(prev_n) + ")")
            n = int(input("n = "))
        except:
            n = prev_n
            print("invalid number, n is still " + str(n))

        self.__iniC = Configuration([0] * n)
        self.__p = Problem(self.__iniC)
        self.__contr = Controller(self.__p)

    def findUsingDFS(self):
        res = self.__contr.DFS(self.__p.getRoot())
        if not res:
            print("No solutions were found for n = " + str(self.__p.getMatrixSize()) + ".\n")
        else:
            print(str(res))

    def findUsingGreedy(self):
        res = self.__contr.greedy(self.__p.getRoot())
        if not res:
            print("No solutions were found for n = " + str(self.__p.getMatrixSize()) + ".\n")
        else:
            print(str(res))
        

    def run(self):
        runM = True
        while runM:
            try:
                self.printMainMenu()
                command = int(input(">> "))
                if command == 0:
                    runM = False
                elif command == 1:
                    self.readConfigSubMenu()
                elif command == 2:
                    self.findUsingDFS()
                elif command == 3:
                    self.findUsingGreedy()
                else:
                    print('Invalid')
            except Exception as e:
                print("Error! " + str(e))


def runTests():
    c = Configuration([2, 4, 0, 0])
    s = State()
    p = Problem(c)

    # Configuration
    assert (c.getSize() == 4)
    assert (c.getValues() == [2, 4, 0, 0])
    assert (c.nextConfig() == [Configuration([2, 4, 1, 0])])

    # State
    assert (s.getValues() == [])
    s = s + 'something random'
    assert (s.getValues() == [])
    s = s + c
    assert (s.getValues() == [c])

    # Problem
    aux = p.expand(s)
    assert (len(aux) == 1)
    assert (aux[0].getValues()[-1] == Configuration([2, 4, 1, 0]))

    print('\nTests passed.')


def main():
    runTests()
    ui = UI()
    ui.run()


main()
