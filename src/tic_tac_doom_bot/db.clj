(ns tic-tac-doom-bot.db
  (:use korma.db korma.core)
  (:require [clojure.string :as string])
  (:import [java.net URI] [java.sql Timestamp]))

(defn db-info-from-url [url]
  (if (empty? url)
    {:host "localhost" :port 5432 :user (System/getenv "USER") 
     :db "tic_tac_doom_irc" :password ""}
    (let [uri (URI. url)
          host (.getHost uri)
          port (.getPort uri)
          [user password] (string/split (.getUserInfo uri) #":")
          db (.substring (.getPath uri) 1)]
      {:host host :port port :user user :password password :db db})))

(defdb db (postgres (db-info-from-url 
                      (System/getenv "HEROKU_POSTGRESQL_IVORY_URL"))))

(defentity messages)

; event has keys user, channel, content, timestamp
; rename user key to user since postgres won't let me have a column called user
(defn tweak-event [event]
  (-> event (assoc :username (event :user)) (dissoc :user)
    (update-in [:timestamp] #(Timestamp. %))))

(defn save-message [event]
  (insert messages (values (tweak-event event))))

(defn get-messages [] (select messages))

