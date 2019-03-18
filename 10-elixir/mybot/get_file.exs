{:ok, res} = Telegram.Api.request(token, "getChat", chat_id: 121843071)

fileId = res["photo"]["big_file_id"]

{:ok, res2} = Telegram.Api.request(token, "getFile", file_id: "#{fileId}")

System.cmd("curl", ["-O", "https://api.telegram.org/file/bot#{token}/#{res2["file_path"]}"])
