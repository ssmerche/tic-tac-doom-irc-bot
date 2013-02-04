(ns immutant.init
  (:require [tic-tac-doom-bot.app :as app]
            [tic-tac-doom-bot.bot :as bot]
            [immutant.daemons :as daemon]
            [immutant.web :as web]))

(daemon/daemonize "tic-tac-doom-bot" bot/start-bot bot/stop-bot :singleton false)
(web/start app/app)
