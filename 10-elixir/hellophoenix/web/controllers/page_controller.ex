defmodule Hellophoenix.PageController do
  use Hellophoenix.Web, :controller

  def index(conn, _params) do
    render conn, "index.html"
  end
end
