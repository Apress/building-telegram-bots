#!/usr/bin/env ruby
require 'telegram_bot'

token = ENV['BOT_TOKEN']
channel_id = ENV['CHANNEL_ID'] 

def send_message(bot, channel, msg) 
    message = TelegramBot::OutMessage.new
    message.chat = channel
    message.text = msg
    message.send_with(bot)
end

bot = TelegramBot.new(token: token)
channel = TelegramBot::Channel.new(id: channel_id)

send_message(bot,channel, ARGV[0])
# send_message(bot,channel, 'I think it is time')