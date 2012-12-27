(ns tic-tac-doom-bot.bot
  (:require [qbits.ash :as ash])
  (:use tic-tac-doom-bot.plugins))

(def room (or (System/getenv "BOT_ROOM") "#tic-tac-doom"))

(def bot-name (or (System/getenv "BOT_NAME") "tic-tac-doom-bot"))

(def irc-info [:nick bot-name :name bot-name :port 6667
               :channels [room] :host "irc.freenode.net"])
(defn greet-room [bot msg] (ash/send-message bot room msg))

(defn start-bot [& args]
  (let [bot (-> (apply ash/make-bot irc-info) record-messages)] 
    (greet-room bot "Hello from the tic-tac-doom bot")))
