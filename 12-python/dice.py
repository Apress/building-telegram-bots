import os
import random
import telepot
from telepot.loop import MessageLoop


def handle(msg):
    chat_id = msg['chat']['id']
    command = msg['text']
    if command == '/roll':
        bot.sendMessage(chat_id, random.randint(1, 6))


bot = telepot.Bot(os.environ["BOT_TOKEN"])
MessageLoop(bot, handle).run_forever()
