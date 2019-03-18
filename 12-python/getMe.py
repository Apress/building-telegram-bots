import telepot
import os
import pprint

TOKEN = os.environ['BOT_TOKEN']
bot = telepot.Bot(TOKEN)
pprint.pprint(bot.getMe())
# empty line