(ns lobos.migrations
  (:refer-clojure :exclude [alter drop bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema config)))

(defmigration add-messages
  (up [] (create (table :messages
                        (varchar :username 50)
                        (varchar :channel 50)
                        (text :content)
                        (timestamp :timestamp))))
                        
  (down [] (drop (table :messages))))

(defn -main [& args]
  (case (first args)
    "down" (do (println "down:") (rollback))
    (do (println "up:") (migrate))))
