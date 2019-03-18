require "./HELLO/*"
require "telegram_bot"

class MyBot < TelegramBot::Bot
  include TelegramBot::CmdHandler

  def initialize
    super("MyBot", File.read("secret.key"))

    cmd "hello" do |msg|
      reply msg, "world!"
    end

    # /add 5 7 => 12
    cmd "add" do |msg, params|
      reply msg, "#{params[0].to_i + params[1].to_i}"
    end
  end
end

my_bot = MyBot.new
my_bot.polling