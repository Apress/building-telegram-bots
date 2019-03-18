use std::thread;
use std::sync::{Arc, Mutex};

static NTHREADS: i32 = 10;


// This is the `main` thread
fn main() {
    // Make a vector to hold the children which are spawned.
    let data = Arc::new(Mutex::new(0));
    let mut children = vec![];
    // let mut hello:i32 = 0;

    for i in 0..NTHREADS {
        // Spin up another thread
        children.push(thread::spawn(move || {
            let mut data = data.lock().unwrap();
            *+data = **data+1;
            println!("this is thread number {} , {}", i, data);
        }));
    }

    for child in children {
        // Wait for the thread to finish. Returns a result.
        let _ = child.join();
    }
}
