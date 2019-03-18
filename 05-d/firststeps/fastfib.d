auto fib(ulong n) {
    import std.bigint : BigInt;
    import std.meta : AliasSeq;
    import std.typecons : tuple;
    BigInt a = 0;
    BigInt b = 1;
    auto bit = 63;
    while (bit > 0) {
        AliasSeq!(a, b) = tuple(
            a * (2*b - a),
            a*a + b*b);

        if (n & (BigInt(1) << bit)) {
            AliasSeq!(a, b) = tuple(b, a+b);
        }
        --bit;
    }
    return a;
}


void main()
{
    import std.stdio : writeln;
    import std.datetime : MonoTime;

    auto t0 = MonoTime.currTime;
    // writeln(fib(10_000_000));
    writeln(fib(1_000_000));
    writeln(MonoTime.currTime - t0);
}