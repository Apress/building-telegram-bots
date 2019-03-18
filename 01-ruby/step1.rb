require 'telegram_bot'
require 'yaml'

token = ENV['BOT_TOKEN']
bot = TelegramBot.new(token: token)

bot.get_updates(fail_silently: true) do |message|
    message.reply do |reply|
        reply.text = "Hello, #{message.from.first_name}!"
        reply.send_with(bot) 
    end
end