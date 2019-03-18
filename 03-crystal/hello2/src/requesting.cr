require "http/client"
require "json"

def search_github(q : String)
  url = "https://api.github.com/search/repositories?q=#{q}"
  HTTP::Client.get(url) do |response|
    res = response.body_io.gets.to_s
    json = JSON.parse(res)
    json["items"][0]["url"]
  end
end

puts search_github("dog")