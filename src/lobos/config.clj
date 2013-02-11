(ns lobos.config
  (:use lobos.connectivity))

(def db {:classname "org.postgresql.Driver"
         :subprotocol "postgresql"
         :user (System/getenv "USER") 
         :password ""
         :subname "//localhost:5432/tic_tac_doom_irc"})

(open-global db)


