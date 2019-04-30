import enum

import nameganerator


class EventType(enum):
    START_QUEUE1 = 1
    START_QUEUE2 = 2
    END_QUEUE = 3
    FINISH = 4


class Event:
    def __init__(self, event_type:EventType, name:str=None):
        self.name = name if name else nameganerator.gen()
        self.type = event_type

