defmodule TelegrambotTest do
  use ExUnit.Case
  doctest Telegrambot

  test "greets the world" do
    assert Telegrambot.hello() == :world
  end
end
