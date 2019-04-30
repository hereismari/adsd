import rng


# begins with nobody being served
current_customer = 0

ENROLL_CUSTOMER_1 = "CUSTOMER OF CLASS 1"
ENROLL_CUSTOMER_2 = "CUSTOMER OF CLASS 2"

is_free = True

queue1 = []
queue2 = []
timeline = {}


def schedule_event(event_time, event):
    if timeline == {}:
        timeline[event_time] = event
        return

    if timeline[event_time] != []:
        timeline[event_time].append(event)
    else:
        timeline[event_time] = event


def execute_event(*params):
    return


def time_of_event(*params):
    return
