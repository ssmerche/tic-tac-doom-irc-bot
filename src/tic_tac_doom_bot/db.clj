(ns tic-tac-doom-bot.db
  (:use korma.db korma.core)
  (:require [clojure.string :as string])
  (:import [java.net URI]))

(defn db-info-from-url [url]
  (if (empty? url)
    {:host "localhost" :port 5432 :user (System/getenv "USER") 
     :db "tic_tac_doom_irc"}
    (let [uri (URI. url)
          host (.getHost uri)
          port (.getPort uri)
          [user password] (string/split (.getUserInfo uri) #":")
          db (.substring (.getPath uri) 1)]
      {:host host :port port :user user :password password :db db})))

(defdb db (postgres (db-info-from-url 
                      (System/getenv "HEROKU_POSTGRESQL_IVORY_URL"))))

(defentity messages)

(defn save-message [event]
  (insert messages (values event)))

(defn get-messages [] (select messages))

