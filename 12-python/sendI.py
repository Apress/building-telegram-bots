import telepot
import os
import sys

TOKEN = os.environ['BOT_TOKEN']
photo = open((sys.argv[1]), 'rb')
chat_id = sys.argv[2]
bot = telepot.Bot(TOKEN)
bot.sendPhoto(chat_id, photo)
# empty