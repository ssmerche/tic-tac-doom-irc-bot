(ns tic-tac-doom-bot.plugins
  (:require [qbits.ash :as ash] [tic-tac-doom-bot.db :as db]))

(defn record-messages [bot]
  (ash/listen bot :on-message db/save-message))

