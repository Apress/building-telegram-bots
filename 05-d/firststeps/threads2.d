import std.concurrency : spawn, thisTid;
import std.stdio : writeln;

void worker(int startState)
{
    static int threadState = 0;
    threadState++;
    writeln("Thread ", thisTid,": My state = ", threadState++);
    if (threadState<5) worker(startState);
}

void main()
{
    for (int i = 0; i < 5; ++i) {
        spawn(&worker, i);
    }
}