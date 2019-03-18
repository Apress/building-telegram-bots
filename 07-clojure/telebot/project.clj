(defproject telebot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :main telebot.core
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ; :env
		; 	{:telegram-token "585672177:AAHswpmdA2zP52ZWoJMdteGa0xQ8KeynWvE"}
  :plugins [[lein-environ "1.1.0"]]
  :dependencies [ 
  [environ "1.1.0"]
  [cheshire "5.6.1"]
  [morse "0.2.4"]
  [org.clojure/clojure "1.8.0"]])
