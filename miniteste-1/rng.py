# coding: utf-8

import numpy as np


def generate_value(lower_bound, upper_bound):
    value = np.random.uniform(lower_bound, upper_bound + 1)
    return int(value)
