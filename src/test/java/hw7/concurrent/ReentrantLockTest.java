package hw7.concurrent;

import org.junit.Assert;
import org.junit.Test;

public class ReentrantLockTest {

	/**
	  
Need to rewrite test cases. Many students are passing test cases with bad implementations 
(for example, allowing a thread to get the write lock as long as IT, not any other thread, holds the read lock).	 
	 */

	
	@Test
	public void testLockSimple() {		
		String testName = "testLockSimple";
		ReentrantLock lock = new ReentrantLock();
		lock.lockWrite();
		lock.lockRead();

		Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
				" Read lock not held. %n", testName), lock.hasRead());
		lock.unlockRead();
		Assert.assertFalse(String.format("%n" + "Test Case: %s%n" +
				" Read lock not released. %n", testName), lock.hasRead());
		Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
				" Write lock not held. %n", testName), lock.hasWrite());
		lock.unlockWrite();
		Assert.assertFalse(String.format("%n" + "Test Case: %s%n" +
				" Write lock not released. %n", testName), lock.hasWrite());
	}

	@Test
	public void testLockMultipleWrites() {		
		String testName = "testLockMultipleWrites";
		ReentrantLock lock = new ReentrantLock();
		
		lock.lockWrite();
		lock.lockWrite();

		lock.unlockWrite();
		Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
				" Write lock not held. %n", testName), lock.hasWrite());
		lock.unlockWrite();
	}

	@Test
	public void testWriteLockMultiThread() {	
		String testName = "testWriteLockMultiThread";

		ReentrantLock lock = new ReentrantLock();
		boolean result = lock.tryLockWrite();
		if(!result) {
			Assert.fail(String.format("%n" + "Test Case: %s%n" +
					" Unable to acquire write lock. %n", testName));
		}
		
		Worker tlrw = new TryLockReadWorker(lock);
		Thread t1 = new Thread(tlrw);

		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			Assert.fail();
		}
		Assert.assertFalse(String.format("%n" + "Test Case: %s%n" +
				" Read lock acquired. %n", testName), tlrw.result);
		lock.unlockWrite();
	}

	@Test
	public void testReadLockMultiThread() {	
		String testName = "testReadLockMultiThread";

		ReentrantLock lock = new ReentrantLock();
		lock.lockRead();

		Worker tlrw = new TryLockReadWorker(lock);
		Thread t1 = new Thread(tlrw);

		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			Assert.fail();
		}
		
		Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
				" Read lock not acquired. %n", testName), tlrw.result);
		lock.unlockRead();
	}

	@Test
	public void testLockUpgrade() {
		String testName = "testLockUpgrade";

		ReentrantLock lock = new ReentrantLock();
		lock.lockRead();

		boolean result = lock.tryLockWrite();
		if(result) {
			Assert.fail(String.format("%n" + "Test Case: %s%n" +
					" Lock upgrade read to write should be disallowed. %n", testName));
		}
		lock.unlockRead();

	}
	
	@Test
	public void testHasRead() {
		String testName = "testHasRead";

		ReentrantLock lock = new ReentrantLock();
		lock.lockRead();
		
		
		Worker hrw = new HasReadWorker(lock);
		Thread t1 = new Thread(hrw);
		
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			Assert.fail();
		} 
		
		Assert.assertFalse(String.format("%n" + "Test Case: %s%n" +
				" Read lock should not be reported as held by thread. %n", testName), hrw.result);

		lock.unlockRead();	
	}

	@Test
	public void testHasWrite() {
		String testName = "testHasWrite";

		ReentrantLock lock = new ReentrantLock();
		lock.lockWrite();
		
		
		Worker hrw = new HasWriteWorker(lock);
		Thread t1 = new Thread(hrw);
		
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			Assert.fail();
		} 
		
		Assert.assertFalse(String.format("%n" + "Test Case: %s%n" +
				" Write lock should not be reported as held by thread. %n", testName), hrw.result);

		lock.unlockWrite();	
	}
	
	
	@Test
	public void testExceptionUnlockRead() {
		String testName = "testExceptionUnlockRead";

		ReentrantLock lock = new ReentrantLock();
		try {
			lock.unlockRead();
			Assert.fail(String.format("%n" + "Test Case: %s%n" +
					" Should throw IllegalMonitorStateException if unlocking unheld read lock. %n", testName));
		} catch(IllegalMonitorStateException ims) {
			
		}
	}
	
	@Test
	public void testExceptionUnlockWrite() {
		String testName = "testExceptionUnlockWrite";

		ReentrantLock lock = new ReentrantLock();
		try {
			lock.unlockWrite();
			Assert.fail(String.format("%n" + "Test Case: %s%n" +
					" Should throw IllegalMonitorStateException if unlocking unheld write lock. %n", testName));
		} catch(IllegalMonitorStateException ims) {
			
		}
	}

	class HasReadWorker extends Worker {

		HasReadWorker(ReentrantLock lock) {
			super(lock);
		}
		
		@Override
		public void run() {
			result = lock.hasRead();
			
		}
		
	}
	
	
	class HasWriteWorker extends Worker {

		HasWriteWorker(ReentrantLock lock) {
			super(lock);
		}
		
		@Override
		public void run() {
			result = lock.hasWrite();
			
		}
		
	}
	
	class TryLockReadWorker extends Worker {
		
		TryLockReadWorker(ReentrantLock lock) {
			super(lock);
		}	
		
		@Override
		public void run() {
			result = lock.tryLockRead();
			
		}
		
	}

	abstract class Worker implements Runnable {
		
		boolean result = false;
		ReentrantLock lock;
		
		Worker(ReentrantLock lock) {
			this.lock = lock;
		}
		
	}
}
