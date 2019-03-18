(defproject mytelegrambot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [environ             "1.1.0"]
                 [morse               "0.2.4"]]

  :plugins [[lein-environ "1.1.0"]]

  :main ^:skip-aot mytelegrambot.core
  :target-path "target/%s"

  :env
  {:telegram-token "585672177:AAHswpmdA2zP52ZWoJMdteGa0xQ8KeynWvE"}

  ; :profiles {:uberjar {:aot :all}}
             )
