from enum import Enum

import namegenerator


class EventType(Enum):
    CHEGADA_1 = 1
    CHEGADA_2 = 2
    FIM_DE_SERVICO = 3


class Event:
    def __init__(self, event_type: EventType, name: str = None):
        self.name = name if name else namegenerator.gen()
        self.type = event_type

    def __str__(self):
        _str = '<Event name='
        _str += self.name + ' type='
        _str += self.type.name + '>'
        return _str

    def __lt__(self, other_event):
        return False
