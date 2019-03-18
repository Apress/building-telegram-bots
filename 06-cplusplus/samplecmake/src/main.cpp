#include <string>
using namespace std;

int main(int argc, char* argv[])
{
    auto url = argv[1];
    printf("Downloading: %s\n", url);
    string command = string("curl --silent -O ");
    const char* cmd = command.append(url).c_str();
    system(cmd);
    return 0;
}
