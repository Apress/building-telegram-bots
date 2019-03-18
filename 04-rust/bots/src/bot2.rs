extern crate futures;
extern crate telegram_bot;
extern crate tokio_core;

use std::env;

use futures::{Future, Stream};
use tokio_core::reactor::{Core, Handle};
use telegram_bot::{Api, Message, SendMessage, ParseMode, UpdateKind};
use telegram_bot::*;

fn multiple_messages(api: Api, message: Message, handle: &Handle) {
    
    let simple = 
        api.send(message.text_reply("Simple message"));
    let markdown = 
        api.send(message.text_reply("- Markdown message \n- line 2\n- line 3").parse_mode(ParseMode::Markdown));
    let html = 
        api.send(message.text_reply("<b>Bold HTML message</b>").parse_mode(ParseMode::Html));
    let private = 
        api.send(message.from.text("Private text"));
    let private_html =
        api.send(message.from.text(format!("<b>Private text</b>")).parse_mode(ParseMode::Html));
    let preview = 
        api.send(message.text_reply("Message with preview https://telegram.org"));

    handle.spawn({
        let future = simple
            .and_then(|_| markdown)
            .and_then(|_| private)
            .and_then(|_| private_html)
            .and_then(|_| preview)
            .and_then(|_| html);

        future.map_err(|_| ()).map(|_| ())
    })
}

fn main() {
    let token = env::var("TELEGRAM_BOT_TOKEN").unwrap();

    let mut core = Core::new().unwrap();
    let handle = core.handle();

    let api = Api::configure(token).build(core.handle()).unwrap();

    let future = api.stream().for_each(|update| {
        if let UpdateKind::Message(message) = update.kind {
            multiple_messages(api.clone(), message, &handle)
        }
        Ok(())
    });

    core.run(future).unwrap();
}
