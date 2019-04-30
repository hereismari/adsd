import pytest

from src.rng import MixedCongruentialGenerator


def test_invalid_mcg():
    with pytest.raises(ValueError):
        _ = MixedCongruentialGenerator(10, 10, 1)


def test_simple_mcg():
    mcg = MixedCongruentialGenerator(1, 2, 3, seed=4)
    assert mcg.sample() == 0
    assert mcg.sample() == 2
    assert mcg.sample() == 1
