import argparse
import logging
logging.basicConfig(
    level=logging.INFO,
    format="[%(levelname)s] %(message)s",
    handlers=[
        logging.FileHandler("log.txt"),
        logging.StreamHandler()
    ])

from src.scheduler import Scheduler


parser = argparse.ArgumentParser(description='Miniteste de simulação.')
parser.add_argument('--simulation_speed', type=float, default=1,
                    help='Velocidade de 1 segundo. Exemplo: se 0.5, '
                         '1 segundo de simulação = 0.5 segundos reais.')
parser.add_argument('--simulation_time', type=int, default=30,
                    help='Tempo de simulação.')


def main(args):
    scheduler = Scheduler(simulation_speed=args.simulation_speed)
    scheduler.run(run_time=args.simulation_time)


if __name__ == '__main__':
    args = parser.parse_args()
    main(args)