defmodule MyBot.Mixfile do
  use Mix.Project

  def project do
    [
      app: :mybot,
      version: "0.1.0",
      deps: deps(),
      elixirc_paths: ["lib"]
    ]
  end

  defp deps do
    [
      {:telegram, git: "https://github.com/visciang/telegram.git", tag: "0.5.0"}
    ]
  end

end