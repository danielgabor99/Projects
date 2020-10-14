# -*- coding: utf-8 -*-
"""
Created on Tue May 20 23:13:55 2020

@author: Daniel
"""
class FuzzySystem:
    """
        Receives variable descriptions and rules and outputs the defuzzified result of the system
    """

    def __init__(self, rules):
        self.inp = {}
        self.outp = None
        self.rules = rules

    def add_description(self, name, descr, out=False):
        """
        description with a name,desc,out
        """
        if out:
            if self.outp is None:
                self.outp = descr
            else:
                pass
        else:
            self.inp[name] = descr

    def compute(self, inputs):
        fuzzy_vals = self.result(inputs)
        rule_vals = self.result_rules(fuzzy_vals)

        fuzzy_out_vars = [(list(descr[0].values())[0], descr[1]) for descr in
                          rule_vals]
        total = 0
        weight_sum = 0
        for var in fuzzy_out_vars:
            weight_sum += var[1]
            total += self.outp.Defuzzify(*var) * var[1]

        return total / weight_sum

    def result(self, inputs):
        return {
            var_name: self.inp[var_name].Fuzzify(inputs[var_name])
            for var_name, val in inputs.items()
        }

    def result_rules(self, fuzzy_vals):
        """
            Returns the fuzzy output of all rules
        """
        return [rule.compute(fuzzy_vals) for rule in self.rules
                if rule.compute(fuzzy_vals)[1] != 0]