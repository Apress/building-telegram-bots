(ns origamibot.core
	(:require 
	  [opencv3.core :as origami]
		[morse.api :as api]
	    [clojure.core.async :refer [<!!]]
		[morse.polling :as p]
	    [morse.handlers :refer :all]
	    [environ.core :refer [env]]
	    [cheshire.core :refer :all]
	    [clojure.string :as str])
	(:gen-class))
	
(def token (env :telegram-token))

(defn apply-cv [filename]
  			 (->  filename
           (origami/imread)
           (origami/cvt-color! origami/COLOR_RGB2GRAY)
           (origami/canny! 300.0 100.0 3 true)
           (origami/bitwise-not!)
           (origami/imwrite filename)))

(defhandler handler
 (message-fn
    (fn [{{id :id} :chat :as message}]
	     (let [fid (-> message :photo last :file_id) filename (str fid ".png")]
  		 (api/download-file token fid)
			 (apply-cv filename)
  		 (api/send-photo token id (clojure.java.io/as-file filename))))))

(defn -main
  [& args]
  (when (str/blank? token)
    (println "Please provde token in TELEGRAM_TOKEN environment variable!")
    (System/exit 1))

  (println "Starting the origamibot")
  (<!! (p/start token handler)))

(-main)