(ns immutant.init
  (:require [tic-tac-doom-bot.app :as app]
            [immutant.messaging :as messaging]
            [immutant.web :as web]))

(web/start app/app)
