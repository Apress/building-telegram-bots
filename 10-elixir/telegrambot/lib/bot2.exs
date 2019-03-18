defmodule Fib do 
    def fib(0) do 0 end
    def fib(1) do 1 end
    def fib(n) do fib(n-1) + fib(n-2) end
  end
  
defmodule Bot2 do

  use Telegram.Bot,
    token: Application.get_env(:telegrambot, :token),
    username: "chapter01bot",
    purge: true

    command "fib", args do
        {intVal, ""} = Integer.parse(Enum.at(args,0))
         request("sendMessage", chat_id: update["chat"]["id"],
         text: "Fib[#{intVal}] = #{Fib.fib(intVal)}")
    end

    any do
       IO.puts "not found"
    end
end

{:ok, _} = Bot2.start_link()
Process.sleep(:infinity)
