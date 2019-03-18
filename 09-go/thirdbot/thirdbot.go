package main

import (
	"io/ioutil"
	"os"
	"strconv"

	telegram "gopkg.in/tucnak/telebot.v2"
)

func main() {
	token, _ := ioutil.ReadFile("token")

	bot, _ := telegram.NewBot(telegram.Settings{Token: string(token)})
	idd, _ := strconv.Atoi(os.Args[1])
	p := &telegram.Photo{File: telegram.FromDisk(os.Args[2])}
	user := telegram.User{ID: idd}
	bot.SendAlbum(&user, telegram.Album{p})

}
