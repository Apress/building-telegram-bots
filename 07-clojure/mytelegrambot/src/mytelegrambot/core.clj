(ns mytelegrambot.core
  (:require [clojure.core.async :refer [<!!]]
            [clojure.string :as str]
            [environ.core :refer [env]]
            [morse.handlers :as h]
            [morse.polling :as p]
            [morse.api :as t])
  (:gen-class))

; TODO: fill correct token
(def token (env :telegram-token))

(def writer (clojure.java.io/writer "message.log" :append true))
(def ew (clojure.java.io/writer "error.log" :append true))

(h/defhandler handler

  (h/command-fn "start"
    (fn [{{id :id :as chat} :chat}]
      (clojure.pprint/pprint chat)
      (println "Bot joined new chat: " chat)
      (t/send-text token id "Welcome to mytelegrambot!")))

  (h/command-fn "help"
    (fn [{{id :id :as chat} :chat}]
      (println "Help was requested in " chat)
      (t/send-text token id "Help is on the way")))

  (h/inline-fn 
  (fn [inline] 
    (clojure.pprint/pprint inline writer)
    (try
    (t/answer-inline 
     token 
     (:id inline)
    [
    {:type "gif"
      :id "gif1"
      :thumb_url "https://media.glamour.com/photos/57ee812a97f0ecec29adfd06/master/w_644,c_limit/lemonslede.gif"
      :gif_url "https://media.glamour.com/photos/57ee812a97f0ecec29adfd06/master/w_644,c_limit/lemonslede.gif"}])
      (catch Exception e (clojure.pprint/pprint e ew)))
    inline
    ))

  (h/message-fn
    (fn [{{id :id} :chat :as message}]
      (clojure.pprint/pprint message writer)    
      (t/send-text token id (str/reverse (:text message)))))    
  
      )

(defn -main
  [& args]
  (when (str/blank? token)
    (println "Please provde token in TELEGRAM_TOKEN environment variable!")
    (System/exit 1))

  (println "Starting the mytelegrambot")
  (<!! (p/start token handler)))

  ; (<!! (p/start token handler))
 (-main)