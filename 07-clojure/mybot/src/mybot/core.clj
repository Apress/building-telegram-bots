(ns mybot.core
  (:require [clojure.core.async :refer [<!!]]
            [clojure.string :as str]
            [environ.core :refer [env]]
            [morse.handlers :as h]
            [morse.polling :as p]
            [morse.api :as t])
  (:gen-class))

; TODO: fill correct token
(def token (env :telegram-token))
; (def token "585672177:AAHswpmdA2zP52ZWoJMdteGa0xQ8KeynWvE")

(h/defhandler handler

  (h/command-fn "start"
    (fn [{{id :id :as chat} :chat}]
      (println "Bot joined new chat: " chat)
      (t/send-text token id "Welcome to mybot start method!")))

  (h/command-fn "help"
    (fn [{{id :id :as chat} :chat}]
      (println "Help was requested in " chat)
      (t/send-text token id "Help is on the way")))

  (h/message-fn
    (fn [{{id :id} :chat :as message}]
      ; (println "Intercepted message: " message)
      (clojure.pprint/pprint message (clojure.java.io/writer "log.txt" :append true))
      ; (spit "log.txt" (str "Intercepted message: " message) :append true )
      (t/send-text token id "I don't do a whole lot maybe!...."))))


(defn -main
  [& args]
  (when (str/blank? token)
    (println "Please provde token in TELEGRAM_TOKEN environment variable!")
    (System/exit 1))

  (println "Starting the mybot")
  (<!! (p/start token handler)))

(-main)