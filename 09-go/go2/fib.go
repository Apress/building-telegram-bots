package main

import (
	"fmt"
	"os"
	"strconv"
)

// Fibonacci computes fibonacci by recursion
func Fibonacci(n int) int {
	if n <= 1 {
		return n
	}
	return Fibonacci(n-1) + Fibonacci(n-2)
}

func main() {
	// fmt.Println(os.Args[0])
	i, _ := strconv.Atoi(os.Args[1])
	fmt.Println(Fibonacci(i))
}
