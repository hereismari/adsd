import math

def interval(std, n=3, t=4.3):
     sm = math.sqrt((std ** 2) / n)
     return round(t * sm, 4)


# stds = [0.1033, 0.0894, 0.0463,0.1191,0.0968,0.1861,0.1114,0.0739,0.0349,0.0576,0.1231]
stds = [0.0014,
        0.0106,
        0.0341,
        0.0062,
        0.0053,
        0.0048,
        0.0071,
        0.0026,
        0.0204,
        0.002,
        0.0007]


for std in stds:
    print(std, interval(std))
