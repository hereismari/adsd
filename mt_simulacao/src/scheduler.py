import enum
from heapq import *
import logging
import time

from src.event import Event
from src.event import EventType

from src.rng import UniformDistribution


class SchedulerState(enum):
    FREE = 1
    BUSY = 2
    FINISHED = 3


class Scheduler:

    def __init__(self, simulation_speed:int = 1, service_time:int = 3):
        self.simulation_speed = simulation_speed
        self.service_time = service_time

        self.current_customer = 0
        self.state = SchedulerState.FREE
        self.element_in_service = None
        self.timeline = []

        # Type 1
        self.queue1 = []
        self.uniform1 = UniformDistribution(seed=1, lower_bound=1, upper_bound=12)

        # Type 2
        self.queue2 = []
        self.uniform2 = UniformDistribution(seed=1, lower_bound=1, upper_bound=4)


    def schedule_event(self, event_time, event):
        heappush(self.timeline, (event_time, event))

    def run(self, run_time):
        seconds = 0
        while seconds < run_time and self.state != SchedulerState.FINISHED:
            time.sleep(1)
            seconds += 1
            self.check_state(seconds)

    def log_event(self, event, seconds):
        logging.log('Nome do evento: {}, Tipo de evento: {}, Momento do evento: {}'.format(
            event.name, event.type, seconds))
        logging.log('Elementos na Fila 1: {}'.format(len(self.queue1)))
        logging.log('Elementos na Fila 2: {}'.format(len(self.queue1)))
        element_in_service = self.element_in_service.name if self.state == SchedulerState.BUSY else 'Nenhum'
        logging.log('Elemento no serviço: {}'.format(element_in_service))

    def schedule_end_queue(self, previous_event, seconds, event_type):
        event = Event(name=previous_event.name, event_type=event_type)
        self.element_in_service = event
        self.schedule_event(event, seconds + self.service_time)

    def schedule_start_queue1(self, previous_event, seconds):
        event = Event(type=EventType.START_QUEUE1)
        self.schedule_event(event, seconds + self.uniform1.sample())

    def schedule_start_queue2(self, seconds):
        event = Event(type=EventType.START_QUEUE2)
        self.schedule_event(event, seconds + self.uniform2.sample())

    def check_state(self, seconds):
        if not len(timeline):
            self.state = SchedulerState.FINISHED
            return
            
        time_next_event, next_event = min(self.timeline)
        if time_next_event !== seconds:
            return
        
        self.log_event(next_event, seconds)
        if next_event.type == EventType.START_QUEUE1:
            if self.state == SchedulerState.FREE:
                self.schedule_end_queue(next_event, seconds, EventType.END_QUEUE1)
            elif self.state == SchedulerState.BUSY:
                self.queue1.append(next_event)
        elif next_event.type == EventType.START_QUEUE2:
            if self.state == SchedulerState.FREE:
                self.schedule_end_queue(next_event, seconds, EventType.END_QUEUE2)
            elif self.state == SchedulerState.BUSY:
                self.queue2.append(next_event)
        elif next_event.type == EventType.END_QUEUE:
            self.queue1.pop(0)
            if len(self.)
            if self.state == SchedulerState.FREE:



            heappop(self.timeline)
            
        
        def schedule_end_of_service(self):
            if not len(self.queue1) and not len(self.queue2):
                self.state = SchedulerState.FREE
            elif len(self.queue1):

        


class Requisition:

    def __init__(self,)



def execute_event(*params):
    return


def time_of_event(*params):
    return
