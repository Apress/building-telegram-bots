echo "Hello World"

proc getAlphabet(): string =
    var accm = ""
    for letter in 'a'..'z':  
      accm.add(letter)
    return accm

echo getAlphabet()