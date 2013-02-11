(ns tic-tac-doom-bot.app
  (:use compojure.core)
  (:require [compojure.handler :as handler] 
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [tic-tac-doom-bot.db :as db] 
            [tic-tac-doom-bot.bot :as bot]
            [tic-tac-doom-bot.templates :as templates]))

(defroutes app-routes
  (GET "/" [] (templates/messages (db/get-messages)))
  (route/not-found "Not Found"))

(def app (handler/site app-routes))

(defn -main [& [port]]
  (bot/start-bot)
  (jetty/run-jetty 
    app {:port (if port (Integer/parseInt port) 8081)}))
