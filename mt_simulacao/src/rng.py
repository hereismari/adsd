# coding: utf-8
from abc import ABC, abstractmethod

import numpy as np
np.random.seed(7)


class RandomNumberGenerator(ABC):
    """A random number generator interface."""

    def __init__(self, seed: int = 333):
        self.seed = seed

    @abstractmethod
    def sample(self):
        """Sample a value from the generator."""
        pass


class MixedCongruentialGenerator(RandomNumberGenerator):
    """Mixed Congruential Generator.

    If one chooses the values of `multi_factor`, `add_factor` and `mod`
    with care, then the generator produces a uniform distribution of
    integers from 0 to `mod` - 1.

    Assumption: mod > 0 and multi_factor < mod, add_factor < mod, X0 < mod.

    The LCG has full period if and only if the following three
    conditions hold (Hull and Dobell, 1962):
        1. The only positive integer that (exactly) divides both mod and add_factor
        is 1
        2. If q is a prime number that divides mod, then q divides multi_factor-1
        3. If 4 divides mod, then 4 divides multi_factor-1 
    """
    def __init__(self, multi_factor: int = 1103515245,
                 add_factor: int = 12345, mod: int = 8765, seed=333):
        super().__init__(seed)
        self.multi_factor = multi_factor
        self.add_factor = add_factor
        self.mod = mod
        self.previous_value = seed

    def sample(self):
        sampled_value = ((self.multi_factor * self.previous_value) + self.add_factor) % self.mod
        self.previous_value = sampled_value
        return sampled_value


class UniformDistributionMCG(RandomNumberGenerator):
    """Uniform distribution random number generator."""

    def __init__(self, lower_bound: int, upper_bound: int, **kwargs):
        super().__init__(self)
        self.lower_bound = lower_bound
        self.upper_bound = upper_bound

        self.mod = (upper_bound - lower_bound) + 1
        self.mcg = MixedCongruentialGenerator(mod=self.mod, **kwargs)

    def sample(self):
        return self.mcg.sample() + self.lower_bound


class UniformDistribution(RandomNumberGenerator):
    """Uniform distribution random number generator."""

    def __init__(self, lower_bound: int, upper_bound: int):
        super().__init__(self)
        self.lower_bound = lower_bound
        self.upper_bound = upper_bound
        
    def sample(self):
        return np.random.randint(low=self.lower_bound, high=(self.upper_bound + 1))
