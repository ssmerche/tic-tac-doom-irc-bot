# tic-tac-doom irc bot

FIXME

## Prerequisites

You will need [Leiningen][1] 2.0.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Development

Add this to the :user map in $HOME/.lein/profiles.clj:
```
        :plugins [[environ/environ.lein "0.3.0"]]
        :hooks [environ.leiningen.hooks]
        :env {:bot-room "#some-test-room" :bot-name "test-bot"}
```

To start a web server for the application, run:

    lein run -m tic-tac-doom-bot.app $PORT

## License

Copyright © 2012 FIXME
