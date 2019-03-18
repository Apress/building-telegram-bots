import vibe.core.core;
import vibe.core.log;
import std.typecons;
import std.file;

string botToken = "";

int main(string[] args)
{
    if (args.length > 1 && args[1] != null) {
        logInfo("Setting token from first argument");
        botToken = args[1];
    }
    logInfo("Reading token...");
    botToken = readText("secret.key");

    runTask(&listenUpdates);

    return runApplication();
}

void listenUpdates()
{
    import telega.botapi;

    try {
        auto api = new BotApi(botToken);

        while(true) {
            logInfo("Waiting for updates...");
            auto updates = api.getUpdates();
            logInfo("Got %d updates", updates.length);

            foreach (update; updates) {
                if (!update.message.isNull) {
                    logInfo("Text from %s: %s", update.message.chat.id, update.message.text);
                    // api.sendMessage(update.message.chat.id, update.message.text);

                    // api.sendPhoto(update.message.chat.id, "https://pbs.twimg.com/tweet_video_thumb/Dm_YlIgXgAAbxZu.jpg");
                    api.sendDocument(update.message.chat.id, "https://ibiblio.org/ebooks/Poe/Black_Cat.pdf");
                    api.sendLocation(update.message.chat.id, 45.8992, 6.1294);

                     InlineQueryResult[] iqr = new InlineQueryResult[1];

            // iqr[0] = InlineQueryResultArticle();
            iqr[1] = InlineQueryResultPhoto();
            // iqr[2] = InlineQueryResultGif();
            // iqr[3] = InlineQueryResultMpeg4Gif();
            // iqr[4] = InlineQueryResultVideo();
            // iqr[5] = InlineQueryResultAudio();
            // iqr[6] = InlineQueryResultVoice();
            // iqr[7] = InlineQueryResultDocument();
            // iqr[8] = InlineQueryResultLocation();
            // iqr[9] = InlineQueryResultVenue();
            // iqr[10] = InlineQueryResultContact();
            // iqr[11] = InlineQueryResultGame();
            // iqr[12] = InlineQueryResultCachedPhoto();
            // iqr[13] = InlineQueryResultCachedGif();
            // iqr[14] = InlineQueryResultCachedMpeg4Gif();
            // iqr[15] = InlineQueryResultCachedSticker();
            // iqr[16] = InlineQueryResultCachedDocument();
            // iqr[17] = InlineQueryResultCachedVideo();
            // iqr[18] = InlineQueryResultCachedVoice();
            // iqr[19] = InlineQueryResultCachedAudio();

api.answerInlineQuery("answer-inline-query", iqr);
                }
                api.updateProcessed(update);

            }

            yield();
        }
    } catch (Exception e) {
        logError(e.toString());

        throw e;
    }
}
