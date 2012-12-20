(ns compojure-test.handler
  (:use compojure.core korma.db korma.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [qbits.ash :as ash]
            [clojure.string :as string])
  (:import [java.sql Date] [java.net URI]))

(defn db-info-from-url [url]
  (if (empty? url)
    {:host "localhost" :port 5432 :user (System/getenv "USER") :db "tic_tac_doom_irc"}
    (let [uri (URI. url)
          host (.getHost uri)
          port (.getPort uri)
          [user password] ((string/split (.getUserInfo uri) #":"))
          db (.substring (.getPath uri) 1)]
      {:host host :port port :user user :password password :db db})))

(defdb db (postgres (db-info-from-url (System/getenv "HEROKU_POSTGRESQL_IVORY_URL"))))

(defentity messages)

(def room (or (System/getenv "BOT_ROOM") "#tic-tac-doom"))

(def bot-name (or (System/getenv "BOT_NAME") "tic-tac-doom-bot"))

(def irc-info [:nick bot-name :name bot-name :port 6667
               :channels [room] :host "irc.freenode.net"])

(defroutes app-routes
  (GET "/" [] (pr-str (select messages)))
  (route/not-found "Not Found"))

; rename user key to user since postgres won't let me have a column called user
(defn tweak-event [event]
  (-> event (assoc :username (event :user)) (dissoc :user)
    (update-in [:timestamp] #(Date. %))))

(defn record-event [event]
  (insert messages (values event)))

(def app
  (handler/site app-routes))

; event has keys user, channel, content, timestamp
(defn bot-handler [bot]
  (ash/listen bot :on-message 
              (fn [event] (record-event (tweak-event event)))))

(defn start-bot [& args]
  (let [bot (-> (apply ash/make-bot irc-info) bot-handler)]
    (ash/send-message bot room "Hello from the tic-tac-doom bot")))
