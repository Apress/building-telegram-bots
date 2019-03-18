def fib():
    a, b = 0, 1
    while True:            # First iteration:
        a, b = b, a + b    # a will now be 1, and b will also be 1, (0 + 1)
        yield a            # yield 0 to start with and then

for index, fibonacci_number in zip(range(20), fib()):
    print('{i:3}: {f:3}'.format(i=index, f=fibonacci_number))


def constant():
    a = 1
    while True:
        yield a

def myrange(n):
    a = 0
    while a < n:
        a=a+1
        yield a
# for i in myrange(10):
#     print(i)
