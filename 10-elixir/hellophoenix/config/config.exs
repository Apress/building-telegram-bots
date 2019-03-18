# This file is responsible for configuring your application
# and its dependencies with the aid of the Mix.Config module.
#
# This configuration file is loaded before any dependency and
# is restricted to this project.
use Mix.Config

# General application configuration
config :hellophoenix,
  ecto_repos: [Hellophoenix.Repo]

# Configures the endpoint
config :hellophoenix, Hellophoenix.Endpoint,
  url: [host: "localhost"],
  secret_key_base: "VrcGupu0ihj142WG1QdD7dp67/r45glD7Rv8tBBe/uB3CTVa6YCoS+sz2PQphWuQ",
  render_errors: [view: Hellophoenix.ErrorView, accepts: ~w(html json)],
  pubsub: [name: Hellophoenix.PubSub,
           adapter: Phoenix.PubSub.PG2]

# Configures Elixir's Logger
config :logger, :console,
  format: "$time $metadata[$level] $message\n",
  metadata: [:request_id]

# Import environment specific config. This must remain at the bottom
# of this file so it overrides the configuration defined above.
import_config "#{Mix.env}.exs"
