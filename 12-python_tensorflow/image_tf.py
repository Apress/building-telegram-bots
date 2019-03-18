# pip install tensorflow

import os
import telepot
from telepot.loop import MessageLoop
import tempfile
import classify_image


def handle(msg):
    if msg["photo"]:
        chat_id = msg['chat']['id']
        f = tempfile.NamedTemporaryFile(delete=True).name+".png"
        photo = msg['photo'][-1]["file_id"]
        bot.download_file(photo, f)
        prediction = classify_image.run_inference_on_image(f)
        bot.sendMessage(chat_id, "I think this image is a %s" % prediction)
    else:
        print("no photo")


classify_image.maybe_download_and_extract()
bot = telepot.Bot(os.environ["BOT_TOKEN"])
MessageLoop(bot, handle).run_forever()