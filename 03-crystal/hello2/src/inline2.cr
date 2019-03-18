require "telegram_bot"
require "http/client"
require "json"

class InlineBot < TelegramBot::Bot

  def initialize
    super("MyBot", File.read("secret.key"))
  end


    def search(q : String)
    url = "https://api.github.com/search/repositories?q=#{q}"
    HTTP::Client.get(url) do |response|
        res = response.body_io.gets.to_s
        json = JSON.parse(res)
        # puts json["items"].take(2)
        json["items"]
    end
    end

  def new_content(json : JSON::Any)
    url = json["url"]
    id = json["id"]
    content = TelegramBot::InputTextMessageContent.new("#{url}")
    TelegramBot::InlineQueryResultArticle.new("#{id}", "#{url}", content)
  end

  def handle(inline_query : TelegramBot::InlineQuery)
    query = inline_query.query
    res = search(query)

    results = Array(TelegramBot::InlineQueryResult).new
    results << new_content(res[0])
    results << new_content(res[1])
    results << new_content(res[2])

    answer_inline_query(inline_query.id, results)
  end
end

InlineBot.new.polling