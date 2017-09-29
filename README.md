Homework 7
==========

### Due Friday, October 27, 2017 - 5:00PM

For this homework, you will implement a *reentrant* read-write lock.

You will implement a lock similar to Java's [ReentrantReadWriteLock](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.html), however you may not use this class in your submission!

The required methods are documented in `ReentrantLock`.

Make sure to adhere to the following rules:

- Multiple threads may hold the read lock as long as no thread holds the write lock.
- Only one thread may hold the write lock at any time. That thread may hold multiple write locks.
- A thread holding a write lock may also acquire the read lock. 
- A thread only releases one lock at a time, therefore a thread may acquire a write lock then acquire a read lock. If this thread releases the write lock it will still hold the read lock.
- A thread holding a read lock *may not* upgrade to the write lock. This is to prevent deadlock.
- The `try...Lock` methods will *not* block, but rather return true or false to indicate whether the lock was acquired.
- The `lock` methods will block until the lock is acquired. Hint, you should use the `wait` method.
- If an `unlock` method is called and the calling thread does *not* hold the lock then an `IllegalMonitorStateException` will be thrown.

### Submission Requirements

1. Use the following link to create your private github repository for this assignment. The repository will be seeded with the skeleton code, test cases, and input files. [Homework 7]()
2. For full credit, make sure to follow all [Style Guidelines](https://github.com/CS514-F17/notes/blob/master/Admin/style.md). Points will be deducted for each violation.

### Grading Rubric

| Points | Criterion | Description |
| ------ | -------- | -------- |  
| 5 | testLockSimple | Code passes all test cases. |
| 5 | testLockMultipleWrites | Code passes all test cases. |
| 5 | testWriteLockMultiThread | Code passes all test cases. |
| 5 | testReadLockMultiThread| Code passes all test cases. |
| 5 | testLockUpgrade | Code passes all test cases. |
| 5 | testHasRead | Code passes all test cases. |
| 5 | testHasWrite | Code passes all test cases. |
| 5 | testExceptionUnlockRead | Code passes all test cases. |
| 5 | testExceptionUnlockWrite | Code passes all test cases. |
| 5 | Style | Code follows all style guidelines. |

Partial credit may be awarded for partial functionality and/or partially correct style elements.

### Academic Dishonesty

Any work you submit is expected to be your own original work. If you use any web resources in developing your code you are required to cite those resources. The only exception to this rule is code that is posted on the class website. The URL of the resource you used in a comment in your code is fine. If I google even a single line of uncited code and find it on the internet you may get a 0 on the assignment or an F in the class. You may also get a 0 on the assignment or an F in the class if your solution is at all similar to that of any other student.
