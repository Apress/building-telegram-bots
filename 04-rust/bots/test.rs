use std::thread;


fn main() {
	thread::Builder::new().name("child1".to_string()).spawn(move || {
	    println!("Hello, world! threaded");
	});
    println!("Hello World!");
}