def fastFib(n, memo={0:1, 1:1}):
    '''efficiently memoized recursive function, returns a Fibonacci number'''
    print('>', n, memo)
    if not n in memo:
        memo[n] = fastFib(n-1, memo) + fastFib(n-2, memo)
    return memo[n] 

print(fastFib(5))
