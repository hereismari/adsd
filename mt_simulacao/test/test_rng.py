import pytest

from src.rng import MixedCongruentialGenerator


def test_simple_mcg():
    mcg = MixedCongruentialGenerator(1, 2, 3, seed=4)
    assert mcg.sample() == 0
    assert mcg.sample() == 2
    assert mcg.sample() == 1
