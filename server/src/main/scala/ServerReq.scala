import org.zeromq.ZMQ

object Main {
    def main(args : Array[String]) {
        //  Prepare our context and socket
        val context = ZMQ.context(1)
        val socket = context.socket(ZMQ.REP)
        println ("Listening on port 5555...")
        socket.bind ("tcp://*:5555")

        var keepRunning = true
        while (keepRunning) {
            val request = socket.recv(0)
            val message = new String(request)
            println ("Received request: [" + message + "]")

            if (message == "disconnect") {
                println("Received 'disconnect' command.")
                keepRunning = false
            }
            //  Do some 'work'
            try {
                Thread.sleep (1000)
            } catch  {
                case e: InterruptedException => e.printStackTrace()
            }

            //  Send reply back to client
            val reply = "from Scala!".getBytes
            socket.send(reply, 0)
        }
        socket.close()
        context.term()
        println("Disconnected!")
    }
}