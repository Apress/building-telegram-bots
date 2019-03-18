import telebot, asyncdispatch, options

const API_KEY = slurp("secret.key")

proc updateHandler(b: Telebot, u: Update) {.async.} =
  var response = u.message.get
  if response.text.isSome:
    let
      text = response.text.get
    var message = newMessage(response.chat.id, text)
    message.replyToMessageId = response.messageId
    discard await b.send(message)

let bot = newTeleBot(API_KEY)
bot.onUpdate(updateHandler)
bot.poll(300)