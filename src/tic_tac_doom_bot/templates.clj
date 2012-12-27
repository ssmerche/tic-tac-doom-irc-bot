(ns tic-tac-doom-bot.templates
  (:require [clojure.string :as string])
  (:use hiccup.core))

(defn message [msg]
  (html [:p [:span.timestamp (str "[" (msg :timestamp)) "] "]
         [:span.username (str (msg :username) ":") " "]
         [:span.content (msg :content)]]))

(defn messages [msgs]
  (string/join "" (map message msgs)))
