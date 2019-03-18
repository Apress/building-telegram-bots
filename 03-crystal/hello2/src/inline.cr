require "telegram_bot"

class InlineBot < TelegramBot::Bot

  def initialize
    super("MyBot", File.read("secret.key"))
  end

  def handle(inline_query : TelegramBot::InlineQuery)
    puts inline_query.query
    results = Array(TelegramBot::InlineQueryResult).new

    content = TelegramBot::InputTextMessageContent.new "Article details"
    results << TelegramBot::InlineQueryResultArticle.new("article/1", "My first article", content)

    answer_inline_query(inline_query.id, results)
  end
end

InlineBot.new.polling