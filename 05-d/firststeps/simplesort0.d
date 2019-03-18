import std.stdio;
import std.algorithm;

void main()
{
    int[] arr1 = [4, 9, 7];
    writefln("%s\n", sort(arr1));
    writefln("%s\n", arr1.sort!("a > b"));
}