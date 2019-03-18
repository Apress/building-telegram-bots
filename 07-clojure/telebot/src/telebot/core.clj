(ns telebot.core
	(:require 
		[morse.api :as api]
	    [clojure.core.async :refer [<!!]]
		[morse.polling :as p]
	    [morse.handlers :refer :all]
	    [environ.core :refer [env]]
	    [cheshire.core :refer :all]
	    [clojure.string :as str])
	(:gen-class))
	
(def openweather-api-token "9220dd1ac43dcaf7091d8c07a8a46efb")
(def token (env :telegram-token))


(def writer (clojure.java.io/writer "message.log" :append true))

(defn weather[city] 
	(let [request
	(str 
		"http://api.openweathermap.org/data/2.5/weather?q=" 
		city 
		"&units=metric&APPID=" 
		openweather-api-token )]
	(:main 
		(parse-string (slurp request) 
		   (fn [k] (keyword k))))))

(defn get-text [message]	
	(str/join " " (rest (str/split message #" "))))

(defhandler handler
 (message-fn
    (fn [{{id :id} :chat :as message}]
   	  (let [place (:text message)]
      (try 
       (api/send-text token id 
	    {:parse_mode "Markdown"} 
	    (str "*" place "*" "\n" (weather place)))
     		(catch Exception e))))))

(defn -main
  [& args]
  (when (str/blank? token)
    (println "Please provde token in TELEGRAM_TOKEN environment variable!")
    (System/exit 1))

  (println "Starting the mytelegrambot")
  (<!! (p/start token handler)))

(-main)