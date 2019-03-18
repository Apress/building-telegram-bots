# pip install opencv-python
# pip install face_recognition

import os
import telepot
from telepot.loop import MessageLoop
import pprint
import cv2 as cv
import tempfile

classifier = cv.CascadeClassifier(cv.data.haarcascades + "haarcascade_frontalface_default.xml")


def detect(image): 
    gray = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    faces = classifier.detectMultiScale(gray, 1.3, 5)
    count = 0
    for (x, y, w, h) in faces:
        count = count+1
        cv.rectangle(image, (x, y), (x+w, y+h), (0, 255, 0), 5)
    return image, count
    

def handle(msg):
    # pprint.pprint(msg)
    if msg["photo"]:
        chat_id = msg['chat']['id']
        f = tempfile.NamedTemporaryFile(delete=True).name+".png"
        photo = msg['photo'][-1]["file_id"]
        path = bot.getFile(photo)["file_path"]
        # bot.sendMessage(chat_id, "Retrieving %s" % path)
        bot.download_file(photo, f)
        p = cv.imread(f)
        hsv, l = detect(p)
        cv.imwrite(f, hsv)
        bot.sendMessage(chat_id, "found %i faces" % l)
        bot.sendPhoto(chat_id, open(f, 'rb'))
        # print("photo sent")
    else:
        print("no photo")


bot = telepot.Bot(os.environ["BOT_TOKEN"])
MessageLoop(bot, handle).run_forever()