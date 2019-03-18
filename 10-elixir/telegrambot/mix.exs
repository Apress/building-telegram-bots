defmodule Telegrambot.MixProject do
  use Mix.Project

  def project do
    [
      app: :telegrambot,
      version: "0.1.0",
      elixir: "~> 1.7",
      elixirc_paths: ["lib"],
      # start_permanent: Mix.env() == :prod,
      deps: deps()
    ]
  end

  # Run "mix help compile.app" to learn about applications.
  def application do
    [
      # extra_applications: [:logger, :timex]
      extra_applications: [:logger, :tesla]
    ]
  end

  # Run "mix help deps" to learn about dependencies.
  defp deps do
    [
      {:timex, "~> 3.0"},
      {:telegram, git: "https://github.com/visciang/telegram.git", tag: "0.5.0"}
    ]
  end
end
