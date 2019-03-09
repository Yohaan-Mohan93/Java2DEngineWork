package javagames.sound;

public class BlockingDataLine extends AudioStream {
	
	private AudioDataLine stream;
	
	public BlockingDataLine(byte[] soundData) {
		super(soundData);
	}
	
	@Override
	public void open() {
		lock.lock();
		try {
			stream = new AudioDataLine(soundData);
			stream.initialize();
			stream.addLineListener(this);
			stream.open();
			while(!open) {
				cond.await();
			}
			createControls(stream.getLine());
			System.out.println("open");
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public void start() {
		lock.lock();
		try {
			stream.start();
			while(!started) {
				cond.await();
			}
			System.out.println("started");
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public void loop(int count) {
		lock.lock();
		try {
			stream.loop(count);
			while(!started) {
				cond.await();
			}
			System.out.println("started");
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public void restart() {
		stream.reset();
	}
	
	@Override
	public void stop() {
		lock.lock();
		try {
			stream.stop();
			while(started) {
				cond.await();
			}
			System.out.println("Stopped");
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	@Override 
	public void close() {
		lock.lock();
		try {
			stream.stop();
			while(started) {
				cond.await();
			}
			clearControls();
			System.out.println("closed");
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
