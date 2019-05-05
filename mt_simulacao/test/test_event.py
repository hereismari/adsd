import pytest

from src.event import Event
from src.event import EventType

def test_comparisson():
    event1 = Event(event_type=EventType.CHEGADA_1, name='event1')
    event2 = Event(event_type=EventType.CHEGADA_2, name='event2')
    event3 = Event(event_type=EventType.FIM_DE_SERVICO, name='event3')
    event4 = Event(event_type=EventType.FIM_DE_SERVICO, name='event4')
    event5 = Event(event_type=EventType.CHEGADA_2, name='event5')
    event6 = Event(event_type=EventType.CHEGADA_1, name='event6')

    events = [event1, event2, event3, event4, event5, event6]
    
    expected_sorted_events = [event6, event1, event2, event5, event3, event4]
    sorted_events = sorted(events)
    
    assert sorted_events == expected_sorted_events