#include "opencv2/highgui.hpp"
using namespace cv;

int main(int argc, char* argv[])
{
    auto bw = imread(argv[1],0);
    imwrite("saved.jpg", bw);
    return 0;
}
