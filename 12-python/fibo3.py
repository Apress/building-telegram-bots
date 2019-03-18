x = [1, 1]

for i in range(2, 40):
    x.append(x[-1] + x[-2])

print(', '.join(str(y) for y in x))
