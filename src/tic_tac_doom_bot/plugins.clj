(ns tic-tac-doom-bot.plugins
  (:require [qbits.ash :as ash] [tic-tac-doom-bot.db :as db])
  (:import [java.sql Date]))

; rename user key to user since postgres won't let me have a column called user
(defn tweak-event [event]
  (-> event (assoc :username (event :user)) (dissoc :user)
    (update-in [:timestamp] #(Date. %))))

; event has keys user, channel, content, timestamp
(defn record-messages [bot]
  (ash/listen bot :on-message 
              (fn [event] (db/save-message (tweak-event event)))))

