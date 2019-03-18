import std.concurrency : spawn, thisTid;
import std.stdio : writeln;

void worker()
{
    static int threadState = 0;
    writeln("Thread ", thisTid,": My state = ", threadState++);
    if (threadState < 5) worker();
}

void main()
{
    for (int i = 0; i < 5; ++i) {
        spawn(&worker);
    }
}