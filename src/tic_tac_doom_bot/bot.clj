(ns tic-tac-doom-bot.bot
  (:require [qbits.ash :as ash])
  (:use tic-tac-doom-bot.plugins environ.core))

(def bot-name (env :bot-name))

(def room (env :bot-room))

(def irc-info [:nick bot-name :name bot-name :port 6667
               :channels [room] :host "irc.freenode.net"])
(defn greet-room [bot msg] (ash/send-message bot room msg))

(defn start-bot [& args]
  (let [bot (-> (apply ash/make-bot irc-info) record-messages)] 
    (greet-room bot "Hello from the tic-tac-doom bot")))
