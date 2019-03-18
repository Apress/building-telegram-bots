package main

import (
	"io/ioutil"
	"log"
	"time"

	telegram "gopkg.in/tucnak/telebot.v2"
)

func main() {
	token, _ := ioutil.ReadFile("token")

	bot, err := telegram.NewBot(telegram.Settings{
		Token:  string(token),
		Poller: &telegram.LongPoller{Timeout: 10 * time.Second},
	})

	if err != nil {
		log.Fatal(err)
		return
	}

	log.Println("Starting GO bot")
	bot.Handle("/hello", func(m *telegram.Message) {
		bot.Send(m.Sender, "hello go")
	})

	bot.Start()
}
