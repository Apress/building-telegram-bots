require "telegram_bot"

class EchoBot < TelegramBot::Bot

  def initialize
    super("MyBot", File.read("secret.key"))
  end

  def handle(message : TelegramBot::Message)
    if text = message.text
      reply message, text
    end
  end
end

EchoBot.new.polling