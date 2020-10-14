# -*- coding: utf-8 -*-
from controller import Controller
from repository import Repository

def main():
    
    repository = Repository()
    controller = Controller(repository)
    controller.run()
    
main()