import std.typecons;
import std.file;
import std.stdio;
import telega.botapi;
import std.datetime.systime : SysTime, Clock;


int main()
{
    auto botToken = readText("secret.key");
    listenUpdates(botToken);
    return 0;
}

void handleUpdate(BotApi api, Update update) {
           if (!update.message.isNull) {
try {
                     writefln("Text from %s: %s", update.message.chat.id, update.message.text);
                    // api.sendMessage(update.message.chat.id, update.message.text);
                    // api.sendPhoto(update.message.chat.id, "https://pbs.twimg.com/tweet_video_thumb/Dm_YlIgXgAAbxZu.jpg");
                    // api.sendDocument(update.message.chat.id, "https://ibiblio.org/ebooks/Poe/Black_Cat.pdf");
                    // api.sendLocation(update.message.chat.id, 45.8992, 6.1294);
                    // SysTime currentTime = Clock.currTime();
                    // api.setChatTitle(update.message.chat.id, currentTime.toISOExtString());
                    // api.setChatTitle(update.message.chat.id, "hello2");
                    writefln("Updated");

                        } catch (Exception e) {
        writefln(e.toString());

        // throw e;
    }
                }
                api.updateProcessed(update);
}

void listenUpdates(string token)
{
    auto api = new BotApi(token);
    while(true) {
        writeln("Waiting for updates...");
        auto updates = api.getUpdates();
        writefln("Got %d updates", updates.length);
        
        foreach (update; updates) {
            handleUpdate(api, update);
        }
    }
}
