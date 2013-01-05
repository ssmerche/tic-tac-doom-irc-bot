(ns tic-tac-doom-bot.templates
  (:require [clojure.string :as string])
  (:use hiccup.core [clj-time.core :only (time-zone-for-offset)]
        clj-time.coerce clj-time.format))

(def timezone (time-zone-for-offset -8))

(def timestamp-formatter (with-zone (formatter "MM-dd-yyyy hh:mm:ss") timezone))

(defn format-timestamp [timestamp]
  (let [date (from-date timestamp)]
    (str (unparse timestamp-formatter date) " PST")))

(defn message [msg]
  (html [:p 
         [:span.timestamp (str "[" (format-timestamp (msg :timestamp))) "] "]
         [:span.username (str (msg :username) ":") " "]
         [:span.content (msg :content)]]))

(defn messages [msgs]
  (string/join "" (map message msgs)))
