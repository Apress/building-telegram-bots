defmodule Telegrambot do
  use Timex

  def hello do
    :world
  end
  
  def timexnow do
    IO.puts Timex.now
  end

end
