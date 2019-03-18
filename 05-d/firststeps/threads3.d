import std.concurrency : spawn, thisTid;
import std.stdio : writeln;
import core.atomic : atomicOp;

shared int flag = 0;

void worker()
{
    atomicOp!"+="(flag, 1);
    writeln("Thread ", thisTid,":global>",flag);
}

void main()
{
    for (int i = 0; i < 5; ++i) {
        spawn(&worker);
    }
}