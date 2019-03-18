defmodule Bot1 do

  use Telegram.Bot,
    token: Application.get_env(:telegrambot, :token),
    username: "chapter01bot",
    purge: true

  message do
    IO.inspect(update)
    request(
      "sendMessage",
      chat_id: update["chat"]["id"],
      text: "Hey! You sent me a message: #{inspect(update)}"
    )
  end
end

{:ok, _} = Bot1.start_link()
Process.sleep(:infinity)
