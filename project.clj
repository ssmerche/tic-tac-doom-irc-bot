(defproject tic-tac-doom-bot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.0-RC15"]
                 [ring/ring-jetty-adapter "1.1.6"]
                 [compojure "1.1.3"]
                 [lobos "1.0.0-beta1"]
                 [cc.qbits/ash "0.2.4"]
                 [korma "0.3.0-beta11"]
                 [hiccup "1.0.2"]
                 [environ "0.3.0"]
                 [clj-time "0.4.4"]
                 [postgresql/postgresql "9.1-901-1.jdbc4"]
                 [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]]
  :plugins [[lein-exec "0.2.1"]]
  :aliases {"migrate" ["run" "-m" "lobos.migrations"]}
  :min-lein-version "2.0.0"
  :profiles {:dev {:dependencies [[ring-mock "0.1.3"]]}}
  :main tic-tac-doom-bot.app
  :immutant {:context-path "/"})
