# thread-excercise

A Maven thread exercise project. Maven build has not been tested as initial commit only run in eclipse.

Main method read a line:
M,N
M -> thread count
N -> queue size

WorkerContainer holds the a list of WorkerSender threads which keep trying to obtain an integer from queue and send it.
Probably, this thread should not keep trying in this case. But the intention is to make it something like ThreadPool. So when there is something available in queue. They will pull the value and process it.
