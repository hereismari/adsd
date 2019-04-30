# coding: utf-8
from abc import ABC, abstractmethod


class RandomNumberGenerator(ABC):
    """A random number generator interface."""

    def __init__(self, seed=333):
        self.seed = seed

    @abstractmethod
    def sample(self, is_discrete:bool = True):
        """Sample a value from the generator.
        
        Args:
            is_discrete: If True samples from a discrete distribution,
            otherwise samples from a continous distribution.
        """
        pass


class MixedCongruentialGenerator(RandomNumberGenerator):
    """Mixed Congruential Generator.

    If one chooses the values of `multi_factor`, `add_factor` and `mod`
    with care, then the generator produces a uniform distribution of
    integers from 0 to `mod` - 1.
    """
    def __init__(self, multi_factor:int = 1103515245, add_factor:int = 12345, mod:int = 8765, seed=333):
        super().__init__(seed)
        if multi_factor >= mod or add_factor >= mod:
            raise ValueError('multi_factor e add_factor não'
                             'devem ser maiores que o mod.')
        self.multi_factor = multi_factor
        self.add_factor = add_factor
        self.mod = mod
        self.previous_value = seed

    def sample(self):
        sampled_value = ((self.multi_factor * self.previous_value) + self.add_factor) % self.mod
        self.previous_value = sampled_value
        return sampled_value


class UniformDistribution(RandomNumberGenerator):
    """Uniform distribution random number generator."""

    def __init__(self, lower_bound:int, upper_bound:int, seed:int):
        super().__init__(self)
        self.lower_bound = lower_bound
        self.upper_bound = upper_bound
        
        self.mod = (lower_bound - upper_bound) + 1
        self.seed = seed
        self.mcg = MixedCongruentialGenerator(seed=self.seed, mod=self.mod)

    def sample(self):
        return self.mcg.sample() + self.lower_bound