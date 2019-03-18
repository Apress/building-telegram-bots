extern crate chrono;

use chrono::prelude::*;

fn main() {
    let utc: DateTime<Utc> = Utc::now();       
    println!("{}", utc);
}