package hw7.concurrent;

/**
 * A read/write lock that allows multiple readers, disallows multiple writers, and allows a writer to 
 * acquire a read lock while holding the write lock. 
 * 
 * A writer may also aquire a second write lock.
 * 
 * A reader may not upgrade to a write lock.
 * 
 */
public class ReentrantLock {

	//TODO: Declare data members.	

	/**
	 * Construct a new ReentrantLock.
	 */
	public ReentrantLock() {
		//TODO: Replace with your code.
	}

	/**
	 * Returns true if the invoking thread holds a read lock.
	 * @return
	 */
	public synchronized boolean hasRead() {
		//TODO: Replace with your code.
		return false;
	}

	/**
	 * Returns true if the invoking thread holds a write lock.
	 * @return
	 */
	public synchronized boolean hasWrite() {
		//TODO: Replace with your code.
		return false;
	}

	/**
	 * Non-blocking method that attempts to acquire the read lock.
	 * Returns true if successful.
	 * @return
	 */
	public synchronized boolean tryLockRead() {
		//TODO: Replace with your code.
		return false;
	}

	/**
	 * Non-blocking method that attempts to acquire the write lock.
	 * Returns true if successful.
	 * @return
	 */	public synchronized boolean tryLockWrite() {
			//TODO: Replace with your code.
			return false;
	 }

	 /**
	  * Blocking method that will return only when the read lock has been 
	  * acquired.
	  */	 
	 public synchronized void lockRead() {
			//TODO: Replace with your code.
	 }

	 /**
	  * Releases the read lock held by the calling thread. Other threads may continue
	  * to hold a read lock.
	  */
	 public synchronized void unlockRead() throws IllegalMonitorStateException {
			//TODO: Replace with your code.
	 }

	 /**
	  * Blocking method that will return only when the write lock has been 
	  * acquired.
	  */
	 public synchronized void lockWrite() {
			//TODO: Replace with your code.
	 }

	 /**
	  * Releases the write lock held by the calling thread. The calling thread may continue to hold
	  * a read lock.
	  */
	 public synchronized void unlockWrite() throws IllegalMonitorStateException {
			//TODO: Replace with your code.
	 }
}