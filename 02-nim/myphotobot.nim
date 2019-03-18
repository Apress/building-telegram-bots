import telebot, asyncdispatch, logging, options

const API_KEY = slurp("secret.key")

proc updateHandler(bot: TeleBot, update: Update) {.async.} =
  var response = update.message.get
  if response.text.isSome:
    let
         animal = response.text.get
         file = "file://" & animal & ".jpg"
    var message = newPhoto(response.chat.id, file)
    message.caption = animal
    discard await bot.send(message)

let
  bot = newTeleBot(API_KEY)
bot.onUpdate(updateHandler)
bot.poll(300)