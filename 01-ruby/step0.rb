# make telegram_bot available to this program
require 'telegram_bot'
require 'yaml'

# pick up the TOKEN we receive from the bot father
bot = TelegramBot.new(token: ENV['BOT_TOKEN'])

# start listening to messages
bot.get_updates() do |message|
    # puts message.to_s
    puts message.to_yaml
end