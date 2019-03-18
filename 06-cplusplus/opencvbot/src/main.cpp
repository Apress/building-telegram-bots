#include <csignal>
#include <cstdio>
#include <cstdlib>
#include <exception>

#include <tgbot/tgbot.h>

#include <string>
#include "opencv2/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"

using namespace std;
using namespace TgBot;
using namespace cv;

const string telegram_url = "https://api.telegram.org/file/bot";
const string tmp_file = "download.jpg";
const string saved_file = "saved.jpg";

string applyOpenCV(string token, string path) {
    string command = string("curl --silent ");
    command.append(telegram_url).append(token).append("/").append(path).append(" -o ").append(tmp_file);
    const char* cmd = command.c_str();
    system(cmd);
    Mat bw = imread(tmp_file,0);
    imwrite(saved_file, bw);
    return saved_file;
}

int main() {
    string token(getenv("TOKEN"));
    printf("Token: %s\n", token.c_str());

    Bot bot(token);
    bot.getEvents().onAnyMessage([&bot, &token](Message::Ptr message) {
        if(message->photo.size() != 0) {
            PhotoSize::Ptr s = message->photo[2];
            if(s!=NULL) {
             string fileId = message->photo[2]->fileId;
             File::Ptr file = bot.getApi().getFile(fileId);
             string filepath = applyOpenCV(token.c_str(), file->filePath.c_str());
             bot.getApi().sendPhoto(message->chat->id, InputFile::fromFile(filepath, "image/jpeg"));
            }
        }
    });

    signal(SIGINT, [](int s) {
        printf("SIGINT got\n");
        exit(0);
    });

    try {
        printf("Bot username: %s\n", bot.getApi().getMe()->username.c_str());
        bot.getApi().deleteWebhook();

        TgLongPoll longPoll(bot);
        while (true) {
            printf("Long poll started\n");
            longPoll.start();
        }
    } catch (exception& e) {
        printf("error: %s\n", e.what());
    }
    
    return 0;
}
