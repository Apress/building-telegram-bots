require "telegram_bot"

class CommandOneBot < TelegramBot::Bot
  include TelegramBot::CmdHandler

  def initialize
    super("MyBot", File.read("secret.key"))

    cmd "hello" do |msg|
        send_message msg.chat.id, "world"
    end

    cmd "animals" do |msg, params|
        send_photo msg.chat.id, File.new("#{params[0]}.jpg")
    end
  end

end

my_bot = CommandOneBot.new
my_bot.polling