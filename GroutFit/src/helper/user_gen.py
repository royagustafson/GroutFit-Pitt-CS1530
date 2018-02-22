
import random
import sys

class UserGen:

    def __init__(self, count=200, seed=None):
        self.count = 200

        if not seed:
            random.seed(sys.curr)
        self.rand = random


    def first_names(self, count=200):
        print("Blah")

    def last_names(self, count=200):
        print("Last")