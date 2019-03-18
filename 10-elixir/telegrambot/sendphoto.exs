token = Application.get_env(:telegrambot, :token)
chat_id = 121843071
photo = "cat.jpg"
Telegram.Api.request(token, "sendPhoto", chat_id: chat_id, photo: {:file, photo})