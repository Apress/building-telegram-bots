import os
import telepot
from telepot.loop import MessageLoop
import pprint
import cv2 as cv
import tempfile


def handle(msg):
    if msg["photo"]:
        chat_id = msg['chat']['id']
        f = tempfile.NamedTemporaryFile(delete=True).name+".png"
        photo = msg['photo'][-1]["file_id"]
        path = bot.getFile(photo)["file_path"]
        bot.sendMessage(chat_id, "Retrieving %s" % path)
        bot.download_file(photo, f)
        p = cv.imread(f)
        hsv = cv.cvtColor(p, cv.COLOR_BGR2GRAY)
        cv.imwrite(f, hsv)
        bot.sendPhoto(chat_id, open(f, 'rb'))
        print("photo sent")
    else:
        print("no photo")


bot = telepot.Bot(os.environ["BOT_TOKEN"])
MessageLoop(bot, handle).run_forever()