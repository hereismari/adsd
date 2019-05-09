from enum import Enum
from heapq import heappop, heappush
import logging
import time

from src.event import Event
from src.event import EventType

from src.rng import UniformDistributionMCGBucket as Uniform


class SchedulerState(Enum):
    FREE = 1
    BUSY = 2


class Scheduler:

    def __init__(self, simulation_speed: int = 1):
        self.simulation_speed = simulation_speed

        self.uniform_service_time = Uniform(lower_bound=2, upper_bound=6, seed=2e31+3)
        # self.uniform_service_time = UniformDistributionMCG(
        #   lower_bound=2, upper_bound=6, seed=2, multi_factor=1, add_factor=3,)

        self.state = SchedulerState.FREE
        self.element_in_service = None
        self.timeline = []

        # Type 1
        self.queue1 = []
        self.uniform1 = Uniform(lower_bound=1, upper_bound=12, seed=2e31)
        # self.uniform1 = UniformDistributionMCG(lower_bound=1, upper_bound=12,
        #                                       seed=11, multi_factor=7, add_factor=5)

        # Type 2
        self.queue2 = []
        self.uniform2 = Uniform(lower_bound=1, upper_bound=4, seed=2e30)
        # self.uniform2 = UniformDistributionMCG(lower_bound=1, upper_bound=4,
        #                                        seed=3, multi_factor=1, add_factor=3)

        # Schedule one event of each type
        self.schedule_start_queue1(0)
        self.schedule_start_queue2(0)

    def run(self, run_time: int = 300):
        seconds = 0
        while seconds < run_time:
            time.sleep(self.simulation_speed)
            seconds += 1
            self.check_state(seconds)

    def log_event(self, event, seconds):
        logging.info('Nome do elemento: {}, Tipo de evento: {}, Momento do evento: {}'.format(
            event.name, event.type, seconds))
        logging.info('Elementos na Fila 1: {}'.format(len(self.queue1)))
        logging.info('Elementos na Fila 2: {}'.format(len(self.queue2)))
        
        if self.state == SchedulerState.BUSY:
            element_in_service = self.element_in_service.name
        else:
            element_in_service = 'Nenhum'
        logging.info('Elemento em serviço: {}'.format(element_in_service))

    def schedule_event(self, event, event_time):
        heappush(self.timeline, (event_time, event))        

    def schedule_end_of_service(self, previous_event, seconds):
        event = Event(name=previous_event.name, event_type=EventType.FIM_DE_SERVICO)
        self.element_in_service = event
        self.schedule_event(event, seconds + self.uniform_service_time.sample())

    def schedule_start_queue1(self, seconds):
        event = Event(event_type=EventType.CHEGADA_1)
        self.schedule_event(event, seconds + self.uniform1.sample())

    def schedule_start_queue2(self, seconds):
        event = Event(event_type=EventType.CHEGADA_2)
        self.schedule_event(event, seconds + self.uniform2.sample())

    def _get_next_event(self, seconds):
        time_next_event, next_event = min(self.timeline)
        if time_next_event != seconds:
            return None
        heappop(self.timeline)
        self.log_event(next_event, seconds)
        return next_event

    def _change_state_to_busy(self, next_event, seconds):
        self.state = SchedulerState.BUSY
        self.schedule_end_of_service(next_event, seconds)

    def _check_queues(self, seconds):
        if not self.queue1 and not self.queue2:
            self.state = SchedulerState.FREE
        elif self.queue1:
            queue_event = self.queue1.pop(0)
            self.schedule_end_of_service(queue_event, seconds)
        else:
            queue_event = self.queue2.pop(0)
            self.schedule_end_of_service(queue_event, seconds)

    def check_state(self, seconds):
        next_event = self._get_next_event(seconds)
        if not next_event:
            return

        if next_event.type == EventType.CHEGADA_1:
            if self.state == SchedulerState.FREE:
                self._change_state_to_busy(next_event, seconds)
            elif self.state == SchedulerState.BUSY:
                self.queue1.append(next_event)
            self.schedule_start_queue1(seconds)
        elif next_event.type == EventType.CHEGADA_2:
            if self.state == SchedulerState.FREE:
                self._change_state_to_busy(next_event, seconds)
            elif self.state == SchedulerState.BUSY:
                self.queue2.append(next_event)
            self.schedule_start_queue2(seconds)
        elif next_event.type == EventType.FIM_DE_SERVICO:
            self._check_queues(seconds)
        
        self.check_state(seconds)