import re

var matches: array[2, string]
if match("abcdefg", re"c(d)ef(g)", matches, 2):
  for s in matches:
    echo s       # => d g

for word in split("00232this02939is39an22example111", re"\d+"):
    writeLine(stdout, word)