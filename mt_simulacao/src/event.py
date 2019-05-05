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

    def __repr__(self):
        _str = '<Event name='
        _str += self.name + ' type='
        _str += self.type.name + '>'
        return _str

    def __lt__(self, other_event):
        # EventType.CHEGADA_1 should be always executed first
        if self.type == EventType.CHEGADA_1:
            return True
        # EventType.CHEGADA_2 should be executed first only if other type is EventType.FIM_DE_SERVICO
        elif self.type == EventType.CHEGADA_2 and other_event.type == EventType.FIM_DE_SERVICO:
            return True
        else:
            return False
    
    def __eq__(self, other_event):
        return self.name == other_event.name and self.type == other_event.type
