extern crate futures;
extern crate telegram_bot;
extern crate tokio_core;

use std::env;
use futures::Stream;
use tokio_core::reactor::Core;
use telegram_bot::*;

fn main() {
    let mut core = Core::new().unwrap();

    let token = env::var("TELEGRAM_BOT_TOKEN").unwrap();
    let api = Api::configure(token).build(core.handle()).unwrap();

    let future = api.stream().for_each(|update| {

        if let UpdateKind::Message(message) = update.kind {

            match message.kind {
                MessageKind::Text {ref data, ..} => {
                        println!("<{}>: {}", &message.from.first_name, data);
                        let txt =  format!("Hi, {}! <b>You</b> just wrote '{}'", 
                            &message.from.first_name, data);
                        api.spawn(message.text_reply(txt).parse_mode(ParseMode::Html));
                        // api.spawn(message.from.text(txt).parse_mode(ParseMode::Html));
                },
                _ => {println!("which message?")}
            }

        }
        Ok(())
    });

    core.run(future).unwrap();
}