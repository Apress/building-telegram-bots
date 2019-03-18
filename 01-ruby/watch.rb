require 'telegram_bot'
require 'filewatcher'

def send_message(bot, channel, msg) 
    message = TelegramBot::OutMessage.new
    message.chat = channel
    message.text = msg
    message.send_with(bot)
end

token = ENV['BOT_TOKEN']
channel_id = ENV['CHANNEL_ID'] 
bot = TelegramBot.new(token: token)
channel = TelegramBot::Channel.new(id: channel_id)

Filewatcher.new(["."]).watch() do |filename, event|
  send_message(bot,channel, "#{filename}")
end