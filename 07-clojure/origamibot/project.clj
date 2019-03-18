(defproject origamibot "0.1.0-SNAPSHOT"
  :injections [
   (clojure.lang.RT/loadLibrary org.opencv.core.Core/NATIVE_LIBRARY_NAME)
  ]
  :repositories [["vendredi" "https://repository.hellonico.info/repository/hellonico/"]]
  :main origamibot.core
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-environ "1.1.0"]]
  :dependencies [ 
  [environ "1.1.0"]
  [cheshire "5.6.1"]
  [origami "0.1.11"]
  [hellonico/morse "0.2.4"]
  [org.clojure/clojure "1.8.0"]])

