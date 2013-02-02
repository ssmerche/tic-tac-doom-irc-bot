# tic-tac-doom irc bot

## Prerequisites

You will need [Leiningen][1] 2.0.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Development

Add this to the :user map in $HOME/.lein/profiles.clj:

        :plugins [[environ/environ.lein "0.3.0"]]
        :hooks [environ.leiningen.hooks]
        :env {:bot-room "#some-test-room" :bot-name "test-bot"}

To start a jetty server (default port is 8081):

    lein run # add a port number if you want a different port
    
To start an immutant server (add ```-Djboss.socket.binding.port-offset=1``` 
if you already have a immutant or torquebox app running):
	
	lein immutant deploy && lein immutant run 

## License

Copyright © 2012 FIXME
