# -*- coding: utf-8 -*-
"""
Created on Thu Apr  9 19:42:21 2020

@author: Daniel
"""

import pandas as pd 
class Node:
    def __init__(self, problem, true_branch, false_branch):
        self.problem = problem
        self.true_branch = true_branch
        self.false_branch = false_branch
def class_counts(rows):
        counts = {}
        for row in rows:
            label = row[-1]
            if label not in counts:
                counts[label] = 0
            counts[label] += 1
            
        return counts

class Leaf:
    def __init__(self, rows):   
        self.predictions = class_counts(rows)
class Problem:  
    def __init__(self, column, value):
        
        self.column = column
        self.value = value
    

    
    def match(self, example):
        
        val = example[self.column]
        return val >= self.value
class Controller:
    
    def __init__(self):
        self.data_set = self.read_dataset()    


    def read_dataset(self):
        data_set = []
        with open("balance-scale.data", 'r') as f:
            for line in f.readlines():
                row = line.split(',')
                label = row.pop(0)
                row = [int(val) for val in row]
                row.append(label)
    
                data_set.append(row)
        return data_set
        
    def get_dataset(self):
        return self.data_set
        
    
    def partition(self,rows, problem):
        
        true_rows, false_rows = [], []
        for row in rows:
            if problem.match(row):
                true_rows.append(row)
            else:
                false_rows.append(row)
                
        return true_rows, false_rows            
                
                
    def gini(self, rows):
        
        counts = class_counts(rows)
        impurity = 1
        for label in counts:
            prob_of_label = counts[label] / float(len(rows))
            impurity -= prob_of_label**2
            
        return impurity
    
    
    def info_gain(self,left, right, current_uncertainty):
        
        p = float(len(left)) / (len(left) + len(right))
        return current_uncertainty - p * self.gini(left) - (1 - p) * self.gini(right)
    
    def find_best_split(self,rows):
        
        best_gain = 0
        best_problem = None
        current_uncertainty = self.gini(rows)
        n_features = len(rows[0])-1
        
        for col in range(n_features):
            
            values = set([row[col] for row in rows])
            
            for val in values:
                
                problem = Problem(col,val)
                true_rows, false_rows = self.partition(rows,problem)
                
            if len(true_rows) == 0 or len(false_rows) == 0:
                continue
            
            gain = self.info_gain(true_rows,false_rows, current_uncertainty)
            
            if gain >= best_gain:
                best_gain, best_problem = gain, problem
                
        return best_gain, best_problem
                
    def classify(self, row, node):
        
        if isinstance(node, Leaf):
            return node.predictions
        
        if node.problem.match(row):
            return self.classify(row, node.true_branch)
        else:
            return self.classify(row, node.false_branch)
    
    def build_tree(self, rows):
    
        gain, problem = self.find_best_split(rows)
        
        if gain == 0:
            return Leaf(rows)
        
        true_rows, false_rows = self.partition(rows,problem)
        true_branch = self.build_tree(true_rows)
        false_branch = self.build_tree(false_rows)
        
        return Node(problem, true_branch, false_branch)
class UI:
    
    def __init__(self):
        self.controller = Controller()
 
    def print_leaf(self,counts):
        total = sum(counts.values())
        probs = {}
        for label in counts.keys():
            probs[label] = str(int(counts[label]/(total+1) * 100))
        return probs[label],total
    
    def print_menu(self):
        print("1. Show predicitions")
    
    def run(self):
        
        my_tree = self.controller.build_tree(self.controller.get_dataset())
        self.print_menu()
        x = int(input())
        if x == 1:
            s=0
            for row in self.controller.get_dataset():
                s=s+int(self.print_leaf(self.controller.classify(row,my_tree))[0])
            print(str(s/len(self.controller.get_dataset()))+"%")

if __name__ == "__main__":   
    ui = UI()
    ui.run()
                                         





