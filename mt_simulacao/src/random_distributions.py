import numpy as np


class UniformRandomNumberGenerator:
    """Uniform distribution random number generator."""

    def __init__(self, lower_bound:int, upper_bound:int):
        super().__init__(self)
        self.lower_bound = lower_bound
        self.upper_bound = upper_bound

    def sample(self, is_discrete=True):
        """Sample a value from the generator.
        
        Args:
            is_discrete: If True samples from a discrete distribution,
            otherwise samples from a continous distribution.
        """
        if is_discrete:
            
        else:
            return np.random.uniform(self.lower_bound, self.upper_bound + 1)
